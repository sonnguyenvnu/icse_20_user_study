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
package com.alipay.sofa.rpc.common;

import com.alipay.sofa.rpc.common.annotation.VisibleForTesting;
import com.alipay.sofa.rpc.common.utils.NetUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;

/**
 * ç³»ç»Ÿç›¸å…³ä¿¡æ?¯
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class SystemInfo {

    /**
     * ç¼“å­˜äº†æœ¬æœºåœ°å?€
     */
    private static String  LOCALHOST;
    /**
     * ç¼“å­˜äº†ç‰©ç?†æœºåœ°å?€
     */
    private static String  HOSTMACHINE;
    /**
     * æ˜¯å?¦Windowsç³»ç»Ÿ
     */
    private static boolean IS_WINDOWS;
    /**
     * æ˜¯å?¦Linuxç³»ç»Ÿ
     */
    private static boolean IS_LINUX;
    /**
     * æ˜¯å?¦MACç³»ç»Ÿ
     */
    private static boolean IS_MAC;

    static {
        boolean[] os = parseOSName();
        IS_WINDOWS = os[0];
        IS_LINUX = os[1];
        IS_MAC = os[2];

        LOCALHOST = NetUtils.getLocalIpv4();
        HOSTMACHINE = parseHostMachine();
    }

    /**
     * è§£æž?ç‰©ç?†æœºåœ°å?€
     *
     * @return ç‰©ç?†æœºåœ°å?€
     */
    @VisibleForTesting
    static boolean[] parseOSName() {
        boolean[] result = new boolean[] { false, false, false };
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            result[0] = true;
        } else if (osName.contains("linux")) {
            result[1] = true;
        } else if (osName.contains("mac")) {
            result[2] = true;
        }
        return result;
    }

    /**
     * æ˜¯å?¦Windowsç³»ç»Ÿ
     */
    public static boolean isWindows() {
        return IS_WINDOWS;
    }

    /**
     * æ˜¯å?¦Linuxç³»ç»Ÿ
     */
    public static Boolean isLinux() {
        return IS_LINUX;
    }

    /**
     * æ˜¯å?¦Macç³»ç»Ÿ
     */
    public static boolean isMac() {
        return IS_MAC;
    }

    /**
     * å¾—åˆ°CPUæ ¸å¿ƒæ•°ï¼ˆdockç‰¹æ®Šå¤„ç?†ï¼‰
     *
     * @return å?¯ç”¨çš„cpuå†…æ ¸æ•°
     */
    public static int getCpuCores() {
        // æ‰¾ä¸?åˆ°æ–‡ä»¶æˆ–è€…å¼‚å¸¸ï¼Œåˆ™åŽ»ç‰©ç?†æœºçš„æ ¸å¿ƒæ•°
        int cpu = RpcConfigs.getIntValue(RpcOptions.SYSTEM_CPU_CORES);
        return cpu > 0 ? cpu : Runtime.getRuntime().availableProcessors();
    }

    /**
     * å¾—åˆ°ç¼“å­˜çš„æœ¬æœºåœ°å?€
     *
     * @return æœ¬æœºåœ°å?€
     */
    public static String getLocalHost() {
        return LOCALHOST;
    }

    /**
     * è®¾ç½®æœ¬æœºåœ°å?€åˆ°ç¼“å­˜ï¼ˆä¸€èˆ¬æ˜¯å¤šç½‘å?¡ç”±å¤–éƒ¨é€‰æ‹©å?Žè®¾ç½®ï¼‰
     *
     * @param localhost æœ¬æœºåœ°å?€
     */
    public static void setLocalHost(String localhost) {
        LOCALHOST = localhost;
    }

    /**
     * è§£æž?ç‰©ç?†æœºåœ°å?€
     *
     * @return ç‰©ç?†æœºåœ°å?€
     */
    @VisibleForTesting
    static String parseHostMachine() {
        String hostMachine = System.getProperty("host_machine");
        return StringUtils.isNotEmpty(hostMachine) ? hostMachine : null;
    }

    /**
     * ç‰©ç?†æœºåœ°å?€
     *
     * @return ç‰©ç?†æœºåœ°å?€
     */
    public static String getHostMachine() {
        return HOSTMACHINE;
    }
}
