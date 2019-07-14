package com.geekq.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekq.admin.service.OrdersService;
import com.geekq.web.service.CulsterService;

@Service("buyService")
public class CulsterServiceImpl implements CulsterService {
	
	final static Logger log = LoggerFactory.getLogger(CulsterServiceImpl.class);
	
//	@Autowired
//	private ItemsService itemService;

	@Autowired
	private OrdersService ordersService;
	
	@Override
	public void doBuyItem(String itemId) {
		// �?少库存
//		itemService.displayReduceCounts(itemId, 1);
		
		// 创建订�?�
		ordersService.createOrder(itemId);
	}
	
	@Override
	public boolean displayBuy(String itemId) {
		
		int buyCounts = 5;
		
//		// 1. 判断库存
//		int stockCounts = itemService.getItemCounts(itemId);
//		if (stockCounts < buyCounts) {
//			log.info("库存剩余{}件，用户需求�?{}件，库存�?足，订�?�创建失败...",
//					stockCounts, buyCounts);
//			return false;
//		}
//
//		// 2. 创建订�?�
		boolean isOrderCreated = ordersService.createOrder(itemId);
//
//		// 3. 创建订�?��?功�?�，扣除库存
//		if (isOrderCreated) {
//			log.info("订�?�创建�?功...");
//			itemService.displayReduceCounts(itemId, buyCounts);
//		} else {
//			log.info("订�?�创建失败...");
//			return false;
//		}
		
		return true;
	}
	
}

