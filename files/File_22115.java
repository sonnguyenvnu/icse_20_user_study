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

package io.elasticjob.lite.internal.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import io.elasticjob.lite.util.env.IpUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * æ•?æ„Ÿä¿¡æ?¯è¿‡æ»¤å·¥å…·ç±».
 * 
 * @author caohao
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SensitiveInfoUtils {
    
    private static final String FAKE_IP_SAMPLE = "ip";
    
    /**
     * å±?è”½æ›¿æ?¢IPåœ°å?€æ•?æ„Ÿä¿¡æ?¯.
     * 
     * @param target å¾…æ›¿æ?¢æ•?æ„Ÿä¿¡æ?¯çš„å­—ç¬¦ä¸²åˆ—è¡¨
     * @return æ›¿æ?¢æ•?æ„Ÿä¿¡æ?¯å?Žçš„å­—ç¬¦ä¸²åˆ—è¡¨
     */
    public static List<String> filterSensitiveIps(final List<String> target) {
        final Map<String, String> fakeIpMap = new HashMap<>();
        final AtomicInteger step = new AtomicInteger();
        return Lists.transform(target, new Function<String, String>() {
            
            @Override
            public String apply(final String input) {
                Matcher matcher = Pattern.compile(IpUtils.IP_REGEX).matcher(input);
                String result = input;
                while (matcher.find()) {
                    String realIp = matcher.group();
                    String fakeIp;
                    if (fakeIpMap.containsKey(realIp)) {
                        fakeIp = fakeIpMap.get(realIp);
                    } else {
                        fakeIp = Joiner.on("").join(FAKE_IP_SAMPLE, step.incrementAndGet());
                        fakeIpMap.put(realIp, fakeIp);
                    }
                    result = result.replace(realIp, fakeIp);
                }
                return result;
            }
        });
    }
}
