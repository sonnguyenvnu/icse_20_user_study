package org.hswebframework.web.authorization.token;


import org.hswebframework.web.authorization.User;
import org.hswebframework.web.authorization.exception.UnAuthorizedException;

import java.io.Serializable;

/**
 * 用户的token信�?�
 *
 * @author zhouhao
 * @since 3.0
 */
public interface UserToken extends Serializable, Comparable<UserToken> {
    /**
     * @return 用户id
     * @see User#getId()
     */
    String getUserId();

    /**
     * @return token
     */
    String getToken();

    /**
     * @return 请求总次数
     */
    long getRequestTimes();

    /**
     * @return 最�?�一次请求时间
     */
    long getLastRequestTime();

    /**
     * @return 首次请求时间
     */
    long getSignInTime();

    /**
     * @return 令牌状�?
     */
    TokenState getState();

    /**
     * @return 令牌类型, 默认:default
     */
    String getType();

    long getMaxInactiveInterval();

    /**
     * @return 是�?�正常
     */
    @Deprecated
    default boolean isEffective() {
        return isNormal();
    }

    default boolean isNormal() {
        return getState() == TokenState.normal;
    }

    /**
     * @return 是�?�已过期
     */
    default boolean isExpired() {
        return getState() == TokenState.expired;
    }

    /**
     * @return 是�?�离线
     */
    default boolean isOffline() {
        return getState() == TokenState.offline;
    }

    default boolean isLock() {
        return getState() == TokenState.lock;
    }

    default boolean isDeny() {
        return getState() == TokenState.deny;
    }


    default boolean validate() {
        if (!isNormal()) {
            throw new UnAuthorizedException(getState());
        }
        return true;
    }

    @Override
    default int compareTo(UserToken target) {
        if (target == null) {
            return 0;
        }
        return Long.compare(getSignInTime(), target.getSignInTime());
    }
}
