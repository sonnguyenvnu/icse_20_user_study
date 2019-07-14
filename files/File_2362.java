package com.zheng.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * èµ„æº?æ–‡ä»¶è¯»å?–å·¥å…·
 *
 * @author shuzheng
 * @date 2016å¹´10æœˆ15æ—¥
 */
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context = null;

	private SpringContextUtil() {
		super();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	/**
	 * æ ¹æ?®å??ç§°èŽ·å?–bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * æ ¹æ?®beanå??ç§°èŽ·å?–æŒ‡å®šç±»åž‹bean
	 * @param beanName beanå??ç§°
	 * @param clazz è¿”å›žçš„beanç±»åž‹,è‹¥ç±»åž‹ä¸?åŒ¹é…?,å°†æŠ›å‡ºå¼‚å¸¸
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return context.getBean(beanName, clazz);
	}
	/**
	 * æ ¹æ?®ç±»åž‹èŽ·å?–bean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		T t = null;
		Map<String, T> map = context.getBeansOfType(clazz);
		for (Map.Entry<String, T> entry : map.entrySet()) {
			t = entry.getValue();
		}
		return t;
	}

	/**
	 * æ˜¯å?¦åŒ…å?«bean
	 * @param beanName
	 * @return
	 */
	public static boolean containsBean(String beanName) {
		return context.containsBean(beanName);
	}

	/**
	 * æ˜¯å?¦æ˜¯å?•ä¾‹
	 * @param beanName
	 * @return
	 */
	public static boolean isSingleton(String beanName) {
		return context.isSingleton(beanName);
	}

	/**
	 * beançš„ç±»åž‹
	 * @param beanName
	 * @return
	 */
	public static Class getType(String beanName) {
		return context.getType(beanName);
	}

}
