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
package com.alipay.sofa.rpc.transport.http;

import com.alipay.sofa.rpc.codec.SerializerFactory;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.core.exception.RpcErrorType;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;

/**
 * Constants of HTTP protocol
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 * @since 5.4.0
 */
public class HttpTransportUtils {

    /**
     * 根�?��?列化�??称获得�?列化类型
     *
     * @param serialization �?列化类型�??称
     * @return �?列化编�?
     */
    public static byte getSerializeTypeByName(String serialization) throws SofaRpcException {
        String sz = serialization.toLowerCase();
        Byte code;
        if (RpcConstants.SERIALIZE_HESSIAN2.equals(sz) || RpcConstants.SERIALIZE_HESSIAN.equals(sz)) {
            code = SerializerFactory.getCodeByAlias(RpcConstants.SERIALIZE_HESSIAN2);
        } else {
            code = SerializerFactory.getCodeByAlias(serialization);
        }
        if (code != null) {
            return code;
        } else {
            throw new SofaRpcException(RpcErrorType.SERVER_DESERIALIZE, "Unsupported serialize type " + serialization
                + " in http protocol.");
        }
    }

    /**
     * Parse serialize type from content type
     *
     * @param contentType Content-type of http request
     * @return serialize code
     * @throws SofaRpcException unknown content type
     */
    public static byte getSerializeTypeByContentType(String contentType) throws SofaRpcException {
        if (StringUtils.isNotBlank(contentType)) {
            String ct = contentType.toLowerCase();
            if (ct.contains("text/plain") || ct.contains("text/html") || ct.contains("application/json")) {
                return getSerializeTypeByName(RpcConstants.SERIALIZE_JSON);
            } else if (ct.contains(RpcConstants.SERIALIZE_PROTOBUF)) {
                return getSerializeTypeByName(RpcConstants.SERIALIZE_PROTOBUF);
            } else if (ct.contains(RpcConstants.SERIALIZE_HESSIAN)) {
                return getSerializeTypeByName(RpcConstants.SERIALIZE_HESSIAN2);
            }
        }
        throw new SofaRpcException(RpcErrorType.SERVER_DESERIALIZE, "Unsupported content type " + contentType
            + " in http protocol, please set HTTP HEAD: '" + RemotingConstants.HEAD_SERIALIZE_TYPE + "'.");
    }

    protected static String[] getInterfaceIdAndMethod(String uri) {
        String[] result;
        int i = uri.indexOf('?');
        if (i > 0) {
            uri = uri.substring(0, i);
        }
        String[] end = uri.split("/");
        if (end.length < 3) {
            throw new SofaRpcException(RpcErrorType.SERVER_DESERIALIZE,
                "The correct URI format is: http://ip:port/serviceName/methodName");
        }
        int resultLength = 2;
        result = new String[resultLength];
        //从第二个元素开始copy 第一个是空字符串
        System.arraycopy(end, 1, result, 0, resultLength);
        return result;
    }
}
