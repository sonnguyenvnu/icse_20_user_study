package com.myimooc.spring.simple.aop.api;

/**
 * <br>
 * 标题: 业务逻辑实现<br>
 * �??述: 业务逻辑实现<br>
 * 时间: 2017/01/18<br>
 *
 * @author zc
 */
public class BizLogicImpl implements BizLogic {

    @Override
    public String save() {
        System.out.println("BizLogicImpl : BizLogicImpl save.");
        return "BizLogicImpl save.";
//		throw new RuntimeException();
    }

}
