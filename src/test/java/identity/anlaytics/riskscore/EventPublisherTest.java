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
package identity.anlaytics.riskscore;

import identity.analytics.riskscore.CEPEngineConfig;
import identity.analytics.riskscore.EventPublisher;
import identity.analytics.riskscore.dto.AuthRequestDTO;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.databridge.agent.DataPublisher;

import java.util.UUID;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * TODO: Class level comments
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({EventPublisher.class, DataPublisher.class})
public class EventPublisherTest {


    @BeforeMethod
    void setUp() {

    }

    @Test
    public void testEventPubliser() throws Exception {
        CEPEngineConfig cepEngineConfig = mock(CEPEngineConfig.class);
        when(cepEngineConfig.getHostname()).thenReturn("localhost");
        when(cepEngineConfig.getBinaryTCPPort()).thenReturn("9612");
        when(cepEngineConfig.getRiskScoreStream()).thenReturn("org.wso2.is.analytics.stream.RiskScore:1.0.0");
        when(cepEngineConfig.getBinarySSLPort()).thenReturn("9712");
        when(cepEngineConfig.getUsername()).thenReturn("admin");
        when(cepEngineConfig.getPassword()).thenReturn("admin");
        DataPublisher dataPublisher = mock(DataPublisher.class);
//        DataPublisher dataPublisher = new DataPublisher("Binary", "tcp://localhost" +
//                ":9612","ssl://localhost" +
//                ":9712","admin", "admin");
        whenNew(DataPublisher.class).withAnyArguments().thenThrow(new RuntimeException());
        EventPublisher eventPublisher = new EventPublisher(dataPublisher, cepEngineConfig);

        AuthRequestDTO authRequestDTO = mock(AuthRequestDTO.class);
        String id = String.valueOf(UUID.randomUUID());
//        eventPublisher.sendEvent(authRequestDTO,id);


    }
}
