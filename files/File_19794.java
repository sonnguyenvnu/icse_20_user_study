package com.example.demo.config;

import com.example.demo.converter.PropertiesHttpMessageConverter;
import com.example.demo.handler.PropertiesHandlerMethodReturnValueHandler;
import com.example.demo.resolver.PropertiesHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MrBird
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        // 获�?�当�? RequestMappingHandlerAdapter 所有的 ArgumentResolver对象
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(argumentResolvers.size() + 1);
        // 添加 PropertiesHandlerMethodArgumentResolver 到集�?�第一个�?置
        newArgumentResolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
        // 将原 ArgumentResolver 添加到集�?�中
        newArgumentResolvers.addAll(argumentResolvers);
        // �?新设置 ArgumentResolver对象集�?�
        requestMappingHandlerAdapter.setArgumentResolvers(newArgumentResolvers);

        // 获�?�当�? RequestMappingHandlerAdapter 所有的 returnValueHandlers对象
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(returnValueHandlers.size() + 1);
        // 添加 PropertiesHandlerMethodReturnValueHandler 到集�?�第一个�?置
        newReturnValueHandlers.add(0, new PropertiesHandlerMethodReturnValueHandler());
        // 将原 returnValueHandlers 添加到集�?�中
        newReturnValueHandlers.addAll(returnValueHandlers);
        // �?新设置 ReturnValueHandlers对象集�?�
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // converters.add(new PropertiesHttpMessageConverter());
        // 指定顺�?，这里为第一个
        converters.add(0, new PropertiesHttpMessageConverter());
    }

}
