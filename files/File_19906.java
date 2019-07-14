package cc.mrbird.security.service;

import cc.mrbird.security.validate.smscode.SmsCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Redisæ“?ä½œéªŒè¯?ç ?æœ?åŠ¡
 */
@Service
public class RedisCodeService {

    private final static String SMS_CODE_PREFIX = "SMS_CODE:";
    private final static Integer TIME_OUT = 300;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * ä¿?å­˜éªŒè¯?ç ?åˆ° redis
     *
     * @param smsCode çŸ­ä¿¡éªŒè¯?ç ?
     * @param request ServletWebRequest
     */
    public void save(SmsCode smsCode, ServletWebRequest request, String mobile) throws Exception {
        redisTemplate.opsForValue().set(key(request, mobile), smsCode.getCode(), TIME_OUT, TimeUnit.SECONDS);
    }

    /**
     * èŽ·å?–éªŒè¯?ç ?
     *
     * @param request ServletWebRequest
     * @return éªŒè¯?ç ?
     */
    public String get(ServletWebRequest request, String mobile) throws Exception {
        return redisTemplate.opsForValue().get(key(request, mobile));
    }

    /**
     * ç§»é™¤éªŒè¯?ç ?
     *
     * @param request ServletWebRequest
     */
    public void remove(ServletWebRequest request, String mobile) throws Exception {
        redisTemplate.delete(key(request, mobile));
    }

    private String key(ServletWebRequest request, String mobile) throws Exception {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new Exception("è¯·åœ¨è¯·æ±‚å¤´ä¸­è®¾ç½®deviceId");
        }
        return SMS_CODE_PREFIX + deviceId + ":" + mobile;
    }
}
