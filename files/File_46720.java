package com.codingapi.txlcn.tracing;

import com.codingapi.txlcn.common.util.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Description: DTX Tracing å·¥å…·
 * Date: 19-2-11 ä¸‹å?ˆ1:02
 *
 * @author ujued
 */
@Slf4j
public class Tracings {

    /**
     * ç§?æœ‰æž„é€ å™¨
     */
    private Tracings() {
    }

    /**
     * ä¼ è¾“Tracingä¿¡æ?¯
     *
     * @param tracingSetter Tracingä¿¡æ?¯è®¾ç½®å™¨
     */
    public static void transmit(TracingSetter tracingSetter) {
        if (TracingContext.tracing().hasGroup()) {
            log.debug("tracing transmit group:{}", TracingContext.tracing().groupId());
            tracingSetter.set(TracingConstants.HEADER_KEY_GROUP_ID, TracingContext.tracing().groupId());
            tracingSetter.set(TracingConstants.HEADER_KEY_APP_MAP,
                    Base64Utils.encodeToString(TracingContext.tracing().appMapString().getBytes(StandardCharsets.UTF_8)));
        }
    }

    /**
     * èŽ·å?–ä¼ è¾“çš„Tracingä¿¡æ?¯
     *
     * @param tracingGetter Tracingä¿¡æ?¯èŽ·å?–å™¨
     */
    public static void apply(TracingGetter tracingGetter) {
        String groupId = Optional.ofNullable(tracingGetter.get(TracingConstants.HEADER_KEY_GROUP_ID)).orElse("");
        String appList = Optional.ofNullable(tracingGetter.get(TracingConstants.HEADER_KEY_APP_MAP)).orElse("");
        TracingContext.init(Maps.newHashMap(TracingConstants.GROUP_ID, groupId, TracingConstants.APP_MAP,
                StringUtils.isEmpty(appList) ? appList : new String(Base64Utils.decodeFromString(appList), StandardCharsets.UTF_8)));
        if (TracingContext.tracing().hasGroup()) {
            log.debug("tracing apply group:{}, app map:{}", groupId, appList);
        }
    }

    /**
     * Tracingä¿¡æ?¯è®¾ç½®å™¨
     */
    public interface TracingSetter {
        /**
         * è®¾ç½®tracingå±žæ€§
         *
         * @param key   key
         * @param value value
         */
        void set(String key, String value);
    }

    /**
     * Tracingä¿¡æ?¯èŽ·å?–å™¨
     */
    public interface TracingGetter {
        /**
         * èŽ·å?–tracingå±žæ€§
         *
         * @param key key
         * @return tracing value
         */
        String get(String key);
    }
}
