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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Internal JSON
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class JSON {

    public static final String CLASS_KEY = "@type";

    /**
     * 对象转为json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String toJSONString(Object object) {
        return JSONSerializer.serialize(object);
    }

    /**
     * �?列化json基本类型（自定义对象需�?先转�?��?Map）
     *
     * @param object  需�?�?列化的对象
     * @param addType 是�?�增加自定义对象标记
     * @return Json格�?字符串
     */
    public static String toJSONString(Object object, boolean addType) {
        return JSONSerializer.serialize(object, addType);
    }

    /**
     * 解�?为指定对象
     *
     * @param text  json字符串
     * @param clazz 指定类
     * @param <T>   指定对象
     * @return 指定对象
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        Object obj = JSONSerializer.deserialize(text);
        return BeanSerializer.deserializeByType(obj, clazz);
    }

    /**
     * 获�?�需�?�?列化的字段，跳过
     *
     * @param targetClass 目标类
     * @return Field list
     */
    protected static List<Field> getSerializeFields(Class targetClass) {
        List<Field> all = new ArrayList<Field>();
        for (Class<?> c = targetClass; c != Object.class && c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();

            for (Field f : fields) {
                int mod = f.getModifiers();
                // transient, static,  @JSONIgnore : skip
                if (Modifier.isStatic(mod) || Modifier.isTransient(mod)) {
                    continue;
                }
                JSONIgnore ignore = f.getAnnotation(JSONIgnore.class);
                if (ignore != null) {
                    continue;
                }

                f.setAccessible(true);
                all.add(f);
            }
        }
        return all;
    }
}
