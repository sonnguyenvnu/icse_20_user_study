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
package com.alipay.sofa.rpc.codec.bolt;

import com.alipay.remoting.exception.DeserializationException;
import com.alipay.remoting.exception.SerializationException;
import com.alipay.sofa.rpc.codec.common.StringSerializer;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.struct.UnsafeByteArrayInputStream;
import com.alipay.sofa.rpc.common.struct.UnsafeByteArrayOutputStream;
import com.alipay.sofa.rpc.common.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Common serializer of map (this generally refers to header).
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 * @author <a href=mailto:hongwei.yhw@antfin.com>HongWei Yi</a>
 */
public class SimpleMapSerializer {

    /**
     * 简�?� map 的�?列化过程, 用�?��?列化 bolt 的 header
     *
     * @param map bolt header
     * @return �?列化�?�的 byte 数组
     * @throws SerializationException SerializationException
     */
    public byte[] encode(Map<String, String> map) throws SerializationException {
        if (map == null || map.isEmpty()) {
            return null;
        }
        UnsafeByteArrayOutputStream out = new UnsafeByteArrayOutputStream(64);
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                /**
                 * 排除�?写null作为key
                 */
                if (key != null) {
                    writeSupportEmpty(key, out);
                    writeSupportEmpty(value, out);
                }
            }
            return out.toByteArray();
        } catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    /**
     * 支�?empty字符串的�?列化
     *
     * @param data 输入数�?�
     * @param out 输入�?
     * @throws IOException 写入异常
     */
    public void writeSupportEmpty(String data, OutputStream out) throws IOException {
        if (StringUtils.isEmpty(data)) {
            writeInt(out, 0);
        } else {
            byte[] bs = data.getBytes(RpcConstants.DEFAULT_CHARSET);
            writeInt(out, bs.length);
            out.write(bs);
        }
    }

    /**
     * 简�?� map 的�??�?列化过程, 用�?��??�?列化 bolt 的 header
     * <p>
     * {@link SofaRpcSerialization#deserializeHeader(com.alipay.remoting.rpc.RequestCommand)}
     *
     * @param bytes bolt header
     * @return �??�?列化�?�的 Map 对象
     * @throws DeserializationException DeserializationException
     */
    public Map<String, String> decode(byte[] bytes) throws DeserializationException {
        Map<String, String> map = new HashMap<String, String>();
        if (bytes == null || bytes.length == 0) {
            return map;
        }

        UnsafeByteArrayInputStream in = new UnsafeByteArrayInputStream(bytes);
        try {
            while (in.available() > 0) {
                int length = readInt(in);
                byte[] key = new byte[length];
                in.read(key);

                length = readInt(in);
                byte[] value = new byte[length];
                in.read(value);

                Charset charset = RpcConstants.DEFAULT_CHARSET;
                map.put(new String(key, charset), new String(value, charset));
            }

            return map;
        } catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }

    /**
     * 写一个String
     *
     * @param out 输出�?
     * @param str 字符串
     * @throws IOException 写入异常
     */
    protected void writeString(OutputStream out, String str) throws IOException {
        if (str == null) {
            writeInt(out, -1);
        } else if (str.isEmpty()) {
            writeInt(out, 0);
        } else {
            byte[] bs = StringSerializer.encode(str);
            writeInt(out, bs.length);
            out.write(bs);
        }
    }

    /**
     * 读�?�一个字符串
     *
     * @param in 输入�?程
     * @return 字符串
     * @throws IOException 读�?�异常
     */
    protected String readString(InputStream in) throws IOException {
        int length = readInt(in);
        if (length < 0) {
            return null;
        } else if (length == 0) {
            return StringUtils.EMPTY;
        } else {
            byte[] value = new byte[length];
            in.read(value);
            return StringSerializer.decode(value);
        }
    }

    /**
     * OutputStream.write(int) 仅 write 第一个 byte, 而�?是整个 int
     *
     * @param out OutputStream
     * @param i int value
     * @throws IOException if an I/O error occurs.
     */
    private void writeInt(OutputStream out, int i) throws IOException {
        out.write((byte) (i >> 24));
        out.write((byte) (i >> 16));
        out.write((byte) (i >> 8));
        out.write(i);
    }

    /**
     * InputStream.read 仅 read 一个 byte
     *
     * @param in InputStream
     * @return int value
     * @throws IOException if an I/O error occurs.
     */
    public int readInt(InputStream in) throws IOException {
        return ((byte) in.read() & 0xff) << 24
            | ((byte) in.read() & 0xff) << 16
            | ((byte) in.read() & 0xff) << 8
            | (byte) in.read() & 0xff;
    }
}
