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
package com.roncoo.pay.permission.dao.impl;

import com.roncoo.pay.common.core.exception.BizException;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.PermissionBaseDao;
import com.roncoo.pay.permission.entity.PermissionBaseEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * æ•°æ?®è®¿é—®å±‚åŸºç¡€æ”¯æ’‘ç±».
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
public abstract class PermissionBaseDaoImpl<T extends PermissionBaseEntity> implements PermissionBaseDao<T> {

	protected static final Log LOG = LogFactory.getLog(PermissionBaseDaoImpl.class);

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE_BY_ID = "update";
	public static final String SQL_BATCH_UPDATE_BY_IDS = "batchUpdateByIds";
	public static final String SQL_BATCH_UPDATE_BY_COLUMN = "batchUpdateByColumn";
	public static final String SQL_SELECT_BY_ID = "selectByPrimaryKey";
	public static final String SQL_LIST_BY_COLUMN = "listByColumn";
	public static final String SQL_COUNT_BY_COLUMN = "getCountByColumn";
	public static final String SQL_DELETE_BY_ID = "deleteByPrimaryKey";
	public static final String SQL_BATCH_DELETE_BY_IDS = "batchDeleteByIds";
	public static final String SQL_BATCH_DELETE_BY_COLUMN = "batchDeleteByColumn";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // æ ¹æ?®å½“å‰?åˆ†é¡µå?‚æ•°è¿›è¡Œç»Ÿè®¡

	/**
	 * æ³¨å…¥SqlSessionTemplateå®žä¾‹(è¦?æ±‚Springä¸­è¿›è¡ŒSqlSessionTemplateçš„é…?ç½®).
	 * å?¯ä»¥è°ƒç”¨sessionTemplateå®Œæˆ?æ•°æ?®åº“æ“?ä½œ.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	/**
	 * å?•æ?¡æ?’å…¥æ•°æ?®.
	 */
	public int insert(T entity) {
		int result = sessionTemplate.insert(getStatement(SQL_INSERT), entity);
		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("æ•°æ?®åº“æ“?ä½œ,insertè¿”å›ž0.{%s}", getStatement(SQL_INSERT));
		}
		return result;
	}

	/**
	 * æ‰¹é‡?æ?’å…¥æ•°æ?®.
	 */
	public int insert(List<T> list) {
		if (list.isEmpty() || list.size() <= 0) {
			return 0;
		}
		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("æ•°æ?®åº“æ“?ä½œ,batchInsertè¿”å›ž0.{%s}", getStatement(SQL_BATCH_INSERT));
		}
		return result;
	}

	/**
	 * æ ¹æ?®idå?•æ?¡æ›´æ–°æ•°æ?®.
	 */
	public int update(T entity) {
		entity.setEditTime(new Date());
		int result = sessionTemplate.update(getStatement(SQL_UPDATE_BY_ID), entity);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("æ•°æ?®åº“æ“?ä½œ,updateByPrimaryKeyè¿”å›ž0.{%s}", getStatement(SQL_UPDATE_BY_ID));
		}
		return result;
	}

	/**
	 * æ ¹æ?®idæ‰¹é‡?æ›´æ–°æ•°æ?®.
	 */
	public int update(List<T> list) {
		if (list.isEmpty() || list.size() <= 0) {
			return 0;
		}
		int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_IDS), list);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("æ•°æ?®åº“æ“?ä½œ,batchUpdateByIdsè¿”å›ž0.{%s}", getStatement(SQL_BATCH_UPDATE_BY_IDS));
		}
		return result;
	}

	/**
	 * æ ¹æ?®columnæ‰¹é‡?æ›´æ–°æ•°æ?®.
	 */
	public int update(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return 0;
		}
		int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_COLUMN), paramMap);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("æ•°æ?®åº“æ“?ä½œ,batchUpdateByColumnè¿”å›ž0.{%s}", getStatement(SQL_BATCH_UPDATE_BY_COLUMN));
		}
		return result;
	}

	/**
	 * æ ¹æ?®idæŸ¥è¯¢æ•°æ?®.
	 */
	public T getById(Long id) {
		return sessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID), id);
	}

	/**
	 * æ ¹æ?®columnæŸ¥è¯¢æ•°æ?®.
	 */
	public T getByColumn(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectOne(getStatement(SQL_LIST_BY_COLUMN), paramMap);
	}

	/**
	 * æ ¹æ?®æ?¡ä»¶æŸ¥è¯¢ getBy: selectOne <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public T getBy(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectOne(getStatement(SQL_LIST_BY), paramMap);
	}

	/**
	 * æ ¹æ?®æ?¡ä»¶æŸ¥è¯¢åˆ—è¡¨æ•°æ?®.
	 */
	public List<T> listBy(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectList(getStatement(SQL_LIST_BY), paramMap);
	}

	/**
	 * æ ¹æ?®columnæŸ¥è¯¢åˆ—è¡¨æ•°æ?®.
	 */
	public List<T> listByColumn(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectList(getStatement(SQL_LIST_BY_COLUMN), paramMap);
	}

	/**
	 * æ ¹æ?®columnæŸ¥è¯¢è®°å½•æ•°.
	 */
	public Long getCountByColumn(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_COLUMN), paramMap);
	}

	/**
	 * æ ¹æ?®idåˆ é™¤æ•°æ?®.
	 */
	public int delete(Long id) {
		return (int) sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	/**
	 * æ ¹æ?®idæ‰¹é‡?åˆ é™¤æ•°æ?®.
	 */
	public int delete(List<T> list) {
		if (list.isEmpty() || list.size() <= 0) {
			return 0;
		} else {
			return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_IDS), list);
		}
	}

	/**
	 * æ ¹æ?®columnæ‰¹é‡?åˆ é™¤æ•°æ?®.
	 */
	public int delete(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return 0;
		} else {
			return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_COLUMN), paramMap);
		}
	}

	/**
	 * åˆ†é¡µæŸ¥è¯¢æ•°æ?® .
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}

		// ç»Ÿè®¡æ€»è®°å½•æ•°
		Long totalCount = sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);

		// æ ¡éªŒå½“å‰?é¡µæ•°
		int currentPage = PageBean.checkCurrentPage(totalCount.intValue(), pageParam.getNumPerPage(), pageParam.getPageNum());
		pageParam.setPageNum(currentPage); // ä¸ºå½“å‰?é¡µé‡?æ–°è®¾å€¼
		// æ ¡éªŒé¡µé?¢è¾“å…¥çš„æ¯?é¡µè®°å½•æ•°numPerPageæ˜¯å?¦å?ˆæ³•
		int numPerPage = PageBean.checkNumPerPage(pageParam.getNumPerPage()); // æ ¡éªŒæ¯?é¡µè®°å½•æ•°
		pageParam.setNumPerPage(numPerPage); // é‡?æ–°è®¾å€¼

		// æ ¹æ?®é¡µé?¢ä¼ æ?¥çš„åˆ†é¡µå?‚æ•°æž„é€ SQLåˆ†é¡µå?‚æ•°
		paramMap.put("pageFirst", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("pageSize", pageParam.getNumPerPage());
		paramMap.put("startRowNum", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("endRowNum", pageParam.getPageNum() * pageParam.getNumPerPage());

		// èŽ·å?–åˆ†é¡µæ•°æ?®é›†
		List<Object> list = sessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap);

		Object isCount = paramMap.get("isCount"); // æ˜¯å?¦ç»Ÿè®¡å½“å‰?åˆ†é¡µæ?¡ä»¶ä¸‹çš„æ•°æ?®ï¼š1:æ˜¯ï¼Œå…¶ä»–ä¸ºå?¦
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), totalCount.intValue(), list, countResultMap);
		} else {
			// æž„é€ åˆ†é¡µå¯¹è±¡
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), totalCount.intValue(), list);
		}
	}

	/**
	 * å‡½æ•°åŠŸèƒ½è¯´æ˜Ž ï¼š èŽ·å?–Mapperå‘½å??ç©ºé—´. ä¿®æ”¹è€…å??å­—ï¼š Along ä¿®æ”¹æ—¥æœŸï¼š 2016-1-8 ä¿®æ”¹å†…å®¹ï¼š
	 * 
	 * @å?‚æ•°ï¼š@param sqlId
	 * @å?‚æ•°ï¼š@return
	 * @returnï¼šString
	 * @throws
	 */
	public String getStatement(String sqlId) {
		String name = this.getClass().getName();
		// å?•çº¿ç¨‹ç”¨StringBuilderï¼Œç¡®ä¿?é€Ÿåº¦ï¼›å¤šçº¿ç¨‹ç”¨StringBuffer,ç¡®ä¿?å®‰å…¨
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(".").append(sqlId);
		return sb.toString();
	}

}
