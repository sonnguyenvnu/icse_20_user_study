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

import com.alipay.sofa.rpc.base.Sortable;
import com.alipay.sofa.rpc.common.struct.OrderedComparator;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.log.LogCodes;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Sofa RPC 与�?置相关的工具类，�?�?赖于 Sofa 框架的�?置 <br>
 * <p>
 * 大部分�?�数�?��?置，优先级：System.setProperty() > 外部加载器(例如�?�能�?个应用独立的sofa-config.properties） > rpc-config.propertirs
 * </p>
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public final class SofaConfigs {

    /**
     * 日志
     */
    private static final Logger                     LOGGER         = LoggerFactory.getLogger(SofaConfigs.class);

    /**
     * 外部加载器
     */
    private static final List<ExternalConfigLoader> CONFIG_LOADERS = new ArrayList<ExternalConfigLoader>();
    /**
     * loader�?�化的�?
     */
    private static ReentrantReadWriteLock           lock           = new ReentrantReadWriteLock();
    /**
     * 读�?，�?许并�?�读 
     */
    private static Lock                             rLock          = lock.readLock();
    /**
     * 写�?，写的时候�?�?许读 
     */
    private static Lock                             wLock          = lock.writeLock();

    /**
     * rpc-config.properties
     */
    private static Properties                       config;

    /**
     * �?始化 config/rpc-config.properties
     * �?始化失败时，直接报错
     *
     * @return �?置内容
     */
    public static synchronized Properties getConfig() {
        if (config == null) {
            try {
                String rpcConfig = "config/rpc-config.properties";
                InputStream ins = SofaConfigs.class.getClassLoader().getResourceAsStream(rpcConfig);
                if (ins == null) {
                    ins = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(rpcConfig);
                }

                config = new Properties();
                config.load(ins);
            } catch (Exception e) {
                config = new Properties();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(LogCodes.getLog(LogCodes.ERROR_RPC_CONFIG_LOAD));
                }
            }
        }

        return config;
    }

    /**
     * 解�?数字型�?置
     *
     * @param key          �?置项
     * @param defaultValue 默认值
     * @return �?置
     */
    public static int getIntegerValue(String key, int defaultValue) {
        return getIntegerValue(null, key, defaultValue);
    }

    /**
     * 解�?数字型�?置
     *
     * @param appName      应用�??
     * @param key          �?置项
     * @param defaultValue 默认值
     * @return �?置
     */
    public static int getIntegerValue(String appName, String key, int defaultValue) {
        String ret = getStringValue0(appName, key);
        return StringUtils.isEmpty(ret) ? defaultValue : CommonUtils.parseInt(ret, defaultValue);
    }

    /**
     * 获�?�Boolean格�?的Config
     *
     * @param key          �?置项
     * @param defaultValue 默认值
     * @return �?置
     */
    public static boolean getBooleanValue(String key, boolean defaultValue) {
        return getBooleanValue(null, key, defaultValue);
    }

    /**
     * 获�?�Boolean格�?的Config
     *
     * @param appName      应用�??
     * @param key          �?置项
     * @param defaultValue 默认值
     * @return �?置
     */
    public static boolean getBooleanValue(String appName, String key, boolean defaultValue) {
        String ret = getStringValue0(appName, key);
        return StringUtils.isEmpty(ret) ? defaultValue : CommonUtils.parseBoolean(ret, defaultValue);
    }

    /**
     * 通用 获�?�方法
     * <p>
     * 与没有 appName 的方法相比，该方法�?需�?传入 appName
     * <p>
     *
     * @param key          �?置项
     * @param defaultValue 默认值
     * @return �?置
     */
    public static String getStringValue(String key, String defaultValue) {
        return getStringValue(null, key, defaultValue);
    }

    /**
     * 获�?��?置值
     *
     * @param appName      应用�??
     * @param key          �?置项
     * @param defaultValue 默认值
     * @return �?置
     */
    public static String getStringValue(String appName, String key, String defaultValue) {
        String ret = getStringValue0(appName, key);
        return StringUtils.isEmpty(ret) ? defaultValue : ret.trim();
    }

    /**
     * System.getProperty() > 外部�?置 > rpc-config.properties
     *
     * @param appName 应用�??
     * @param key     �?置项
     * @return �?置
     */
    private static String getStringValue0(String appName, String key) {
        String ret = System.getProperty(key);
        if (StringUtils.isNotEmpty(ret)) {
            return ret;
        }
        rLock.lock();
        try {
            for (ExternalConfigLoader configLoader : CONFIG_LOADERS) {
                ret = appName == null ? configLoader.getValue(key)
                    : configLoader.getValue(appName, key);
                if (StringUtils.isNotEmpty(ret)) {
                    return ret;
                }
            }
        } finally {
            rLock.unlock();
        }
        return getConfig().getProperty(key);
    }

    /**
     * 注册外部�?置加载器
     *
     * @param configLoader �?置加载器
     */
    public static void registerExternalConfigLoader(ExternalConfigLoader configLoader) {
        wLock.lock();
        try {
            CONFIG_LOADERS.add(configLoader);
            Collections.sort(CONFIG_LOADERS, new OrderedComparator<ExternalConfigLoader>());
        } finally {
            wLock.unlock();
        }
    }

    /**
     * �??注册外部�?置加载器
     *
     * @param configLoader �?置加载器
     */
    public static void unRegisterExternalConfigLoader(ExternalConfigLoader configLoader) {
        wLock.lock();
        try {
            CONFIG_LOADERS.remove(configLoader);
            Collections.sort(CONFIG_LOADERS, new OrderedComparator<ExternalConfigLoader>());
        } finally {
            wLock.unlock();
        }
    }

    /**
     * 外部�?置加载器
     */
    public static abstract class ExternalConfigLoader implements Sortable {

        /**
         * 顺�?
         *
         * @return 顺�?，从�?到大执行
         */
        @Override
        public int getOrder() {
            return 0;
        }

        /**
         * 获�?��?置
         *
         * @param key 键
         * @return 值
         */
        public abstract String getValue(String key);

        /**
         * 按应用获�?��?置
         *
         * @param appName 应用�??
         * @param key     键
         * @return 值
         */
        public abstract String getValue(String appName, String key);
    }
}
