/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.context;

/**
 * RPC æ¡†æž¶è¿?è¡ŒçŠ¶æ€?
 * 
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public final class RpcRunningState {

    /**
     * æ˜¯å?¦æ­£åœ¨å…³é—­
     */
    static boolean shuttingDown = false;

    /**
     * æ˜¯å?¦å?•å…ƒæµ‹è¯•ï¼ˆè·³è¿‡ä¸€äº›åŠ è½½æˆ–è€…å?¸è½½ï¼‰
     */
    static boolean unitTestMode = false;

    /**
     * æ˜¯å?¦debugæ¨¡å¼?ï¼Œå¼€å?¯å?Žï¼Œä¼šæ‰“å?°ä¸€äº›é¢?å¤–çš„è°ƒè¯•æ—¥å¿—ï¼Œä¸?è¿‡è¿˜æ˜¯å?—slf4jçš„æ—¥å¿—çº§åˆ«é™?åˆ¶
     */
    static boolean debugMode    = false;

    /**
     * æ˜¯å?¦æ­£åœ¨å…³é—­
     *
     * @return æ˜¯å?¦å…³é—­
     */
    public static boolean isShuttingDown() {
        return shuttingDown;
    }

    /**
     * è®¾ç½®æ˜¯å?¦æ­£åœ¨å…³é—­
     *
     * @param shuttingDown æ˜¯å?¦æ­£åœ¨å…³é—­
     */
    static void setShuttingDown(boolean shuttingDown) {
        RpcRunningState.shuttingDown = shuttingDown;
    }

    /**
     * æ˜¯å?¦å?•å…ƒæµ‹è¯•æ¨¡å¼?
     *
     * @return æ˜¯å?¦å?•å…ƒæµ‹è¯•æ¨¡å¼?
     */
    public static boolean isUnitTestMode() {
        return unitTestMode;
    }

    /**
     * è®¾ç½®æ˜¯å?¦å?•å…ƒæµ‹è¯•æ¨¡å¼?
     *
     * @param unitTestMode å?•å…ƒæµ‹è¯•æ¨¡å¼?
     */
    public static void setUnitTestMode(boolean unitTestMode) {
        RpcRunningState.unitTestMode = unitTestMode;
    }

    /**
     * æ˜¯å?¦è°ƒè¯•æ¨¡å¼?
     *
     * @return æ˜¯å?¦è°ƒè¯•æ¨¡å¼?
     */
    public static boolean isDebugMode() {
        return debugMode;
    }

    /**
     * è®¾ç½®æ˜¯å?¦è°ƒè¯•æ¨¡å¼?
     *
     * @param debugMode æ˜¯å?¦è°ƒè¯•æ¨¡å¼?
     */
    public static void setDebugMode(boolean debugMode) {
        RpcRunningState.debugMode = debugMode;
    }
}
