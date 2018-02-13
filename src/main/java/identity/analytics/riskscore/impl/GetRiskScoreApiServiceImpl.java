/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package identity.analytics.riskscore.impl;

import identity.analytics.riskscore.CEPEngineConfig;
import identity.analytics.riskscore.CarbonServiceValueHolder;
import identity.analytics.riskscore.EventPublisher;
import identity.analytics.riskscore.GetRiskScoreApiService;
import identity.analytics.riskscore.ResultContainer;
import identity.analytics.riskscore.RiskScoreStreamConsumer;
import identity.analytics.riskscore.dto.AuthRequestDTO;
import identity.analytics.riskscore.dto.RiskScoreDTO;
import identity.analytics.riskscore.exception.RiskScoreServiceConfigurationException;
import identity.analytics.riskscore.util.RiskScoreServiceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.event.stream.core.EventStreamService;
import org.wso2.carbon.event.stream.core.exception.EventStreamConfigurationException;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.core.Response;

/**
 * Contains the risk score service implementation logic
 */
public class GetRiskScoreApiServiceImpl extends GetRiskScoreApiService {

    private static final Log log = LogFactory.getLog(GetRiskScoreApiServiceImpl.class);

    private EventStreamService eventStreamService;
    private RiskScoreStreamConsumer riskScoreStreamConsumer;
    private Map<String, ResultContainer> resultContainerMap = new ConcurrentHashMap<>();
    private CEPEngineConfig cepEngineConfig;
    private EventPublisher publisher;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    public GetRiskScoreApiServiceImpl() {

        eventStreamService = (EventStreamService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(EventStreamService.class, null);
        //Set the references so other classes can reuse
        CarbonServiceValueHolder.setEventstreamservice(eventStreamService);
        CarbonServiceValueHolder.setResultContainerMap(resultContainerMap);

        try {
            //Reads the configuration file located at <PRODUCT_HOME>/repository/conf/cep-config.xml
            cepEngineConfig = RiskScoreServiceUtil.loadCEPConfig();
            riskScoreStreamConsumer = new RiskScoreStreamConsumer(cepEngineConfig.getRiskScoreStream());
            publisher = new EventPublisher(cepEngineConfig);
            executorService.submit(new StreamSubscriber());
        } catch (RiskScoreServiceConfigurationException e) {
            log.error("Exception occurred when reading CEP config at repository/conf/cep-config.xml. Please check the" +
                    " configurations");
            throw new RuntimeException("Exception occurred when reading CEP config at repository/conf/cep-config.xml." +
                    " Please check the" +
                    " configurations", e);
        }
    }


    /**
     * auth request is forwarded to publisher to publish to CEP. Then upon calling getResults() request thread will
     * block on a
     * Count Down latch until results arrive.
     *
     * @param authRequest authentication request of riskscore calculation
     * @return response
     */
    @Override
    public Response getRiskScore(AuthRequestDTO authRequest) {
        String id = String.valueOf(UUID.randomUUID());
        ResultContainer resultContainer = new ResultContainer();
        resultContainerMap.put(id, resultContainer);
        publisher.sendEvent(authRequest, id);
        RiskScoreDTO result = null;

        try {
            result = resultContainer.getRiskScoreDTO();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage(), e);
        }
        resultContainerMap.remove(id);
        if (log.isDebugEnabled()) {
            log.info("Sending the result to the calling service");
        }
        return Response.ok().entity(result).build();
    }


    public class StreamSubscriber implements Runnable {
        //Periodically poll and subscribe to service. This is needed because at the time of web app initialization
        // relevant resources needed for the web app are not loaded in WSO2 server. Hence we keep polling for the
        // resources.
        @Override
        public void run() {
            //setup thread local information that will be used by carbon service for verification purposes.
            int tenantId = MultitenantConstants.SUPER_TENANT_ID;
            PrivilegedCarbonContext.startTenantFlow();
            PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantId(tenantId);
            while (true) {
                try {
                    //subscribe to Event Stream Service as consumers to receive events from CEP
                    eventStreamService.subscribe(riskScoreStreamConsumer);
                } catch (EventStreamConfigurationException e) {
                    log.error("Riskscore service API deployment held back due to unavailability of event " +
                            "streams. Will re-try again in 20 seconds.", e);
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();
                        log.error(e.getMessage(), e);
                    }
                    continue;
                }
                log.info("Riskscore Service API Successfully subscribed to event streams");
                break;
            }
            PrivilegedCarbonContext.endTenantFlow();
        }
    }
}

