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
package com.alipay.sofa.rpc.codec;

import com.alipay.sofa.rpc.common.struct.TwoWayMap;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.ext.ExtensionClass;
import com.alipay.sofa.rpc.ext.ExtensionLoader;
import com.alipay.sofa.rpc.ext.ExtensionLoaderFactory;
import com.alipay.sofa.rpc.ext.ExtensionLoaderListener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * �?列化工厂
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public final class SerializerFactory {

    /**
     * 除了托管给扩展加载器的工厂模�?（�?留alias：实例）外<br>
     * 还需�?�?外�?留编�?和实例的映射：{编�?：�?列化器}
     */
    private final static ConcurrentMap<Byte, Serializer> TYPE_SERIALIZER_MAP = new ConcurrentHashMap<Byte, Serializer>();

    /**
     * 除了托管给扩展加载器的工厂模�?（�?留alias：实例）外，还需�?�?外�?留编�?和实例的映射：{别�??：编�?}
     */
    private final static TwoWayMap<String, Byte>         TYPE_CODE_MAP       = new TwoWayMap<String, Byte>();

    /**
     * 扩展加载器
     */
    private final static ExtensionLoader<Serializer>     EXTENSION_LOADER    = buildLoader();

    private static ExtensionLoader<Serializer> buildLoader() {
        return ExtensionLoaderFactory.getExtensionLoader(Serializer.class,
            new ExtensionLoaderListener<Serializer>() {
                @Override
                public void onLoad(ExtensionClass<Serializer> extensionClass) {
                    // 除了�?留 tag：Serializer外， 需�?�?留 code：Serializer
                    TYPE_SERIALIZER_MAP.put(extensionClass.getCode(), extensionClass.getExtInstance());
                    TYPE_CODE_MAP.put(extensionClass.getAlias(), extensionClass.getCode());
                }
            });
    }

    /**
     * 按�?列化�??称返回�??议对象
     *
     * @param alias �?列化�??称
     * @return �?列化器
     */
    public static Serializer getSerializer(String alias) {
        // 工厂模�?  托管给ExtensionLoader
        return EXTENSION_LOADER.getExtension(alias);
    }

    /**
     * 按�?列化�??称返回�??议对象
     *
     * @param type �?列�?�编�?
     * @return �?列化器
     */
    public static Serializer getSerializer(byte type) {
        Serializer serializer = TYPE_SERIALIZER_MAP.get(type);
        if (serializer == null) {
            throw new SofaRpcRuntimeException("Serializer Not Found :\"" + type + "\"!");
        }
        return serializer;
    }

    /**
     * 通过别�??获�?�Code
     *
     * @param serializer �?列化的�??字
     * @return �?列化编�?
     */
    public static Byte getCodeByAlias(String serializer) {
        return TYPE_CODE_MAP.get(serializer);
    }

    /**
     * 通过Code获�?�别�??
     *
     * @param code �?列化的Code
     * @return �?列化别�??
     */
    public static String getAliasByCode(byte code) {
        return TYPE_CODE_MAP.getKey(code);
    }

}
