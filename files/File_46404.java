/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codingapi.txlcn.common.util.id;

/**
 * Description:
 * Date: 19-1-30 ä¸‹å?ˆ8:10
 *
 * @author Twitter
 * @author æ— å§‹ä¹‹å??
 */
public class SnowFlakeGenerator {

    public static class Factory {
        /**
         * æœºå™¨æ ‡è¯†å? ç”¨çš„ä½?æ•°
         */
        private final static int DEFAULT_MACHINE_BIT_NUM = 5;

        /**
         * æ•°æ?®ä¸­å¿ƒå? ç”¨çš„ä½?æ•°
         */
        private final static int DEFAULT_IDC_BIT_NUM = 5;

        private int machineBitNum;
        private int idcBitNum;

        public Factory() {
            this.idcBitNum = DEFAULT_IDC_BIT_NUM;
            this.machineBitNum = DEFAULT_MACHINE_BIT_NUM;
        }

        public Factory(int machineBitNum, int idcBitNum) {
            this.idcBitNum = idcBitNum;
            this.machineBitNum = machineBitNum;
        }

        public SnowFlakeGenerator create(long idcId, long machineId) {
            return new SnowFlakeGenerator(this.idcBitNum, this.machineBitNum, idcId, machineId);
        }
    }

    /**
     * èµ·å§‹çš„æ—¶é—´æˆ³ UTC+8:00 2019-01-01 0:0:0 000
     */
    private final static long START_STAMP = 1546272000000L;

    /**
     * å?¯åˆ†é…?çš„ä½?æ•°
     */
    private final static int REMAIN_BIT_NUM = 22;

    /**
     * idcç¼–å?·
     */
    private long idcId;

    /**
     * æœºå™¨ç¼–å?·
     */
    private long machineId;

    /**
     * å½“å‰?åº?åˆ—å?·
     */
    private long sequence = 0L;

    /**
     * ä¸Šæ¬¡æœ€æ–°æ—¶é—´æˆ³
     */
    private long lastStamp = -1L;

    /**
     * idcå??ç§»é‡?ï¼šä¸€æ¬¡è®¡ç®—å‡ºï¼Œé?¿å…?é‡?å¤?è®¡ç®—
     */
    private int idcBitLeftOffset;

    /**
     * æœºå™¨idå??ç§»é‡?ï¼šä¸€æ¬¡è®¡ç®—å‡ºï¼Œé?¿å…?é‡?å¤?è®¡ç®—
     */
    private int machineBitLeftOffset;

    /**
     * æ—¶é—´æˆ³å??ç§»é‡?ï¼šä¸€æ¬¡è®¡ç®—å‡ºï¼Œé?¿å…?é‡?å¤?è®¡ç®—
     */
    private int timestampBitLeftOffset;

    /**
     * æœ€å¤§åº?åˆ—å€¼ï¼šä¸€æ¬¡è®¡ç®—å‡ºï¼Œé?¿å…?é‡?å¤?è®¡ç®—
     */
    private int maxSequenceValue;

    private SnowFlakeGenerator(int idcBitNum, int machineBitNum, long idcId, long machineId) {
        int sequenceBitNum = REMAIN_BIT_NUM - idcBitNum - machineBitNum;

        if (idcBitNum < 0 || machineBitNum <= 0 || sequenceBitNum <= 0) {
            throw new IllegalArgumentException("Error bit number");
        }

        this.maxSequenceValue = ~(-1 << sequenceBitNum);

        machineBitLeftOffset = sequenceBitNum;
        idcBitLeftOffset = machineBitNum + sequenceBitNum;
        timestampBitLeftOffset = idcBitNum + machineBitNum + sequenceBitNum;

        this.idcId = idcId;
        this.machineId = machineId;
    }

    /**
     * äº§ç”Ÿä¸‹ä¸€ä¸ªID
     * @return id
     */
    public synchronized long nextId() {
        long currentStamp = getTimeMill();
        if (currentStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.");
        }

        if (currentStamp == lastStamp) {
            sequence = (sequence + 1) & this.maxSequenceValue;
            if (sequence == 0L) {
                lastStamp = tilNextMillis();
            }
        } else {
            sequence = 0L;
        }

        lastStamp = currentStamp;

        return (currentStamp - START_STAMP) <<
                timestampBitLeftOffset | idcId << idcBitLeftOffset | machineId << machineBitLeftOffset | sequence;
    }

    private long getTimeMill() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis() {
        long timestamp = getTimeMill();
        while (timestamp <= lastStamp) {
            timestamp = getTimeMill();
        }
        return timestamp;
    }
    
}
