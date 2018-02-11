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
 * Container object block the request thread, collect multiple results from CEP per order and return the request
 * thread with results upon receiving expected number of results.
 */
public class ResultContainer {
    private static final Logger log = Logger.getLogger(ResultContainer.class);
    private CountDownLatch latch;
    private RiskScoreDTO riskScoreDTO;
    private AtomicInteger resultCount;
    private int thresholdCount = -9999;

    public ResultContainer(int size) {
        latch = new CountDownLatch(1);
        resultCount = new AtomicInteger(0);
    }

//    /**
//     * Upon receiving a result from forgotten stream, this method will update the result list and handle locking
//     * mechanisms.
//     *
//     * @param productID
//     * @param isForgotten
//     * @param currentThreshold
//     * @param avgCases
//     * @param avgEaches
//     */
//    public void addResult(int productID, boolean isForgotten, double currentThreshold, double avgCases, double avgEaches) {
//        //if this is a result for a forgotten product add to list
//        if (isForgotten) {
//            results.add(new ForgottenItemsDTO(Integer.toString(productID), (int) currentThreshold, (int) avgCases,
//                    (int) avgEaches));
//        }
//        //increase the result count
//        resultCount.incrementAndGet();
//        //if thresholdCount is set by count stream
//        if (thresholdCount != -9999) {
//            //if we have received the expected number of results
//            if (thresholdCount == resultCount.get()) {
//                //release the request thread.
//                latch.countDown();
//            }
//        }
//    }

    /**
     * Upon receiving a result from riskscore stream this method will update the result list and handle locking
     * mechanisms.
     *
     * @param eventID
     * @param score
     */
    public void addResult(String eventID, int score) {

        riskScoreDTO = new RiskScoreDTO(eventID, score);
        latch.countDown();

    }

    /**
     * This method will be called upon receiving count value though count stream.
     *
     * @param count count of results to be received
     */
    public void addThresholdCount(int count) {
        thresholdCount = count;
        //if we have already received expected amount of results release request thread.
        if (thresholdCount == resultCount.get()) {
            latch.countDown();
        }
    }

    public RiskScoreDTO getRiskScoreDTO() {
        return riskScoreDTO;
    }


}
