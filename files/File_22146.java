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

package io.elasticjob.lite.util.config;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分片项工具类.
 *
 * @author zhangliang
 */
@Getter
public final class ShardingItems {
    
    private static final String DELIMITER = ",";
    
    /**
     * 根�?�分片项字符串获�?�分片项列表.
     *
     * @param itemsString 分片项字符串
     * @return 分片项列表
     */
    public static List<Integer> toItemList(final String itemsString) {
        if (Strings.isNullOrEmpty(itemsString)) {
            return Collections.emptyList();
        }
        String[] items = itemsString.split(DELIMITER);
        List<Integer> result = new ArrayList<>(items.length);
        for (String each : items) {
            int item = Integer.parseInt(each);
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    /**
     * 根�?�分片项列表获�?�分片项字符串.
     *
     * @param items 分片项列表
     * @return 分片项字符串
     */
    public static String toItemsString(final List<Integer> items) {
        return items.isEmpty() ? "" : Joiner.on(DELIMITER).join(items);
    }
}
