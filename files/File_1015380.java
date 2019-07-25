package org.xujin.moss.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.DependsOn;
import org.xujin.moss.security.jwt.JwtFilter;
import org.xujin.moss.security.jwt.ResourceCheckFilter;
import org.xujin.moss.security.shiro.DBRealm;
import com.google.common.collect.Lists;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xujin.moss.service.UserService;

import javax.servlet.Filter;
import java.util.*;


@Configuration
public class ShiroAuthConfig {


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器并且�?��??为jwt
        Map<String, Filter> filterMap = new HashMap<>();

        // 必须设置 SecurityManager
        factoryBean.setSecurityManager(securityManager);
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JwtFilter());
        filterMap.put("resourceCheckFilter", new ResourceCheckFilter());
        factoryBean.setLoginUrl("/admin/login");
        factoryBean.setSuccessUrl("/index");
        factoryBean.setFilters(filterMap);
        // 设置无�?��?时跳转的 url;
        factoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 访问 /unauthorized/** �?通过JWTFilter
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/media/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        // 所有请求通过我们自己的JWT Filter
        filterChainDefinitionMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(UserService userService) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        List<Realm> realms = Lists.newArrayList();
        realms.add(new DBRealm(userService));
        manager.setRealms(realms);

        /*
         * 关闭shiro自带的session，详情�?文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }


    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager){
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager);
        return bean;
    }

    /**
     * 下�?�的代�?是添加注解支�?
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止�?�?代�?�和�?�能引起代�?�出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
