package com.myimooc.spring.aop.proxy.pattern;

/**
 * @title 代�?�对象
 * @describe �?�样也实现了Subject接�?�
 * @author zc
 * @version 1.0 2017-09-13
 */
public class Proxy implements Subject{

	/**
	 * 需�?引用目标对象
	 */
	private RealSubject realSubject;

	/**
	 * 强制必须传入目标对象
	 * @param realSubject 目标对象
	 */
	public Proxy(RealSubject realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	public void request() {
		
		// 在目标对象方法执行之�?�?�一些�?外的事情
		System.out.println("before");
		
		try{
		
			// 代�?�对象�?会�?�真实的业务逻辑，还是委托给真实的目标对象执行
			realSubject.request();
		}catch (Exception e) {
			System.out.println("ex:"+e.getMessage());
			throw e;
		}finally {
			
			// 在目标对象方法执行之�?��?�一些�?外的事情
			System.out.println("after");
		}
	}
}
