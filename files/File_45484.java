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

import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.ext.ExtensionClass;
import com.alipay.sofa.rpc.ext.ExtensionLoader;
import com.alipay.sofa.rpc.ext.ExtensionLoaderFactory;
import com.alipay.sofa.rpc.ext.ExtensionLoaderListener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Factory of Compressor
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public final class CompressorFactory {

    /**
     * 除了托管给扩展加载器的工厂模�?（�?留alias：实例）外<br>
     * 还需�?�?外�?留编�?和实例的映射：{编�?：压缩器}
     */
    private final static ConcurrentMap<Byte, Compressor> TYPE_COMPRESSOR_MAP = new ConcurrentHashMap<Byte, Compressor>();
    /**
     * 除了托管给扩展加载器的工厂模�?（�?留alias：实例）外<br>
     * 还需�?�?外�?留编�?和实例的映射：{别�??：编�?}
     */
    private final static ConcurrentMap<String, Byte>     TYPE_CODE_MAP       = new ConcurrentHashMap<String, Byte>();

    /**
     * 扩展加载器
     */
    private final static ExtensionLoader<Compressor>     EXTENSION_LOADER    = buildLoader();

    private static ExtensionLoader<Compressor> buildLoader() {
        return ExtensionLoaderFactory.getExtensionLoader(Compressor.class, new ExtensionLoaderListener<Compressor>() {
            @Override
            public void onLoad(ExtensionClass<Compressor> extensionClass) {
                // 除了�?留 tag：Compressor外， 需�?�?留 code：Compressor
                TYPE_COMPRESSOR_MAP.put(extensionClass.getCode(), extensionClass.getExtInstance());
                TYPE_CODE_MAP.put(extensionClass.getAlias(), extensionClass.getCode());
            }
        });
    }

    /**
     * 按压缩算法�??称返回�??议对象
     *
     * @param alias 压缩算法
     * @return Compressor
     */
    public static Compressor getCompressor(String alias) {
        // 工厂模�?  托管给ExtensionLoader
        return EXTENSION_LOADER.getExtension(alias);
    }

    /**
     * 按压缩编�?返回�??议对象
     *
     * @param code Compressor编�?
     * @return Compressor
     */
    public static Compressor getCompressor(byte code) {
        Compressor compressor = TYPE_COMPRESSOR_MAP.get(code);
        if (compressor == null) {
            throw new SofaRpcRuntimeException("Compressor Not Found :\"" + code + "\"!");
        }
        return compressor;
    }

    /**
     * 通过别�??获�?�Code
     *
     * @param compress 压缩�??字
     * @return 压缩编�?
     */
    public static byte getCodeByAlias(String compress) {
        return TYPE_CODE_MAP.get(compress);
    }
}
