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
package com.alipay.sofa.rpc.protocol;

import com.alipay.sofa.rpc.common.RpcConfigs;
import com.alipay.sofa.rpc.common.RpcOptions;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.ext.ExtensionClass;
import com.alipay.sofa.rpc.ext.ExtensionLoader;
import com.alipay.sofa.rpc.ext.ExtensionLoaderFactory;
import com.alipay.sofa.rpc.ext.ExtensionLoaderListener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Factory of protocol
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ProtocolFactory {

    /**
     * 除了托管给扩展加载器的工厂模�?（�?留alias：实例）外<br>
     * 还需�?�?外�?留编�?和实例的映射：{编�?：�??议}
     */
    private final static ConcurrentMap<Byte, Protocol> TYPE_PROTOCOL_MAP = new ConcurrentHashMap<Byte, Protocol>();

    /**
     * 除了托管给扩展加载器的工厂模�?（�?留alias：实例）外<br>
     * 还需�?�?外�?留编�?和实例的映射：{别�??：编�?}
     */
    private final static ConcurrentMap<String, Byte>   TYPE_CODE_MAP     = new ConcurrentHashMap<String, Byte>();

    /**
     * 扩展加载器
     */
    private final static ExtensionLoader<Protocol>     EXTENSION_LOADER  = buildLoader();

    private static ExtensionLoader<Protocol> buildLoader() {
        return ExtensionLoaderFactory.getExtensionLoader(Protocol.class, new ExtensionLoaderListener<Protocol>() {
            @Override
            public void onLoad(ExtensionClass<Protocol> extensionClass) {
                // 除了�?留 alias：Protocol外， 需�?�?留 code：Protocol
                Protocol protocol = extensionClass
                    .getExtInstance();
                TYPE_PROTOCOL_MAP.put(extensionClass.getCode(), protocol);
                TYPE_CODE_MAP.put(extensionClass.getAlias(), extensionClass.getCode());
                if (RpcConfigs.getBooleanValue(RpcOptions.TRANSPORT_SERVER_PROTOCOL_ADAPTIVE)) {
                    maxMagicOffset = 2;
                    registerAdaptiveProtocol(protocol.protocolInfo());
                }
            }
        });
    }

    /**
     * 按�??议�??称返回�??议对象
     *
     * @param alias �??议�??称
     * @return �??议对象
     */
    public static Protocol getProtocol(String alias) {
        // 工厂模�?  托管给ExtensionLoader
        return EXTENSION_LOADER.getExtension(alias);
    }

    /**
     * 按�??议编�?�返回�??议对象
     *
     * @param code �??议编�?
     * @return �??议对象
     */
    public static Protocol getProtocol(byte code) {
        Protocol protocol = TYPE_PROTOCOL_MAP.get(code);
        if (protocol == null) {
            throw new SofaRpcRuntimeException("Extension Not Found :\"" + code + "\"!");
        }
        return protocol;
    }

    /**
     * 通过别�??获�?��??议编�?
     *
     * @param protocol �??议的�??字
     * @return �??议编�?
     */
    public static Byte getCodeByAlias(String protocol) {
        return TYPE_CODE_MAP.get(protocol);
    }

    /**
     * 根�?�头部�?几个魔术�?，判断是哪�?�??议的长连接
     *
     * @param magicHeadBytes 头部魔术�?
     * @return �??议
     */
    public static Protocol adaptiveProtocol(byte[] magicHeadBytes) {
        for (Protocol protocol : TYPE_PROTOCOL_MAP.values()) {
            if (protocol.protocolInfo().isMatchMagic(magicHeadBytes)) {
                return protocol;
            }
        }
        return null;
    }

    /**
     * 最大�??移�?，用于一个端�?�支�?多�??议时使用
     */
    private static int maxMagicOffset;

    /**
     * 注册�??议到适�?�??议
     *
     * @param protocolInfo �??议�??述信�?�
     */
    protected static synchronized void registerAdaptiveProtocol(ProtocolInfo protocolInfo) {
        // �?�最大�??移�?
        maxMagicOffset = Math.max(maxMagicOffset, protocolInfo.magicFieldOffset() + protocolInfo.magicFieldLength());
    }

    /**
     * 得到最大�??移�?
     *
     * @return 最大�??移�?
     */
    public static int getMaxMagicOffset() {
        return maxMagicOffset;
    }

}
