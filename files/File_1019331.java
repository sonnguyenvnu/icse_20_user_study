package cn.iocoder.common.framework.util;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * {@link ServiceException} 工具类
 *
 * 目的在于，格�?化异常信�?��??示。
 * 考虑到 String.format 在�?�数�?正确时会报错，因此使用 {} 作为�?��?符，并使用 {@link #doFormat(int, String, Object...)} 方法�?�格�?化
 *
 * 因为 {@link #messages} 里�?�默认是没有异常信�?��??示的模�?�的，所以需�?使用方自己�?始化进去。目�?想到的有几�?方�?：
 *
 * 1. 异常�??示信�?�，写在枚举类中，例如说，cn.iocoder.oceans.user.api.constants.ErrorCodeEnum 类 + ServiceExceptionConfiguration
 * 2. 异常�??示信�?�，写在 .properties 等等�?置文件
 * 3. 异常�??示信�?�，写在 Apollo 等等�?置中心中，从而实现�?�动�?刷新
 * 4. 异常�??示信�?�，存储在 db 等等数�?�库中，从而实现�?�动�?刷新
 */
public class ServiceExceptionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionUtil.class);

    /**
     * 错误�?�??示模�?�
     */
    private static ConcurrentMap<Integer, String> messages = new ConcurrentHashMap<>();

    public static void putAll(Map<Integer, String> messages) {
        ServiceExceptionUtil.messages.putAll(messages);
    }

    public static void put(Integer code, String message) {
        ServiceExceptionUtil.messages.put(code, message);
    }

    // TODO 芋艿，�?�能�?是目�?最优解，目�?暂时这样
    public static <T> CommonResult<T> error(Integer code) {
        return CommonResult.error(code, messages.get(code));
    }

    public static CommonResult error(Integer code, Object... params) {
        String message = doFormat(code, messages.get(code), params);
        return CommonResult.error(code, message);
    }

    /**
     * 创建指定编�?�的 ServiceException 的异常
     *
     * @param code 编�?�
     * @return 异常
     */
    public static ServiceException exception(Integer code) {
        return new ServiceException(code, messages.get(code));
    }

    /**
     * 创建指定编�?�的 ServiceException 的异常
     *
     * @param code 编�?�
     * @param params 消�?��??示的�?��?符对应的�?�数
     * @return 异常
     */
    public static ServiceException exception(Integer code, Object... params) {
        String message = doFormat(code, messages.get(code), params);
        return new ServiceException(code, message);
    }

    public static ServiceException exception(Integer code, String messagePattern, Object... params) {
        String message = doFormat(code, messagePattern, params);
        return new ServiceException(code, message);
    }

    /**
     * 将错误编�?�对应的消�?�使用 params 进行格�?化。
     *
     * @param code           错误编�?�
     * @param messagePattern 消�?�模版
     * @param params         �?�数
     * @return 格�?化�?�的�??示
     */
    private static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                LOGGER.error("[doFormat][�?�数过多：错误�?({})|错误内容({})|�?�数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    sbuf.append(messagePattern.substring(i, messagePattern.length()));
                    return sbuf.toString();
                }
            } else {
                sbuf.append(messagePattern.substring(i, j));
                sbuf.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            LOGGER.error("[doFormat][�?�数过少：错误�?({})|错误内容({})|�?�数({})", code, messagePattern, params);
        }
        sbuf.append(messagePattern.substring(i, messagePattern.length()));
        return sbuf.toString();
    }

}
