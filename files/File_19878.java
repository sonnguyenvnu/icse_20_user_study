package com.example.demo.authentication;

import com.example.demo.domain.User;
import com.example.demo.utils.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义实现 ShiroRealm，包�?�认�?和授�?�两大模�?�
 *
 * @author MrBird
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * `
     * 授�?�模�?�，获�?�用户角色和�?��?
     *
     * @param token token
     * @return AuthorizationInfo �?��?信�?�
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());
        User user = SystemUtils.getUser(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获�?�用户角色集（模拟值，实际从数�?�库获�?�）
        simpleAuthorizationInfo.setRoles(user.getRole());

        // 获�?�用户�?��?集（模拟值，实际从数�?�库获�?�）
        simpleAuthorizationInfo.setStringPermissions(user.getPermission());
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认�?
     *
     * @param authenticationToken 身份认�? token
     * @return AuthenticationInfo 身份认�?信�?�
     * @throws AuthenticationException 认�?相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过�?�的，已�?�?过了解密
        String token = (String) authenticationToken.getCredentials();

        String username = JWTUtil.getUsername(token);

        if (StringUtils.isBlank(username))
            throw new AuthenticationException("token校验�?通过");

        // 通过用户�??查询用户信�?�
        User user = SystemUtils.getUser(username);

        if (user == null)
            throw new AuthenticationException("用户�??或密�?错误");
        if (!JWTUtil.verify(token, username, user.getPassword()))
            throw new AuthenticationException("token校验�?通过");
        return new SimpleAuthenticationInfo(token, token, "shiro_realm");
    }
}
