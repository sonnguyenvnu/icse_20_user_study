package com.myimooc.spring.simple.ioc.interfaces;

/**
 * <br>
 * æ ‡é¢˜: é?¢å?‘æŽ¥å?£ç¼–ç¨‹<br>
 * æ??è¿°: å?¦å¤–ä¸€ä¸ªå®žçŽ°<br>
 * æ—¶é—´: 2017/01/18<br>
 *
 * @author zc
 */
public class OneInterfacesImpl implements OneInterface {

    public String hello(String word) {
        return "Word form interfaces\"OneInterface\":" + word;
    }

    @Override
    public void say(String arg) {
        System.out.println("OneInterfacesImpl sayï¼š" + arg);
    }

}
