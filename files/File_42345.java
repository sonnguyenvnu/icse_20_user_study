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
package com.roncoo.pay.app.reconciliation.biz;

import com.roncoo.pay.app.reconciliation.parser.ParserInterface;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckBatch;
import com.roncoo.pay.reconciliation.vo.ReconciliationEntityVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 对账文件解�?业务逻辑.
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Component("reconciliationFileParserBiz")
public class ReconciliationFileParserBiz implements BeanFactoryAware {

	// 加载beanfactory
	private BeanFactory beanFactory;

	public Object getService(String payInterface) {
		return beanFactory.getBean(payInterface);
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	private static final Log LOG = LogFactory.getLog(ReconciliationFileParserBiz.class);

	/**
	 * 解�?file文件
	 * 
	 * @param batch
	 *            对账批次实体
	 * @param file
	 *            下载的对账文件
	 * @param billDate
	 *            下载对账�?�的日期
	 * 
	 * @param interfaceCode
	 *            具体的支付方�?
	 * 
	 * @return 转�?�之�?�的vo对象
	 * @throws IOException
	 */
	public List<ReconciliationEntityVo> parser(RpAccountCheckBatch batch, File file, Date billDate, String interfaceCode) throws IOException {

		// 解�?�? ReconciliationEntityVo 对象
		List<ReconciliationEntityVo> rcVoList = null;

		// 根�?�支付方�?得到解�?器的�??字
		String parserClassName = interfaceCode + "Parser";
		LOG.info("根�?�支付方�?得到解�?器的�??字[" + parserClassName + "]");
		ParserInterface service = null;
		try {
			// 根�?��??字获�?�相应的解�?器
			service = (ParserInterface) this.getService(parserClassName);
		} catch (NoSuchBeanDefinitionException e) {
			LOG.error("根�?�解�?器的�??字[" + parserClassName + "]，没有找到相应的解�?器");
			return null;
		}
		// 使用相应的解�?器解�?文件
		rcVoList = service.parser(file, billDate, batch);

		return rcVoList;

	}
}
