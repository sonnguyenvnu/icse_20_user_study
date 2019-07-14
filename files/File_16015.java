/*
 *
 *  * Copyright 2019 http://www.hswebframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.hswebframework.web;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ThreadLocal 工具类,通过在ThreadLocal存储map信�?�,�?�实现在ThreadLocal中维护多个信�?�
 * <br>e.g.<code>
 * ThreadLocalUtils.put("key",value);<br>
 * ThreadLocalUtils.get("key");<br>
 * ThreadLocalUtils.remove("key");<br>
 * ThreadLocalUtils.getAndRemove("key");<br>
 * ThreadLocalUtils.get("key",()-&gt;defaultValue);<br>
 * ThreadLocalUtils.clear();<br>
 * </code>
 *
 * @author zhouhao
 * @since 2.0
 */
@SuppressWarnings("unchecked")
public final class ThreadLocalUtils {

    private ThreadLocalUtils() {
    }

    private static final ThreadLocal<Map<String, Object>> local = ThreadLocal.withInitial(HashMap::new);

    /**
     * @return threadLocal中的全部值
     */
    public static Map<String, Object> getAll() {
        return local.get();
    }

    /**
     * 设置一个值到ThreadLocal
     *
     * @param key   键
     * @param value 值
     * @param <T>   值的类型
     * @return 被放入的值
     * @see Map#put(Object, Object)
     */
    public static <T> T put(String key, T value) {
        local.get().put(key, value);
        return value;
    }

    /**
     * 删除�?�数对应的值
     *
     * @param key
     * @see Map#remove(Object)
     */
    public static void remove(String key) {
        local.get().remove(key);
    }

    /**
     * 清空ThreadLocal
     *
     * @see Map#clear()
     */
    public static void clear() {
        local.remove();
    }

    /**
     * 从ThreadLocal中获�?�值
     *
     * @param key 键
     * @param <T> 值泛型
     * @return 值, �?存在则返回null, 如果类型与泛型�?一致, �?�能抛出{@link ClassCastException}
     * @see Map#get(Object)
     * @see ClassCastException
     */
    public static <T> T get(String key) {
        return ((T) local.get().get(key));
    }

    /**
     * 从ThreadLocal中获�?�值,并指定一个当值�?存在的�??供者
     *
     * @see Supplier
     * @since 3.0
     */
    public static <T> T get(String key, Supplier<T> supplierOnNull) {
        return ((T) local.get().computeIfAbsent(key, k -> supplierOnNull.get()));
    }

    /**
     * 获�?�一个值�?�然�?�删除掉
     *
     * @param key 键
     * @param <T> 值类型
     * @return 值, �?存在则返回null
     * @see this#get(String)
     * @see this#remove(String)
     */
    public static <T> T getAndRemove(String key) {
        try {
            return get(key);
        } finally {
            remove(key);
        }
    }

}
