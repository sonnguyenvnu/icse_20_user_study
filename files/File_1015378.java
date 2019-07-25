package org.xujin.moss.security.shiro;

import org.xujin.moss.security.jwt.JwtToken;
import org.xujin.moss.security.jwt.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.ldap.AbstractLdapRealm;
import org.apache.shiro.realm.ldap.LdapContextFactory;
import org.apache.shiro.realm.ldap.LdapUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import java.util.HashSet;
import java.util.Set;

/**
 * LDAP
 * @author xujin
 */
public class LdapRealm extends AbstractLdapRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapRealm.class);

    /**
     * LDAP信�?�
     * this.principalSuffix = "@corp.xxx.com";
       this.searchBase = "ou=xujin,DC=corp,DC=xujin,DC=org";
       this.url = "ldap://ip:端�?�";
     */
    public LdapRealm() {
        this.principalSuffix = "";
        this.searchBase = "";
        this.url = "";
        super.onInit();
    }


    /**
     * 必须�?写此方法，�?然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken authenticationToken,
                                                            LdapContextFactory ldapContextFactory) throws NamingException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数�?�库进行对比
        String username = JwtUtil.getUsername(token);

        if (null==username  || !JwtUtil.verify(token, username)) {
            throw new AuthenticationException("token认�?失败�?");
        }
        LdapContext ctx = null;
        try {
            ctx = ldapContextFactory.getLdapContext(username, null);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        } finally {
            LdapUtils.closeContext(ctx);
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

    @Override
    protected AuthorizationInfo queryForAuthorizationInfo(PrincipalCollection principals,
                                                          LdapContextFactory ldapContextFactory) throws NamingException {
        System.out.println("————�?��?认�?————");
        String username = JwtUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        //String role = userMapper.getRole(username);
        //�?个角色拥有默认的�?��?
        //String rolePermission = userMapper.getRolePermission(username);
        //�?个用户�?�以设置新的�?��?
        //String permission = userMapper.getPermission(username);
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需�?将 role, permission �?装到 Set 作为 info.setRoles(), info.setStringPermissions() 的�?�数
       // roleSet.add(role);
       // permissionSet.add(rolePermission);
        //permissionSet.add(permission);
        //设置该用户拥有的角色和�?��?
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
