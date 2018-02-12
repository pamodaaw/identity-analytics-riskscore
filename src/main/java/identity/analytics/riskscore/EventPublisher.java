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

package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.AuthRequestDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.databridge.agent.DataPublisher;
import org.wso2.carbon.databridge.agent.exception.DataEndpointAgentConfigurationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointAuthenticationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointConfigurationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointException;
import org.wso2.carbon.databridge.commons.exception.TransportException;

/**
 * Event Publisher class to publish incoming requests as events to CEP engine.
 */
public class EventPublisher {
    private static final Log log = LogFactory.getLog(EventPublisher.class);
    private DataPublisher dataPublisher;
    private CEPEngineConfig cepEngineConfig;

    public EventPublisher(CEPEngineConfig cepEngineConfig) {
        this.cepEngineConfig = cepEngineConfig;
        try {
            this.dataPublisher = new DataPublisher("Binary", "tcp://" + cepEngineConfig.getHostname() +
                    ":" + cepEngineConfig.getBinaryTCPPort(), "ssl://" + cepEngineConfig.getHostname() +
                    ":" + cepEngineConfig.getBinarySSLPort(), cepEngineConfig.getUsername(), cepEngineConfig
                    .getPassword());

        } catch (DataEndpointAgentConfigurationException e) {
            log.error("Error in initializing binary data-publisher to send requests to global throttling engine " +
                    e.getMessage(), e);
        } catch (DataEndpointException e) {
            log.error("Error in initializing binary data-publisher to send requests to global throttling engine " +
                    e.getMessage(), e);
        } catch (DataEndpointConfigurationException e) {
            log.error("Error in initializing binary data-publisher to send requests to global throttling engine " +
                    e.getMessage(), e);
        } catch (DataEndpointAuthenticationException e) {
            log.error("Error in initializing binary data-publisher to send requests to global throttling engine " +
                    e.getMessage(), e);
        } catch (TransportException e) {
            log.error("Error in initializing binary data-publisher to send requests to global throttling engine " +
                    e.getMessage(), e);
        }

    }

    public void sendEvent(Object[] data, String streamID) {
        org.wso2.carbon.databridge.commons.Event event = new org.wso2.carbon.databridge.commons.Event(streamID,
                System.currentTimeMillis(), null, null, data);
//        dataPublisher.publish(event);

        Object[] payload = new Object[2];
        payload[0] = "eventID_1";
        payload[1] = "pamoda";
        org.wso2.carbon.databridge.commons.Event testEvent = new org.wso2.carbon.databridge.commons.Event(
                "org.wso2.is.analytics.stream.RiskScoreRequest:1.0.0", System.currentTimeMillis(),
                null, null, payload);
        dataPublisher.publish(testEvent);

    }


    public void sendEvent(AuthRequestDTO authRequest) {

        //payload should match the stream definition
        // create one event representing order and send
        Object[] payloadData = new Object[23];
        payloadData[0] = authRequest.getContextId();
        payloadData[1] = authRequest.getEventId();
        payloadData[2] = authRequest.getEventType();
        payloadData[3] = authRequest.getAuthenticationSuccess();
        payloadData[4] = authRequest.getUsername();
        payloadData[5] = authRequest.getLocalUsername();
        payloadData[6] = authRequest.getUserStoreDomain();
        payloadData[7] = authRequest.getTenantDomain();
        payloadData[8] = authRequest.getRemoteIp();
        payloadData[9] = "NOT_AVAILABLE";
        payloadData[10] = authRequest.getInboundAuthType();
        payloadData[11] = authRequest.getServiceProvider();
        payloadData[12] = authRequest.getRememberMeEnabled();
        payloadData[13] = authRequest.getForceAuthEnabled();
        payloadData[14] = authRequest.getPassiveAuthEnabled();
        payloadData[15] = authRequest.getRolesCommaSeparated();
        payloadData[16] = authRequest.getStepAuthenticator();
        payloadData[17] = authRequest.getIdentityProvider();
        payloadData[18] = authRequest.getAuthStepSuccess();
        payloadData[19] = authRequest.getStepAuthenticator();
        payloadData[20] = authRequest.getIsFirstLogin();
        payloadData[21] = authRequest.getIdentityProviderType();
        payloadData[22] = System.currentTimeMillis();

        sendEvent(payloadData, cepEngineConfig.getAuthenticationStream());

    }
}
