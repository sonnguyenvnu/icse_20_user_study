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
import com.roncoo.pay.reconciliation.dao.RpAccountCheckMistakeScratchPoolDao;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckMistakeScratchPool;
import com.roncoo.pay.reconciliation.service.RpAccountCheckMistakeScratchPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对账暂存池接�?�实现 .
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Service("rpAccountCheckMistakeScratchPoolService")
public class RpAccountCheckMistakeScratchPoolServiceImpl implements RpAccountCheckMistakeScratchPoolService {

	@Autowired
	private RpAccountCheckMistakeScratchPoolDao rpAccountCheckMistakeScratchPoolDao;

	@Override
	public void saveData(RpAccountCheckMistakeScratchPool RpAccountCheckMistakeScratchPool) {
		rpAccountCheckMistakeScratchPoolDao.insert(RpAccountCheckMistakeScratchPool);
	}

	/**
	 * 批�?�?存记录
	 * 
	 * @param ScratchPoolList
	 */
	public void savaListDate(List<RpAccountCheckMistakeScratchPool> scratchPoolList) {
		for (RpAccountCheckMistakeScratchPool record : scratchPoolList) {
			rpAccountCheckMistakeScratchPoolDao.insert(record);
		}
	}

	@Override
	public void updateData(RpAccountCheckMistakeScratchPool RpAccountCheckMistakeScratchPool) {
		rpAccountCheckMistakeScratchPoolDao.update(RpAccountCheckMistakeScratchPool);
	}

	@Override
	public RpAccountCheckMistakeScratchPool getDataById(String id) {
		return rpAccountCheckMistakeScratchPoolDao.getById(id);
	}

	/**
	 * 获�?�分页数�?�
	 * 
	 * @param pageParam
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, RpAccountCheckMistakeScratchPool rpAccountCheckMistakeScratchPool) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		return rpAccountCheckMistakeScratchPoolDao.listPage(pageParam, paramMap);
	}

	/**
	 * 从缓冲池中删除数�?�
	 * 
	 * @param scratchPoolList
	 */
	public void deleteFromPool(List<RpAccountCheckMistakeScratchPool> scratchPoolList) {
		for (RpAccountCheckMistakeScratchPool record : scratchPoolList) {
			rpAccountCheckMistakeScratchPoolDao.delete(record.getId());
		}

	}

	/**
	 * 查询出缓存池中所有的数�?�
	 * 
	 * @return
	 */
	public List<RpAccountCheckMistakeScratchPool> listScratchPoolRecord(Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		return rpAccountCheckMistakeScratchPoolDao.listByColumn(paramMap);
	}
}
