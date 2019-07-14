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

package io.elasticjob.lite.console.util;

import io.elasticjob.lite.console.domain.EventTraceDataSourceConfiguration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®çš„ä¼šè¯?å£°æ˜Žå‘¨æœŸ.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionEventTraceDataSourceConfiguration {
    
    private static EventTraceDataSourceConfiguration eventTraceDataSourceConfiguration;
    
    /**
     * ä»Žå½“å‰?ä¼šè¯?èŒƒå›´èŽ·å?–äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®.
     * 
     * @return äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     */
    public static EventTraceDataSourceConfiguration getEventTraceDataSourceConfiguration() {
        return eventTraceDataSourceConfiguration;
    }
    
    /**
     * è®¾ç½®äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®è‡³å½“å‰?ä¼šè¯?èŒƒå›´.
     *
     * @param eventTraceDataSourceConfiguration äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     */
    public static void setDataSourceConfiguration(final EventTraceDataSourceConfiguration eventTraceDataSourceConfiguration) {
        SessionEventTraceDataSourceConfiguration.eventTraceDataSourceConfiguration = eventTraceDataSourceConfiguration;
    }
}
