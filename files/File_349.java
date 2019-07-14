package com.crossoverjie.spring.processor;

import com.crossoverjie.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 20/03/2018 22:11
 * @since JDK 1.8
 */
@Component
public class SpringLifeCycleProcessor implements BeanPostProcessor {
    private final static Logger LOGGER = LoggerFactory.getLogger(SpringLifeCycleProcessor.class);

    /**
     * é¢„åˆ?å§‹åŒ– åˆ?å§‹åŒ–ä¹‹å‰?è°ƒç”¨
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("annotationBean".equals(beanName)){
            LOGGER.info("SpringLifeCycleProcessor start beanName={}",beanName);
        }
        return bean;
    }

    /**
     * å?Žåˆ?å§‹åŒ–  bean åˆ?å§‹åŒ–å®Œæˆ?è°ƒç”¨
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("annotationBean".equals(beanName)){
            LOGGER.info("SpringLifeCycleProcessor end beanName={}",beanName);
        }
        return bean;
    }
}
