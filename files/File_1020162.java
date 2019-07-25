package com.myimooc.guice.demo.useguice;

import com.google.inject.AbstractModule;
import com.myimooc.guice.demo.useguice.helloworlddemo.HelloWorldModule;

/**
 * @title MainModuleç±»
 * @describe Guiceç”¨æ?¥é…?ç½®çš„ç±»
 * @author zc
 * @version 1.0 2017-10-15
 */
public class MainModule extends AbstractModule{

	@Override
	protected void configure() {
		install(new HelloWorldModule());
	}
}
