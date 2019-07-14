/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.reconciliation.service.impl;

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.reconciliation.dao.RpAccountCheckBatchDao;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckBatch;
import com.roncoo.pay.reconciliation.service.RpAccountCheckBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 对账批次接�?�实现 .
 * 
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Service("rpAccountCheckBatchService")
public class RpAccountCheckBatchServiceImpl implements RpAccountCheckBatchService {

	@Autowired
	private RpAccountCheckBatchDao rpAccountCheckBatchDao;

	@Override
	public void saveData(RpAccountCheckBatch rpAccountCheckBatch) {
		rpAccountCheckBatchDao.insert(rpAccountCheckBatch);
	}

	@Override
	public void updateData(RpAccountCheckBatch rpAccountCheckBatch) {
		rpAccountCheckBatchDao.update(rpAccountCheckBatch);
	}

	@Override
	public RpAccountCheckBatch getDataById(String id) {
		return rpAccountCheckBatchDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {

		return rpAccountCheckBatchDao.listPage(pageParam, paramMap);
	}

	/**
	 * 根�?��?�件查询实体
	 * 
	 * @param paramMap
	 */
	public List<RpAccountCheckBatch> listBy(Map<String, Object> paramMap) {
		return rpAccountCheckBatchDao.listBy(paramMap);
	}

}
