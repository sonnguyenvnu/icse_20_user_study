package org.hswebframework.web.oauth2.core.scope;

import org.hswebframework.web.authorization.Permission;

import java.util.Set;

/**
 * scope解�?器
 *
 * @author zhouhao
 */
public interface ScopeParser {
    /**
     * 将文本解�?为Set
     * <pre>
     *     user-info:get user-share:push
     * </pre>
     * <pre>
     *     Set{"user-info:get","user-share:push"}
     * </pre>
     *
     * @param scopeText socket文本
     * @return socpe集�?�
     */
    Set<String> fromScopeText(String scopeText);

    String toScopeText(Set<String> scopeText);

    /**
     * 将scope解�?为Permission
     *
     * @param scope scope集�?�
     * @return permission集�?�。如果�?�数为null或者空，则返回空集�?�
     */
    Set<Permission> parsePermission(Set<String> scope);

    default Set<Permission> parsePermission(String scopeText) {
        return parsePermission(fromScopeText(scopeText));
    }
}
