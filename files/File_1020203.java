package com.myimooc.classloader;

/**
 * @title 接�?�实现类
 * @describe BaseManager的�?类，此类需�?实现Java类的热加载功能
 * @author zc
 * @version 1.0 2017-12-01
 */
public class MyManager implements BaseManager {

	@Override
	public void logic() {
		System.out.println("学习如何实现Java类的热加载案例");
	}
}
