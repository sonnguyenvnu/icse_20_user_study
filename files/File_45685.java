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
package com.alipay.sofa.rpc.common.json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>�?�关心JSON格�?的解�?，�?关心java的具体类型</p>
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class JSONSerializer {

    /**
     * �?列化json基本类型（自定义对象需�?先转�?��?Map）
     *
     * @param object 需�?�?列化的对象
     * @return Json格�?字符串
     */
    public static String serialize(Object object) {
        return serialize(object, false);
    }

    /**
     * �?列化json基本类型（自定义对象需�?先转�?��?Map）
     *
     * @param object  需�?�?列化的对象
     * @param addType 是�?�显示增加类型
     * @return Json格�?字符串
     */
    public static String serialize(Object object, boolean addType) {
        if (object == null) {
            return "null";
        } else if (object instanceof CharSequence || object instanceof Character) { //TODO 去除特殊字符
            String tmp = object.toString();
            return '\"' + tmp.replace("\"", "\\\"").replace("\b", "\\b")
                .replace("\t", "\\t").replace("\r", "\\r")
                .replace("\f", "\\f").replace("\n", "\\n") + '\"';
        } else if (object instanceof Number || object instanceof Boolean) {
            return object.toString();
        } else if (object instanceof Map) {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            Map map = (Map) object;
            Iterator itr = map.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry entry = (Map.Entry) itr.next();
                sb.append(serialize(entry.getKey(), addType)).append(':').append(serialize(entry.getValue(), addType))
                    .append(',');
            }
            int last = sb.length() - 1;
            if (sb.charAt(last) == ',') {
                sb.deleteCharAt(last);
            }
            sb.append('}');
            return sb.toString();
        } else if (object instanceof Collection) {
            return serialize(((Collection) object).toArray(), addType);
        } else if (object.getClass().isArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            int last = Array.getLength(object) - 1;
            for (int i = 0; i <= last; ++i) {
                Object value = Array.get(object, i);
                sb.append(serialize(value, addType)).append(',');
            }
            last = sb.length() - 1;
            if (sb.charAt(last) == ',') {
                sb.deleteCharAt(last);
            }
            sb.append(']');
            return sb.toString();
        } else {
            //throw new IllegalArgumentException("Unsupported type " + object.getClass().getName() + ":" + object.toString());
            // 自定义对象，先转�?map等
            return serialize(BeanSerializer.serialize(object, addType), addType);
        }
    }

    /**
     * �??�?列化json转对象 (�?�返回JSON的标准类型：String，Number，True/False/Null，Map，Array)
     *
     * @param json json字符串
     * @return 转�?��?�的对象
     * @throws ParseException 解�?异常
     */
    public static Object deserialize(String json) throws ParseException {
        // 去掉注释
        return new JSONSerializer(json).nextValue();
    }

    private int          position;
    private final char[] buffer;

    protected JSONSerializer(String string) {
        this.buffer = string.toCharArray();
        this.position = -1;
    }

    /**
     * �?�返回JSON的标准类型：String，Number，True/False/Null，Map，Array
     *
     * @return 标准json类型对象
     * @throws ParseException 解�?出现异常
     */
    protected Object nextValue() throws ParseException {
        try {
            char c = this.nextToken();
            switch (c) {
                case '{':
                    try {
                        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                        if (nextToken() != '}') {
                            --position;
                            while (true) {
                                String key = nextValue().toString();
                                if (nextToken() != ':') {
                                    throw new ParseException(new String(this.buffer), this.position,
                                        "Expected a ':' after a key");
                                }
                                map.put(key, nextValue());
                                switch (nextToken()) {
                                    case ';':
                                    case ',':
                                        if (nextToken() == '}') {
                                            return map;
                                        }
                                        --position;
                                        break;
                                    case '}':
                                        return map;
                                    default:
                                        throw new ParseException(new String(this.buffer), this.position,
                                            "Expected a ',' or '}'");
                                }
                            }
                        } else {
                            return map;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                        throw new ParseException(new String(this.buffer), this.position, "Expected a ',' or '}'");
                    }

                case '[':
                    try {
                        ArrayList<Object> list = new ArrayList<Object>();
                        if (nextToken() != ']') {
                            --position;
                            while (true) {
                                if (nextToken() == ',') {
                                    --position;
                                    list.add(null);
                                } else {
                                    --position;
                                    list.add(nextValue());
                                }
                                switch (nextToken()) {
                                    case ',':
                                        if (nextToken() == ']') {
                                            return list;
                                        }
                                        --position;
                                        break;
                                    case ']':
                                        return list;
                                    default:
                                        throw new ParseException(new String(this.buffer), this.position,
                                            "Expected a ',' or ']'");
                                }
                            }
                        } else {
                            return list;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                        throw new ParseException(new String(this.buffer), this.position, "Expected a ',' or ']'");
                    }

                case '"': // �?�引�?��?�引�?�
                case '\'':
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        char ch = this.buffer[++position];
                        switch (ch) {
                            case '\n':
                            case '\r':
                                throw new ParseException(new String(this.buffer), this.position, "Unterminated string");
                            case '\\': // 读到�??斜�?� 读�?�下一个�?置
                                ch = this.buffer[++position];
                                switch (ch) {
                                    case 'b':
                                        sb.append('\b');
                                        break;
                                    case 't':
                                        sb.append('\t');
                                        break;
                                    case 'n':
                                        sb.append('\n');
                                        break;
                                    case 'f':
                                        sb.append('\f');
                                        break;
                                    case 'r':
                                        sb.append('\r');
                                        break;
                                    case 'u': // unicode
                                        int num = 0;
                                        for (int i = 3; i >= 0; --i) {
                                            int tmp = buffer[++position];
                                            if (tmp <= '9' && tmp >= '0') {
                                                tmp = tmp - '0';
                                            } else if (tmp <= 'F' && tmp >= 'A') {
                                                tmp = tmp - ('A' - 10);
                                            } else if (tmp <= 'f' && tmp >= 'a') {
                                                tmp = tmp - ('a' - 10);
                                            } else {
                                                throw new ParseException(new String(this.buffer), this.position,
                                                    "Illegal hex code");
                                            }
                                            num += tmp << (i * 4);
                                        }
                                        sb.append((char) num);
                                        break;
                                    case '"':
                                    case '\'':
                                    case '\\':
                                    case '/':
                                        sb.append(ch);
                                        break;
                                    default:
                                        throw new ParseException(new String(this.buffer), this.position,
                                            "Illegal escape.");
                                }
                                break;
                            default:
                                if (ch == c) {
                                    return sb.toString();
                                }
                                sb.append(ch);
                        }
                    }
            }

            int startPosition = this.position;
            while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
                c = this.buffer[++position];
            }
            String substr = new String(buffer, startPosition, position-- - startPosition);
            if ("true".equalsIgnoreCase(substr)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(substr)) {
                return Boolean.FALSE;
            }
            if ("null".equalsIgnoreCase(substr)) {
                return null;
            }

            char b = "-+".indexOf(substr.charAt(0)) < 0 ? substr.charAt(0) : substr.charAt(1);
            if (b >= '0' && b <= '9') {
                try {
                    Long l = Long.valueOf(substr.trim());
                    if (l.intValue() == l) {
                        return l.intValue();
                    }
                    return l;
                } catch (NumberFormatException exInt) {
                    try {
                        return new Double(substr.trim());
                    } catch (NumberFormatException ignore) { // NOPMD
                    }
                }
            }
            return substr;
        } catch (ArrayIndexOutOfBoundsException ignore) {
            throw new ParseException(new String(this.buffer), this.position, "Unexpected end");
        }
    }

    private char nextToken() throws ArrayIndexOutOfBoundsException {
        char char1;
        while ((char1 = this.buffer[++position]) <= ' ' || char1 == '/') {
            switch (char1) {
                case '/': //注释开始
                    char ch = this.buffer[++position];
                    switch (ch) {
                        case '/': // �?�行注释
                            while (true) {
                                ch = this.buffer[++position];
                                if (ch == '\n') {
                                    break;
                                }
                            }
                            break;
                        case '*': //多行注释
                            while (true) {
                                ch = this.buffer[++position];
                                if (ch == '*') {
                                    ch = this.buffer[++position];
                                    if (ch == '/') {
                                        break;
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
        return this.buffer[position];
    }
}
