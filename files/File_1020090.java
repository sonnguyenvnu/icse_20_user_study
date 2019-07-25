package com.myimooc.spring.simple.beanannotation.javabased;

/**
 * <br>
 * 标题: 字符串仓库<br>
 * �??述: 字符串仓库<br>
 * 时间: 2017/01/18<br>
 *
 * @author zc
 */
public class StringStore implements Store<String> {

    public void init() {
        System.out.println("This is init.");
    }

    public void destroy() {
        System.out.println("This is destroy.");
    }

}
