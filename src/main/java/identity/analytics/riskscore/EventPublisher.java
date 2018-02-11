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
        dataPublisher.publish(event);

    }

    public void sendEvent(AuthRequestDTO authRequest, String id) {

        //payload should match the stream definition
        String contextId = authRequest.getContextId();
        String eventId = authRequest.getEventId();
        String eventType = authRequest.getEventType();
        boolean authenticationSuccess = authRequest.getAuthenticationSuccess();
        String username = authRequest.getUsername();
        String localUsername = authRequest.getLocalUsername();
        String userStoreDomain = authRequest.getUserStoreDomain();
        String tenantDomain = authRequest.getTenantDomain();
        String remoteIp = authRequest.getRemoteIp();
        String region = authRequest.getRegion();
        String inboundAuthType = authRequest.getInboundAuthType();
        String serviceProvider = authRequest.getServiceProvider();
        boolean rememberMeEnabled = authRequest.getRememberMeEnabled();
        boolean forceAuthEnabled = authRequest.getForceAuthEnabled();
        boolean passiveAuthEnabled = authRequest.getPassiveAuthEnabled();
        String rolesCommaSeparated = authRequest.getRolesCommaSeparated();
        String authenticationStep = authRequest.getAuthenticationStep();
        String identityProvider = authRequest.getIdentityProvider();
        boolean authStepSuccess = authRequest.getAuthStepSuccess();
        String stepAuthenticator = authRequest.getStepAuthenticator();
        boolean isFirstLogin = authRequest.getIsFirstLogin();
        String identityProviderType = authRequest.getIdentityProviderType();

        //create one event representing order and send
        Object[] payload = new Object[]{contextId, eventId, eventType, authenticationSuccess, username, localUsername,
                userStoreDomain, tenantDomain, remoteIp, region, inboundAuthType, serviceProvider, rememberMeEnabled,
                forceAuthEnabled, passiveAuthEnabled, rolesCommaSeparated, authenticationStep, identityProvider,
                authStepSuccess, stepAuthenticator, isFirstLogin, identityProviderType};
        sendEvent(payload, cepEngineConfig.getAuthenticationStream());

    }
}
