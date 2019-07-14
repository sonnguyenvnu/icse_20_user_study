package com.us.example.service;

import com.us.example.dao.PermissionDao;
import com.us.example.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by yangyibo on 17/1/19.
 */
@Service
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    //此方法是为了判定用户请求的url 是�?�在�?��?表中，如果在�?��?表中，则返回给 decide 方法，用�?�判定用户是�?�有此�?��?。如果�?在�?��?表中则放行。
    //因为我�?想�?一次�?�了请求，都先�?匹�?一下�?��?表中的信�?�是�?是包�?�此url，
    // 我准备直接拦截，�?管请求的url 是什么都直接拦截，然�?�在MyAccessDecisionManager的decide 方法中�?�拦截还是放行的决策。
    //所以此方法的返回值�?能返回 null 此处我就�?便返回一下。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> co=new ArrayList<>();
        co.add(new SecurityConfig("null"));
        return co;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
