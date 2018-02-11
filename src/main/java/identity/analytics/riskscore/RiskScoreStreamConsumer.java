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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.event.stream.core.WSO2EventConsumer;

/**
 * Event consumer class to consume riskscore stream. Correlation is made using streamID which is extracted from CEP config.
 */
public class RiskScoreStreamConsumer implements WSO2EventConsumer {
    private static final Log log = LogFactory.getLog(RiskScoreStreamConsumer.class);
    private String streamID;

    public RiskScoreStreamConsumer(String streamID) {
        this.streamID = streamID;
    }

    @Override
    public String getStreamId() {
        return streamID;
    }

    /**
     * This method will be called upon receiving new events for riskscore stream. In the method we find the corresponding
     * result container and add the result there.
     *
     * @param event
     */
    public void onEvent(Event event) {
        ResultContainer container = CarbonServiceValueHolder.getResultContainerMap().get(event.getPayloadData()[0]);
        if (container != null) {
            container.addResult((String)event.getPayloadData()[1], (int) event.getPayloadData()[2]);
        }
    }

    @Override
    public void onAddDefinition(StreamDefinition streamDefinition) {
        //Do nothing
    }

    @Override
    public void onRemoveDefinition(StreamDefinition streamDefinition) {
        //Do nothing
    }
}
