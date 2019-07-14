/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.elasticjob.lite.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

/**
 * Gson构建器.
 *
 * @author caohao
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GsonFactory {
    
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    
    private static volatile Gson gson = GSON_BUILDER.create();
    
    /**
     * 注册Gson解�?对象.
     * 
     * @param type Gson解�?对象类型
     * @param typeAdapter Gson解�?对象适�?器
     */
    public static synchronized void registerTypeAdapter(final Type type, final TypeAdapter typeAdapter) {
        GSON_BUILDER.registerTypeAdapter(type, typeAdapter);
        gson = GSON_BUILDER.create();
    }
    
    /**
     * 获�?�Gson实例.
     * 
     * @return Gson实例
     */
    public static Gson getGson() {
        return gson;
    }
}
