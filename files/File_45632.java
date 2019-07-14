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
package com.alipay.sofa.rpc.log;

import com.alipay.sofa.rpc.common.utils.IOUtils;
import com.alipay.sofa.rpc.log.exception.LogCodeNotFoundException;
import com.alipay.sofa.rpc.log.exception.LogFormatException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 RPC-01001
 �?�件�??(-) | 日志版本(1�?) | 日志级别(1�?) | 日志�?(3�?)

 日志级别：
 0: 普通日志输出。
 1: 业务警告：这类日志一般是业务使用�?当时的输出，比如�?推�??使用哪些接�?��?�?�现业务潜在的风险时，打�?�的日志。业务开�?�人员需�?知�?�这样的输出，并能正确解决
 2: 业务错误：这类日志一般是业务异常时的输出，当出现这个日志时，系统的�?个�?务�?�能会�?�?�用，或者状�?异常。业务开�?�人员需�?知�?�这样的输出，并能正确解决
 3: 框架警告：这类日志一般是框架内部警告，出现这个日志时应该�?影�?业务使用，但是会存在潜在的风险，业务开�?�人员应该需�?�?�系框架�?�学分�?
 4: 框架错误：这类日志一般是框架内部异常，出现这个日志时，业务开�?�人员应该难以解决，需�?�?�系框架�?�学
 9: debug日志

 日志�?：（3�?目�?够用了，如果�?增加到4�?的�?，直接改日志�?版本�?�）
 三�?日志�?，第一�?代表日志所属内部模�?�：
 0：通用
 1：代�?�层
 2：路由层
 3：�?务调用
 4：TR
 *
 * @author <a href=mailto:hongwei.yhw@antfin.com>hongwei.yhw</a>
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class LogCodes {

    private final static Logger                LOGGER                                     = LoggerFactory
                                                                                              .getLogger(LogCodes.class);
    protected final static String              LOG                                        = "RPC-%s: %s %s";
    protected static final Map<String, String> LOG_CODES                                  = new ConcurrentHashMap<String, String>();

    protected static String                    NOTE                                       = "";

    protected final static String              NOTE_CODE                                  = "99999";

    public static final String                 WARN_CANNOT_FOUND_SERVICE_4_SERVER         = "01301";
    public static final String                 ERROR_SERVICE_INTERFACE_CANNOT_FOUND       = "02001";
    public static final String                 ERROR_STANDALONE_APPNAME_CHECK_FAIL        = "02002";
    public static final String                 ERROR_STANDALONE_REFER_GENERIC_CHECK_FAIL  = "02003";
    public static final String                 ERROR_PROVIDER_TARGET_NULL                 = "02101";
    public static final String                 ERROR_PROVIDER_TARGET_REGISTERED           = "02102";
    public static final String                 ERROR_PROXY_CONSUME_FAIL                   = "02103";
    public static final String                 ERROR_PROXY_PUBLISH_FAIL                   = "02104";
    public static final String                 ERROR_PROXY_PRE_UNPUBLISH_FAIL             = "02105";
    public static final String                 ERROR_PROXY_POST_UNPUBLISH_FAIL            = "02106";
    public static final String                 ERROR_PROXY_BINDING_CLASS_CANNOT_FOUND     = "02107";
    public static final String                 ERROR_CANNOT_FOUND_SERVICE_4_CLIENT        = "02301";
    public static final String                 ERROR_INVOKE_TIMEOUT                       = "02302";
    public static final String                 ERROR_INVOKE_TIMEOUT_NO_TARGET             = "02303";
    public static final String                 ERROR_INVOKE_GET_CLIENT                    = "02304";
    public static final String                 ERROR_INVOKE_NO_TR_INVOKE_SERVICE          = "02305";
    public static final String                 ERROR_NO_AVAILBLE_PROVIDER                 = "02306";
    public static final String                 ERROR_TRANSMIT_PARSE                       = "02307";
    public static final String                 ERROR_TRANSMIT_PARSE_APPNAME               = "02308";
    public static final String                 ERROR_TRANSMIT_PARSE_CONFIG                = "02309";
    public static final String                 ERROR_TRANSMIT_PARSE_URL                   = "02310";
    public static final String                 ERROR_TRANSMIT                             = "02311";
    public static final String                 ERROR_TARGET_URL_INVALID                   = "02312";
    public static final String                 ERROR_RESPONSE_FUTURE_NULL                 = "02401";
    public static final String                 ERROR_DECODE_REQ_CLASS_CANNOT_FOUND        = "02402";
    public static final String                 ERROR_DECODE_RES_CLASS_CANNOT_FOUND        = "02403";
    public static final String                 ERROR_DECODE_REQ_PROTOCOL_INVALID          = "02404";
    public static final String                 ERROR_DECODE_RES_PROTOCOL_INVALID          = "02405";
    public static final String                 ERROR_DECODE_CLASS_NOT_FOUND               = "02406";
    public static final String                 ERROR_PROVIDER_TR_POOL_REJECTION           = "02407";
    public static final String                 ERROR_PROVIDER_TR_POOL_FULL                = "02408";
    public static final String                 ERROR_PROVIDER_TR_START                    = "02409";
    public static final String                 ERROR_PROVIDER_TR_STOP                     = "02410";
    public static final String                 ERROR_PROVIDER_SERVICE_CANNOT_FOUND        = "02411";
    public static final String                 ERROR_PROVIDER_SERVICE_METHOD_CANNOT_FOUND = "02412";
    public static final String                 ERROR_PROVIDER_PROCESS                     = "02413";
    public static final String                 ERROR_INIT_METHOD_SPECIAL                  = "02414";
    public static final String                 ERROR_DECODE_REQ_SIG_CLASS_NOT_FOUND       = "02415";
    public static final String                 LOCALFILEREGISTRY_FAIL_WRITEFILE           = "02416";
    public static final String                 LOCALFILEREGISTRY_FAIL_READFILE            = "02417";
    public static final String                 LOCALFILEREGISTRY_FAIL_READURL             = "02418";
    public static final String                 LOCALFILEREGISTRY_FAIL_WRITECONFLICT       = "02419";
    public static final String                 LOCALFILEREGISTRY_FAIL_INVOKE              = "02420";
    public static final String                 ERROR_RESPONSE_FUTURE_NOT_CLEAR            = "02421";
    public static final String                 ERROR_DISCARD_TIMEOUT_REQUEST              = "02422";
    public static final String                 ERROR_DISCARD_TIMEOUT_RESPONSE             = "02423";
    public static final String                 ERROR_PROXY_UNCOSUME_FAIL                  = "02424";
    public static final String                 ERROR_GUICE_MODULE_CANNOT_FOUND            = "04001";
    public static final String                 ERROR_SOFA_FRAMEWORK_INVALID               = "04002";
    public static final String                 ERROR_RPC_LOG_LOAD                         = "04003";
    public static final String                 ERROR_RPC_CONFIG_LOAD                      = "04004";
    public static final String                 ERROR_RPC_NETWORK_ADDRESS_LOAD             = "04005";
    public static final String                 ERROR_APPLICATION_CONTEXT_NULL             = "04006";
    public static final String                 ERROR_RPC_EVENT_HANDLE_ERROR               = "04007";
    public static final String                 ERROR_SERVICE_PUBLISHING                   = "04101";
    public static final String                 ERROR_SERVICE_UNPUBLISHING                 = "04102";
    public static final String                 ERROR_OSGI_BUNDLECONTEXT_NULL              = "04103";
    public static final String                 ERROR_OSGI_RESGISTER_SERVICE               = "04104";
    public static final String                 ERROR_OSGI_UNRESGISTER_SERVICE             = "04105";
    public static final String                 ERROR_ADDRESSING_CHAIN_EMPTY               = "04201";
    public static final String                 ERROR_ROUTE_ADDRESS_HANDLER_NULL           = "04202";
    public static final String                 ERROR_ROUTE_ADDRESS_HANDLER_REGISTERED     = "04203";
    public static final String                 ERROR_ROUTE_ADDRESS_HANDLERS_NULL          = "04204";
    public static final String                 ERROR_ROUTE_ADDRESS_PHASE_EXIST            = "04205";
    public static final String                 ERROR_ROUTE_ADDRESS_SUBTOKEN_DECRY         = "04206";
    public static final String                 ERROR_METRIC_REPORT_ERROR                  = "04207";
    public static final String                 ERROR_CE_SERVER_STARTED_LISTENER_ERROR     = "04901";

    public static final String                 WARN_PROCESS_ADDRESS_WAIT                  = "03101";
    public static final String                 WARN_PROCESS_ADDRESS_WAIT_CONTINUE         = "03102";
    public static final String                 WARN_PROCESS_PARSE_TARGET_METHOD           = "03103";
    public static final String                 WARN_PROVIDER_CUT_CAUSE                    = "03401";
    public static final String                 WARN_PROVIDER_STOPPED                      = "03402";
    public static final String                 WARN_BINDING_ADDRESS_WAIT_TIME             = "01101";
    public static final String                 WARN_CONSUMER_NOT_PERMIT                   = "01102";
    public static final String                 WARN_SUCCESS_BY_RETRY                      = "01201";
    public static final String                 WARN_DESERIALIZE_HEADER_ERROR              = "01401";

    public static final String                 INFO_ACTIVATOR_START                       = "00001";
    public static final String                 INFO_ACTIVATOR_END                         = "00002";
    public static final String                 INFO_GET_CONFIG_DEFAULT_APP                = "00004";
    public static final String                 INFO_GET_CONFIG_PROPERTY                   = "00005";
    public static final String                 INFO_TRANSMIT_INIT_FINISH                  = "00101";
    public static final String                 INFO_TRANSMIT_URLS_HANDLE                  = "00102";
    public static final String                 INFO_ADDRESS_WAIT_START                    = "00103";
    public static final String                 INFO_ADDRESS_WAIT_OVER                     = "00104";
    public static final String                 INFO_ROUTE_REGISTRY_PUB                    = "00201";
    public static final String                 INFO_ROUTE_REGISTRY_SUB                    = "00202";
    public static final String                 INFO_ROUTE_REGISTRY_UNPUB                  = "00203";
    public static final String                 INFO_ROUTE_REGISTRY_URLS_HANDLE            = "00204";
    public static final String                 INFO_ROUTE_REGISTRY_PUB_START              = "00205";
    public static final String                 INFO_ROUTE_REGISTRY_PUB_OVER               = "00206";
    public static final String                 LOCALFILEREGISTRY_WRITE_FILEOVER           = "00207";
    public static final String                 INFO_REGISTRY_IGNORE                       = "00208";
    public static final String                 INFO_CONNECT_PUT_TO_ALIVE                  = "00209";
    public static final String                 INFO_CONNECT_PUT_TO_RETRY                  = "00210";
    public static final String                 INFO_CONNECT_RETRY_START                   = "00211";
    public static final String                 INFO_CONNECT_RETRY_SUCCES                  = "00212";
    public static final String                 INFO_CONNECT_RETRY_REMOVE                  = "00213";
    public static final String                 INFO_CONNECT_ALIVE_REMOVE                  = "00214";
    public static final String                 INFO_NEGOTIATION_RESULT                    = "00215";
    public static final String                 INFO_REGULATION_ABNORMAL                   = "00216";
    public static final String                 INFO_REGULATION_ABNORMAL_NOT_DEGRADE       = "00217";
    public static final String                 INFO_ROUTE_REGISTRY_UNSUB                  = "00218";

    public static final String                 INFO_PROCESS_PROFILER_CLIENT_INVOKE        = "00301";
    public static final String                 INFO_PROCESS_PROVIDER_TR_IN                = "00302";
    public static final String                 INFO_PROCESS_PROVIDER_TR_OUT               = "00303";
    public static final String                 INFO_SERVICE_METADATA_IS_NULL              = "00304";

    public static final String                 ERROR_PROVIDER_GRPC_START                  = "05001";

    static {
        init("logcodes-common");
    }

    /**
     * �?始化 Log Codes
     * @param filename 用户�??
     */
    public static void init(String filename) {
        ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader newClassLoader = LogCodes.class.getClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(newClassLoader);
            // 由于 ConfigUtil 类是在 sofa-rpc-api 工程里的，core �?赖了 log
            // 所以�?能直接使用 ConfigUtil，以�?导致循环�?赖
            // 故直接获�?�环境�?��?
            String encoding = Locale.getDefault().toString();
            if (encoding == null || encoding.length() == 0) {
                encoding = Locale.ENGLISH.toString();
            }
            String name = "sofa-rpc/" + filename + "_" + encoding + ".properties";
            // 如果没有找到文件，默认读�?� $filename_en.properties
            if (LogCodes.class.getClassLoader().getResource(name) == null) {
                name = "sofa-rpc/" + filename + "_" + Locale.ENGLISH.toString() + ".properties";
            }
            InputStreamReader reader = null;
            InputStream in = null;
            try {
                Properties properties = new Properties();
                in = LogCodes.class.getClassLoader().getResourceAsStream(name);
                reader = new InputStreamReader(in, "UTF-8");
                properties.load(reader);
                for (Map.Entry entry : properties.entrySet()) {
                    LOG_CODES.put((String) entry.getKey(), (String) entry.getValue());
                }
                NOTE = LOG_CODES.get(NOTE_CODE) == null ? "" : LOG_CODES.get(NOTE_CODE);
            } catch (Exception e) {
                LOGGER.error("�?始化日志�?失败：" + name, e);
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(reader);
            }
        } finally {
            Thread.currentThread().setContextClassLoader(oldClassLoader);
        }

    }

    /**
     * 该方法�?应该由日志输出类直接使用，RPC 的所有的日志输出�?�应该使用 {@link Logger} 类
     *
     * 仅当需�?设置 Exception 的�??示消�?�的时候�?�?�使用该方法
     *
     * @param code 日志�?
     * @return 日志内容
     */
    public static String getLog(String code) {
        if (!LOG_CODES.containsKey(code)) {
            throw new LogCodeNotFoundException(code);
        }
        try {
            return String.format(LOG, code, LOG_CODES.get(code), LogCodes.NOTE);
        } catch (Throwable e) {
            throw new LogFormatException(code);
        }
    }

    /**
     * 当输入为日志�?的时候，输出日志�?对应的日志内容
     * �?�则直接输出日志内容
     *
     * @param codeOrMsg 日志�?或日志输出
     * @return 基本日志输出，�?包�?�日志�? 
     */
    public static String getLiteLog(String codeOrMsg) {
        if (!LOG_CODES.containsKey(codeOrMsg)) {
            return codeOrMsg;
        }
        try {
            return LOG_CODES.get(codeOrMsg);
        } catch (Throwable e) {
            throw new LogFormatException(codeOrMsg);
        }
    }

    public static String getLog(String code, Object... messages) {
        String message = LOG_CODES.get(code);

        if (message == null) {
            throw new LogCodeNotFoundException(code);
        }

        try {
            return String.format(LOG, code, MessageFormat.format(message, messages), LogCodes.NOTE);
        } catch (Throwable e) {
            throw new LogFormatException(code);
        }
    }

    /**
     * 当输入为日志�?的时候，输出日志�?对应的日志内容
     * �?�则直接输出日志内容
     *
     * @param codeOrMsg 日志�?或日志输出
     * @return 基本日志输出，�?包�?�日志�?
     */
    public static String getLiteLog(String codeOrMsg, Object... messages) {
        String message = LOG_CODES.get(codeOrMsg);

        if (message == null) {
            return MessageFormat.format(codeOrMsg, messages);
        }

        try {
            return MessageFormat.format(message, messages);
        } catch (Throwable e) {
            throw new LogFormatException(codeOrMsg);
        }
    }
}
