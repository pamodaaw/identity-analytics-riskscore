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

import identity.analytics.riskscore.dto.RiskScoreDTO;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Container object blocks the request thread, collects result from IS-Analytics return the request thread
 */
public class ResultContainer {
    private static final Logger log = Logger.getLogger(ResultContainer.class);
    private CountDownLatch latch;
    private RiskScoreDTO riskScoreDTO;

    public ResultContainer() {
        latch = new CountDownLatch(1);
    }

    /**
     * Upon receiving a result from riskscore stream this method will update the result list and handle locking
     * mechanisms.
     *
     * @param eventID   eventId of the result riskscore event
     * @param score     risk score for the request
     */
    public void addResult(String eventID, int score) {
        riskScoreDTO = new RiskScoreDTO(eventID, score);
        latch.countDown();
        if (log.isDebugEnabled()) {
            log.info("Result is added to the container. Releasing the thread");
        }
    }

    /**
     * Wait for other threads to post results
     *
     * @return isThrottled
     */
    public RiskScoreDTO getRiskScoreDTO() throws InterruptedException {
        latch.await(5, TimeUnit.SECONDS);
        return riskScoreDTO;
    }

}
