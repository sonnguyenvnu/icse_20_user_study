package com.myimooc.spring.aop.guide.service;

import com.myimooc.spring.aop.guide.domain.Product;
import org.springframework.stereotype.Service;

import com.myimooc.spring.aop.guide.security.AdminOnly;

/**
 * @title äº§å“?æœ?åŠ¡ç±»
 * @describe äº§å“?ç›¸å…³ä¸šåŠ¡æœ?åŠ¡-AOPæ–¹å¼?å®žçŽ°æ?ƒé™?æ ¡éªŒ
 * @author zc
 * @version 1.0 2017-09-03
 */
@Service
public class ProductServiceAop {
	
	@AdminOnly
	public void insert(Product product){
		System.out.println("insert product");
	}
	
	@AdminOnly
	public void delete(Long id){
		System.out.println("delete product");
	}
	
}
