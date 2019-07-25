package com.myimooc.java.design.pattern.observer.common;

/**
 * @describe 具体的观察者对象，实现更新的方法，使自身的状�?和目标的状�?�?�?一致
 * @author zc
 * @version 1.0 2017-08-29
 */
public class ConcreteObserver implements Observer {
	
	/**
	 * 观察者的状�?
	 */
	private String observerState;
	
	/**
	 * 获�?�目标类的状�?�?�步到观察者的状�?中
	 */
	@Override
	public void update(Subject subject) {
		observerState = ((ConcreteSubject)subject).getSubjectState();
	}

	public String getObserverState() {
		return observerState;
	}
	
}
