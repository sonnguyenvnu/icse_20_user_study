package com.myimooc.java.design.pattern.observer.push;

/**
 * @describe 具体的观察者对象，实现更新的方法，使自身的状�?和目标的状�?�?�?一致
 * @author zc
 * @version 1.0 2017-08-29
 */
public class ConcreteObserver implements Observer {
	
	/**
	 * 观察者的�??称，是�?收到了这个信�?�
	 */
	private String observerName;
	
	/**
	 * 天气的内容信�?�，这个消�?�从目标处获�?�
	 */
	private String weatherContent;
	
	/**
	 * �??醒的内容，�?�?�的观察者�??醒�?�?�的内容
	 */
	private String remindThing;
	
	/**
	 * 获�?�目标类的状�?�?�步到观察者的状�?中
	 */
	@Override
	public void update(String weatherContent) {
		this.weatherContent = weatherContent;
		System.out.println(observerName + " 收到了天气信�?�  " + weatherContent + "，准备去�?� "+remindThing);
	}

	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}

	public String getWeatherContent() {
		return weatherContent;
	}

	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
	}

	public String getRemindThing() {
		return remindThing;
	}

	public void setRemindThing(String remindThing) {
		this.remindThing = remindThing;
	}
}
