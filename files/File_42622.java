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
package com.roncoo.pay.trade.service.impl;

import com.roncoo.pay.common.core.enums.PublicEnum;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.common.core.utils.DateUtils;
import com.roncoo.pay.trade.dao.RpTradePaymentOrderDao;
import com.roncoo.pay.trade.dao.RpTradePaymentRecordDao;
import com.roncoo.pay.trade.entity.RpTradePaymentOrder;
import com.roncoo.pay.trade.entity.RpTradePaymentRecord;
import com.roncoo.pay.trade.enums.TradeStatusEnum;
import com.roncoo.pay.trade.service.RpTradePaymentQueryService;
import com.roncoo.pay.trade.utils.MerchantApiUtil;
import com.roncoo.pay.trade.vo.OrderPayResultVo;
import com.roncoo.pay.trade.vo.PaymentOrderQueryParam;
import com.roncoo.pay.user.entity.RpUserPayConfig;
import com.roncoo.pay.user.exception.UserBizException;
import com.roncoo.pay.user.service.RpUserPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>功能说明:交易模�?�查询类实现</b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */

@Service("rpTradePaymentQueryService")
public class RpTradePaymentQueryServiceImpl implements RpTradePaymentQueryService {
	@Autowired
	private RpTradePaymentRecordDao rpTradePaymentRecordDao;

	@Autowired
	private RpTradePaymentOrderDao rpTradePaymentOrderDao;

	@Autowired
	private RpUserPayConfigService rpUserPayConfigService;

	/**
	 * 根�?��?�数查询交易记录List
	 *
	 * @param paramMap
	 * @return
	 */
	public List<RpTradePaymentRecord> listPaymentRecord(Map<String, Object> paramMap) {
		return rpTradePaymentRecordDao.listByColumn(paramMap);
	}

	/**
	 * 根�?�商户支付KEY �?�商户订�?��?� 查询支付结果
	 *
	 * @param payKey
	 *            商户支付KEY
	 * @param orderNo
	 *            商户订�?��?�
	 * @return
	 */
	@Override
	public OrderPayResultVo getPayResult(String payKey, String orderNo) {

		RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
		if (rpUserPayConfig == null) {
			throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
		}

		String merchantNo = rpUserPayConfig.getUserNo();// 商户编�?�
		RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo, orderNo);

		RpTradePaymentRecord rpTradePaymentRecord = rpTradePaymentRecordDao.getSuccessRecordByMerchantNoAndMerchantOrderNo(rpTradePaymentOrder.getMerchantNo(), rpTradePaymentOrder.getMerchantOrderNo());

		OrderPayResultVo orderPayResultVo = new OrderPayResultVo();// 返回结果
		if (rpTradePaymentOrder != null && TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {// 支付记录为空,或者支付状�?�?��?功
			orderPayResultVo.setStatus(PublicEnum.YES.name());// 设置支付状�?
			orderPayResultVo.setOrderPrice(rpTradePaymentOrder.getOrderAmount());// 设置支付订�?�
			orderPayResultVo.setProductName(rpTradePaymentOrder.getProductName());// 设置产�?�??称
			String url = getMerchantNotifyUrl(rpTradePaymentRecord, rpTradePaymentOrder, rpTradePaymentRecord.getReturnUrl(), TradeStatusEnum.SUCCESS);
			orderPayResultVo.setReturnUrl(url);
		}

		return orderPayResultVo;
	}


	private String getMerchantNotifyUrl(RpTradePaymentRecord rpTradePaymentRecord , RpTradePaymentOrder rpTradePaymentOrder , String sourceUrl , TradeStatusEnum tradeStatusEnum){

		RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByUserNo(rpTradePaymentRecord.getMerchantNo());
		if (rpUserPayConfig == null){
			throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"用户支付�?置有误");
		}

		Map<String , Object> paramMap = new HashMap<>();

		String payKey = rpUserPayConfig.getPayKey();// �?业支付KEY
		paramMap.put("payKey",payKey);
		String productName = rpTradePaymentRecord.getProductName(); // 商�?�??称
		paramMap.put("productName",productName);
		String orderNo = rpTradePaymentRecord.getMerchantOrderNo(); // 订�?�编�?�
		paramMap.put("orderNo",orderNo);
		BigDecimal orderPrice = rpTradePaymentRecord.getOrderAmount(); // 订�?�金�? , �?��?:元
		paramMap.put("orderPrice",orderPrice);
		String payWayCode = rpTradePaymentRecord.getPayWayCode(); // 支付方�?编�? 支付�?: ALIPAY  微信:WEIXIN
		paramMap.put("payWayCode",payWayCode);
		paramMap.put("tradeStatus",tradeStatusEnum);//交易状�?
		String orderDateStr = DateUtils.formatDate(rpTradePaymentOrder.getOrderDate(), "yyyyMMdd"); // 订�?�日期
		paramMap.put("orderDate",orderDateStr);
		String orderTimeStr = DateUtils.formatDate(rpTradePaymentOrder.getOrderTime(), "yyyyMMddHHmmss"); // 订�?�时间
		paramMap.put("orderTime",orderTimeStr);
		String remark = rpTradePaymentRecord.getRemark(); // 支付备注
		paramMap.put("remark",remark);
		String trxNo = rpTradePaymentRecord.getTrxNo();//支付�?水�?�
		paramMap.put("trxNo",trxNo);

		String field1 = rpTradePaymentOrder.getField1(); // 扩展字段1
		paramMap.put("field1",field1);
		String field2 = rpTradePaymentOrder.getField2(); // 扩展字段2
		paramMap.put("field2",field2);
		String field3 = rpTradePaymentOrder.getField3(); // 扩展字段3
		paramMap.put("field3",field3);
		String field4 = rpTradePaymentOrder.getField4(); // 扩展字段4
		paramMap.put("field4",field4);
		String field5 = rpTradePaymentOrder.getField5(); // 扩展字段5
		paramMap.put("field5",field5);

		String paramStr = MerchantApiUtil.getParamStr(paramMap);
		String sign = MerchantApiUtil.getSign(paramMap, rpUserPayConfig.getPaySecret());
		String notifyUrl = sourceUrl + "?" + paramStr + "&sign=" + sign;

		return notifyUrl;
	}

	/**
	 * 根�?�银行订�?��?�查询支付记录
	 *
	 * @param bankOrderNo
	 * @return
	 */
	public RpTradePaymentRecord getRecordByBankOrderNo(String bankOrderNo) {
		return rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
	}

	/**
	 * 根�?�支付�?水�?�查询支付记录
	 *
	 * @param trxNo
	 * @return
	 */
	public RpTradePaymentRecord getRecordByTrxNo(String trxNo){
		return rpTradePaymentRecordDao.getByTrxNo(trxNo);
	}

	/**
	 * 分页查询支付订�?�
	 *
	 * @param pageParam
	 * @param paymentOrderQueryParam
	 * @return
	 */
	@Override
	public PageBean<RpTradePaymentOrder> listPaymentOrderPage(PageParam pageParam, PaymentOrderQueryParam paymentOrderQueryParam) {

		Map<String , Object> paramMap = new HashMap<String , Object>();
		paramMap.put("merchantNo", paymentOrderQueryParam.getMerchantNo());//商户编�?�
		paramMap.put("merchantName", paymentOrderQueryParam.getMerchantName());//商户�??称
		paramMap.put("merchantOrderNo", paymentOrderQueryParam.getMerchantOrderNo());//商户订�?��?�
		paramMap.put("fundIntoType", paymentOrderQueryParam.getFundIntoType());//资金�?入类型
		paramMap.put("merchantOrderNo", paymentOrderQueryParam.getOrderDateBegin());//订�?�开始时间
		paramMap.put("merchantOrderNo", paymentOrderQueryParam.getOrderDateEnd());//订�?�结�?�时间
		paramMap.put("payTypeName", paymentOrderQueryParam.getPayTypeName());//支付类型
		paramMap.put("payWayName", paymentOrderQueryParam.getPayWayName());//支付类型
		paramMap.put("status", paymentOrderQueryParam.getStatus());//支付状�?

		if (paymentOrderQueryParam.getOrderDateBegin() != null){
			paramMap.put("orderDateBegin", paymentOrderQueryParam.getOrderDateBegin());//支付状�?
		}

		if (paymentOrderQueryParam.getOrderDateEnd() != null){
			paramMap.put("orderDateEnd", paymentOrderQueryParam.getOrderDateEnd());//支付状�?
		}

		return rpTradePaymentOrderDao.listPage(pageParam,paramMap);
	}

	/**
	 * 分页查询支付记录
	 *
	 * @param pageParam
	 * @param paymentOrderQueryParam
	 * @return
	 */
	@Override
	public PageBean<RpTradePaymentRecord> listPaymentRecordPage(PageParam pageParam, PaymentOrderQueryParam paymentOrderQueryParam) {
		Map<String , Object> paramMap = new HashMap<String , Object>();
		paramMap.put("merchantNo", paymentOrderQueryParam.getMerchantNo());//商户编�?�
		paramMap.put("merchantName", paymentOrderQueryParam.getMerchantName());//商户�??称
		paramMap.put("merchantOrderNo", paymentOrderQueryParam.getMerchantOrderNo());//商户订�?��?�
		paramMap.put("fundIntoType", paymentOrderQueryParam.getFundIntoType());//资金�?入类型
		paramMap.put("merchantOrderNo", paymentOrderQueryParam.getOrderDateBegin());//订�?�开始时间
		paramMap.put("merchantOrderNo", paymentOrderQueryParam.getOrderDateEnd());//订�?�结�?�时间
		paramMap.put("payTypeName", paymentOrderQueryParam.getPayTypeName());//支付类型
		paramMap.put("payWayName", paymentOrderQueryParam.getPayWayName());//支付类型
		paramMap.put("status", paymentOrderQueryParam.getStatus());//支付状�?

		if (paymentOrderQueryParam.getOrderDateBegin() != null){
			paramMap.put("orderDateBegin", paymentOrderQueryParam.getOrderDateBegin());//支付状�?
		}

		if (paymentOrderQueryParam.getOrderDateEnd() != null){
			paramMap.put("orderDateEnd", paymentOrderQueryParam.getOrderDateEnd());//支付状�?
		}

		return rpTradePaymentRecordDao.listPage(pageParam,paramMap);
	}

	/**
	 * 获�?�交易�?水报表
	 *
	 * @param merchantNo
	 * @return
	 */
	public List<Map<String, String>> getPaymentReport(String merchantNo){
		return rpTradePaymentRecordDao.getPaymentReport(merchantNo);
	}

	/**
	 * 获�?�交易方�?报表
	 *
	 * @param merchantNo
	 * @return
	 */
	public List<Map<String, String>> getPayWayReport(String merchantNo){
		return rpTradePaymentRecordDao.getPayWayReport(merchantNo);
	}
}
