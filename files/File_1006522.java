package com.jpay.weixin.api;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jpay.ext.kit.PaymentKit;
import com.jpay.ext.kit.StrKit;
import com.jpay.weixin.api.WxPayApi.TradeType;

/**
 * @author Javen 2017年5月22日
 */
public class WxPayApiConfig implements Serializable {
	private static final long serialVersionUID = -6447075676732210047L;

	private String appId;
	private String mchId;
	private String subAppId;
	private String subMchId;
	private String paternerKey;
	private String nonceStr;
	private String body;
	private String attach;
	private String transactionId;
	private String outTradeNo;
	private String totalFee;
	private String spbillCreateIp;
	private String notifyUrl;
	private TradeType tradeType;
	private String openId;
	private String subOpenId;
	private String authCode;
	private String sceneInfo;

	private String planId;
	private String contractCode;
	private String requestSerial;
	private String contractDisplayAccount;
	private String version;
	private String timestamp;
	private String returnApp;
	private String returnWeb;

	private String contractNotifyUrl;
	private String contractId;

	private PayModel payModel;
	private String profitSharing;
	private SignType signType;
	private String deviceInfo;
	private String detail;
	private String feeType;
	private String timeStart;
	private String timeExpire;
	private String goodsTag;
	private String limitPay;
	private String receipt;
	private String beginTime;
	private String endTime;
	private int offset;
	private int limit;
	
	private String deposit;
	private String faceCode;
	private String consumeFee;
	private String refundFee;
	private String refundFeeType;
	private String refundDesc;
	private String refundAccount;
	private String outRefundNo;
	private String refundId;
	private String id;
	private String name;
	private String areaCode;
	private String address;

	/**
	 * 分别对应商户模�?�?�?务商模�?
	 */
	public static enum PayModel {
		BUSINESSMODEL, SERVICEMODE
	}

	/**
	 * 签�??类型
	 */
	public static enum SignType {
		MD5, HMAC_SHA256
	}

	private WxPayApiConfig() {
	}

	public static WxPayApiConfig New() {
		return new WxPayApiConfig();
	}

	public Map<String, String> createMap() {
		Map<String, String> map = new HashMap<String, String>();

		if (getPayModel().equals(PayModel.SERVICEMODE)) {
			map.put("sub_mch_id", getSubMchId());
			if (StrKit.notBlank(getSubAppId())) {
				map.put("sub_appid", subAppId);
			}
		}

		/**
		 * openId和sub_openid�?�以选传其中之一，如果选择传sub_openid,则必须传sub_appid
		 */
		if (getTradeType().equals(TradeType.JSAPI)) {
			if (StrKit.notBlank(getSubAppId())) {
				map.put("sub_appid", subAppId);
				map.put("sub_openid", getSubOpenId());
			} else {
				map.put("openid", getOpenId());
			}
		}
		/**
		 * H5支付必填scene_info
		 */
		if (getTradeType().equals(TradeType.MWEB)) {
			if (StrKit.isBlank(getSceneInfo())) {
				throw new IllegalArgumentException("微信H5支付 scene_info �?能�?�时为空");
			}
			map.put("scene_info", getSceneInfo());
		}

		map.put("appid", getAppId());
		map.put("mch_id", getMchId());
		map.put("nonce_str", getNonceStr());
		map.put("body", getBody());
		map.put("out_trade_no", getOutTradeNo());
		map.put("total_fee", getTotalFee());
		map.put("spbill_create_ip", getSpbillCreateIp());

		map.put("trade_type", getTradeType().name());

		map.put("attach", getAttach());
		map.put("device_info", getDeviceInfo());
		map.put("detail", getDetail());
		map.put("fee_type", getFeeType());
		map.put("time_start", getTimeStart());
		map.put("time_expire", getTimeExpire());
		map.put("goods_tag", getGoodsTag());
		map.put("limit_pay", getLimitPay());
		map.put("receipt", getReceipt());
		map.put("profit_sharing", getProfitSharing());
		if (getTradeType().equals(TradeType.MICROPAY)) {
			map.put("auth_code", getAuthCode());
		} else {
			map.put("notify_url", getNotifyUrl());
		}
		map.put("deposit", getDeposit());
		map.put("consume_fee",getConsumeFee());
		map.put("id", getId());
		map.put("name", getName());
		map.put("area_code", getAreaCode());
		map.put("address", getAddress());
		map.put("refund_fee", getRefundFee());
		map.put("refund_fee_type", getRefundFeeType());
		map.put("refund_desc", getAddress());
		map.put("refund_account", getRefundAccount());
		map.put("out_refund_no", getOutRefundNo());
		map.put("refund_id", getRefundId());
		map.put("face_code", getFaceCode());
		return map;
	}

	/**
	 * 构建请求�?�数
	 * 
	 * @return Map<String, String>
	 */
	public Map<String, String> build() {
		Map<String, String> map = createMap();
		map.put("sign", PaymentKit.createSign(map, getPaternerKey()));
		return map;
	}
	/**
	 * 构建请求�?�数
	 * 
	 * @param signType
	 * @return Map<String, String>
	 * @throws Exception 
	 */
	public Map<String, String> build(SignType signType) throws Exception {
		Map<String, String> map = createMap();
		if(SignType.MD5 == signType) {
			map.put("sign_type", "MD5");
		}else {
			map.put("sign_type", "HMAC-SHA256");
		}
		map.put("sign", PaymentKit.createSign(map, getPaternerKey(), signType));
		return map;
	}

	/**
	 * 构建查询订�?��?�数
	 * 
	 * @return <Map<String, String>>
	 */
	public Map<String, String> orderQueryBuild() {
		Map<String, String> map = new HashMap<String, String>();
		if (getPayModel().equals(PayModel.SERVICEMODE)) {
			map.put("sub_mch_id", getSubMchId());
			map.put("sub_appid", getSubAppId());
		}

		map.put("appid", getAppId());
		map.put("mch_id", getMchId());

		if (StrKit.notBlank(getTransactionId())) {
			map.put("transaction_id", getTransactionId());
		} else {
			if (StrKit.isBlank(getOutTradeNo())) {
				throw new IllegalArgumentException("out_trade_no,transaction_id �?能�?�时为空");
			}
			map.put("out_trade_no", getOutTradeNo());
		}
		map.put("nonce_str", String.valueOf(System.currentTimeMillis()));
		map.put("sign", PaymentKit.createSign(map, getPaternerKey()));
		return map;
	}

	/**
	 * 构建申请签约Map
	 * 
	 * @return 申请签约Map
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> entrustwebBuild() throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", getAppId());
		map.put("mch_id", getMchId());
		map.put("plan_id", getPlanId());
		map.put("contract_code", getContractCode());
		map.put("request_serial", getRequestSerial());
		map.put("contract_display_account", getContractDisplayAccount());
		map.put("notify_url", getNotifyUrl());
		map.put("version", getVersion());
		map.put("timestamp", getTimestamp());
		map.put("sign", PaymentKit.createSign(map, getPaternerKey()));

		for (Entry<String, String> param : map.entrySet()) {
			String key = param.getKey();
			String value = param.getValue();
			value = PaymentKit.urlEncode(value);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * 构建支付中签约Map
	 * 
	 * @return 支付中签约Map
	 */
	public Map<String, String> contractorderBuild() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("appid", getAppId());
		map.put("mch_id", getMchId());
		map.put("contract_appid", getAppId());
		map.put("contract_mchid", getMchId());
		map.put("out_trade_no", getOutTradeNo());
		map.put("nonce_str", getNonceStr());
		map.put("body", getBody());
		map.put("attach", getAttach());
		map.put("notify_url", getNotifyUrl());
		map.put("total_fee", getTotalFee());
		map.put("spbill_create_ip", getSpbillCreateIp());
		map.put("trade_type", getTradeType().name());
		if (getTradeType().equals(TradeType.JSAPI)) {
			map.put("openid", getOpenId());
		}
		map.put("plan_id", getPlanId());
		map.put("contract_code", getContractCode());
		map.put("request_serial", getRequestSerial());
		map.put("contract_display_account", getContractDisplayAccount());
		map.put("contract_notify_url", getContractNotifyUrl());

		map.put("sign", PaymentKit.createSign(map, getPaternerKey()));

		return map;
	}

	/**
	 * 构建查询签约关系的Map
	 * 
	 * @return 查询签约关系的Map
	 */
	public Map<String, String> querycontractBuild() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("appid", getAppId());
		map.put("mch_id", getMchId());

		if (StrKit.notBlank(getPlanId())) {
			map.put("plan_id", getPlanId());
			map.put("contract_code", getContractCode());
		} else {
			map.put("contract_id", getContractId());
		}
		map.put("version", getVersion());
		map.put("sign", PaymentKit.createSign(map, getPaternerKey()));

		return map;
	}

	/**
	 * 构建申请扣款的Map
	 * 
	 * @return 申请扣款的Map
	 */
	public Map<String, String> pappayapplyBuild() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", getAppId());
		map.put("mch_id", getMchId());
		map.put("nonce_str", getNonceStr());
		map.put("body", getBody());
		map.put("attach", getAttach());
		map.put("out_trade_no", getOutTradeNo());
		map.put("total_fee", getTotalFee());
		map.put("spbill_create_ip", getSpbillCreateIp());
		map.put("notify_url", getNotifyUrl());
		map.put("trade_type", getTradeType().name());
		map.put("contract_id", getContractId());
		map.put("sign", PaymentKit.createSign(map, getPaternerKey()));
		return map;
	}

	public String getAppId() {
		if (StrKit.isBlank(appId))
			throw new IllegalArgumentException("appId 未被赋值");
		return appId;
	}

	public WxPayApiConfig setAppId(String appId) {
		if (StrKit.isBlank(appId))
			throw new IllegalArgumentException("appId 值�?能为空");
		this.appId = appId;
		return this;
	}

	public String getMchId() {
		if (StrKit.isBlank(mchId))
			throw new IllegalArgumentException("mchId 未被赋值");
		return mchId;
	}

	public WxPayApiConfig setMchId(String mchId) {
		if (StrKit.isBlank(mchId))
			throw new IllegalArgumentException("mchId 值�?能为空");
		this.mchId = mchId;
		return this;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public WxPayApiConfig setSubAppId(String subAppId) {
		if (StrKit.isBlank(subAppId))
			throw new IllegalArgumentException("subAppId 值�?能为空");
		this.subAppId = subAppId;
		return this;
	}

	public String getSubMchId() {
		if (StrKit.isBlank(subMchId))
			throw new IllegalArgumentException("subMchId 未被赋值");
		return subMchId;
	}

	public WxPayApiConfig setSubMchId(String subMchId) {
		if (StrKit.isBlank(subMchId))
			throw new IllegalArgumentException("subMchId 值�?能为空");
		this.subMchId = subMchId;
		return this;
	}

	public String getNonceStr() {
		if (StrKit.isBlank(nonceStr))
			nonceStr = String.valueOf(System.currentTimeMillis());
		return nonceStr;
	}

	public WxPayApiConfig setNonceStr(String nonceStr) {
		if (StrKit.isBlank(nonceStr))
			throw new IllegalArgumentException("nonceStr 值�?能为空");
		this.nonceStr = nonceStr;
		return this;
	}

	public String getBody() {
		if (StrKit.isBlank(body))
			throw new IllegalArgumentException("body 未被赋值");
		return body;
	}

	public WxPayApiConfig setBody(String body) {
		if (StrKit.isBlank(body))
			throw new IllegalArgumentException("body 值�?能为空");
		this.body = body;
		return this;
	}

	public String getAttach() {
		return attach;
	}

	public WxPayApiConfig setAttach(String attach) {
		if (StrKit.isBlank(attach))
			throw new IllegalArgumentException("attach 值�?能为空");
		this.attach = attach;
		return this;
	}

	public String getOutTradeNo() {
		if (StrKit.isBlank(outTradeNo))
			throw new IllegalArgumentException("outTradeNo 未被赋值");
		return outTradeNo;
	}

	public WxPayApiConfig setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		return this;
	}

	public String getTotalFee() {
		if (StrKit.isBlank(totalFee))
			throw new IllegalArgumentException("totalFee 未被赋值");
		return totalFee;
	}

	public WxPayApiConfig setTotalFee(String totalFee) {
		if (StrKit.isBlank(totalFee))
			throw new IllegalArgumentException("totalFee 值�?能为空");
		this.totalFee = totalFee;
		return this;
	}

	public String getSpbillCreateIp() {
		if (StrKit.isBlank(spbillCreateIp))
			throw new IllegalArgumentException("spbillCreateIp 未被赋值");
		return spbillCreateIp;
	}

	public WxPayApiConfig setSpbillCreateIp(String spbillCreateIp) {
		if (StrKit.isBlank(spbillCreateIp))
			throw new IllegalArgumentException("spbillCreateIp 值�?能为空");
		this.spbillCreateIp = spbillCreateIp;
		return this;
	}

	public String getNotifyUrl() {
		if (StrKit.isBlank(notifyUrl))
			throw new IllegalArgumentException("notifyUrl 未被赋值");
		return notifyUrl;
	}

	public WxPayApiConfig setNotifyUrl(String notifyUrl) {
		if (StrKit.isBlank(notifyUrl))
			throw new IllegalArgumentException("notifyUrl 值�?能为空");
		this.notifyUrl = notifyUrl;
		return this;
	}

	public TradeType getTradeType() {
		if (tradeType == null)
			throw new IllegalArgumentException("tradeType 未被赋值");
		return tradeType;
	}

	public WxPayApiConfig setTradeType(TradeType tradeType) {
		if (tradeType == null)
			throw new IllegalArgumentException("mchId 值�?能为空");
		this.tradeType = tradeType;
		return this;
	}

	public String getOpenId() {
		if (StrKit.isBlank(openId))
			throw new IllegalArgumentException("openId 未被赋值");
		return openId;
	}

	public WxPayApiConfig setOpenId(String openId) {
		if (StrKit.isBlank(openId))
			throw new IllegalArgumentException("openId 值�?能为空");
		this.openId = openId;
		return this;
	}

	public String getSubOpenId() {
		if (StrKit.isBlank(subOpenId))
			throw new IllegalArgumentException("subOpenId 未被赋值");
		return subOpenId;
	}

	public WxPayApiConfig setSubOpenId(String subOpenId) {
		if (StrKit.isBlank(subOpenId))
			throw new IllegalArgumentException("subOpenId 值�?能为空");
		this.subOpenId = subOpenId;
		return this;
	}

	public String getPaternerKey() {
		if (StrKit.isBlank(paternerKey))
			throw new IllegalArgumentException("paternerKey 未被赋值");
		return paternerKey;
	}

	public WxPayApiConfig setPaternerKey(String paternerKey) {
		if (StrKit.isBlank(paternerKey))
			throw new IllegalArgumentException("paternerKey 值�?能为空");
		this.paternerKey = paternerKey;
		return this;
	}

	public PayModel getPayModel() {
		if (payModel == null)
			payModel = PayModel.BUSINESSMODEL;
		return payModel;
	}

	public WxPayApiConfig setPayModel(PayModel payModel) {
		if (payModel == null)
			payModel = PayModel.BUSINESSMODEL;
		this.payModel = payModel;
		return this;
	}

	public SignType getSignType() {
		if (signType == null)
			signType = SignType.MD5;
		return signType;
	}

	public WxPayApiConfig setSignType(SignType signType) {
		if (signType == null)
			signType = SignType.MD5;
		this.signType = signType;
		return this;
	}

	public String getAuthCode() {
		if (StrKit.isBlank(authCode))
			throw new IllegalArgumentException("authCode 未被赋值");
		return authCode;
	}

	public WxPayApiConfig setAuthCode(String authCode) {
		if (StrKit.isBlank(paternerKey))
			throw new IllegalArgumentException("authCode 值�?能为空");
		this.authCode = authCode;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public WxPayApiConfig setTransactionId(String transactionId) {
		if (StrKit.isBlank(transactionId))
			throw new IllegalArgumentException("transactionId 值�?能为空");
		this.transactionId = transactionId;
		return this;
	}

	public String getSceneInfo() {
		return sceneInfo;
	}

	public WxPayApiConfig setSceneInfo(String sceneInfo) {
		this.sceneInfo = sceneInfo;
		return this;
	}

	public String getPlanId() {
		if (StrKit.isBlank(planId))
			throw new IllegalArgumentException("planId 未被赋值");
		return planId;
	}

	public WxPayApiConfig setPlanId(String planId) {
		if (StrKit.isBlank(planId))
			throw new IllegalArgumentException("planId 值�?能为空");
		this.planId = planId;
		return this;
	}

	public String getContractCode() {
		if (StrKit.isBlank(contractCode))
			throw new IllegalArgumentException("contractCode 未被赋值");
		return contractCode;
	}

	public WxPayApiConfig setContractCode(String contractCode) {
		if (StrKit.isBlank(contractCode))
			throw new IllegalArgumentException("contractCode 值�?能为空");
		this.contractCode = contractCode;
		return this;
	}

	public String getRequestSerial() {
		if (StrKit.isBlank(requestSerial))
			throw new IllegalArgumentException("requestSerial 未被赋值");
		return requestSerial;
	}

	public WxPayApiConfig setRequestSerial(String requestSerial) {
		if (StrKit.isBlank(requestSerial))
			throw new IllegalArgumentException("requestSerial 值�?能为空");
		this.requestSerial = requestSerial;
		return this;
	}

	public String getContractDisplayAccount() {
		if (StrKit.isBlank(contractDisplayAccount))
			throw new IllegalArgumentException("contractDisplayAccount 未被赋值");
		return contractDisplayAccount;
	}

	public WxPayApiConfig setContractDisplayAccount(String contractDisplayAccount) {
		if (StrKit.isBlank(contractDisplayAccount))
			throw new IllegalArgumentException("contractDisplayAccount 值�?能为空");
		this.contractDisplayAccount = contractDisplayAccount;
		return this;
	}

	public String getVersion() {
		if (StrKit.isBlank(version))
			version = "1.0";
		return version;
	}

	public WxPayApiConfig setVersion(String version) {
		if (StrKit.isBlank(version))
			throw new IllegalArgumentException("version 值�?能为空");
		this.version = version;
		return this;
	}

	public String getTimestamp() {
		if (StrKit.isBlank(timestamp)) {
			timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		}
		return timestamp;
	}

	public WxPayApiConfig setTimestamp(String timestamp) {
		if (StrKit.isBlank(timestamp))
			throw new IllegalArgumentException("timestamp 值�?能为空");
		if (timestamp.length() != 10)
			throw new IllegalArgumentException("timestamp 值必须为10�?");
		this.timestamp = timestamp;
		return this;
	}

	public String getReturnApp() {
		return returnApp;
	}

	public WxPayApiConfig setReturnApp(String returnApp) {
		this.returnApp = returnApp;
		return this;
	}

	public String getReturnWeb() {
		return returnWeb;
	}

	public WxPayApiConfig setReturnWeb(String returnWeb) {
		this.returnWeb = returnWeb;
		return this;
	}

	public String getContractNotifyUrl() {
		if (StrKit.isBlank(contractNotifyUrl))
			throw new IllegalArgumentException("contractNotifyUrl 未被赋值");
		return contractNotifyUrl;
	}

	public WxPayApiConfig setContractNotifyUrl(String contractNotifyUrl) {
		this.contractNotifyUrl = contractNotifyUrl;
		return this;
	}

	public String getContractId() {
		if (StrKit.isBlank(contractId))
			throw new IllegalArgumentException("contractId 未被赋值");
		return contractId;
	}

	public WxPayApiConfig setContractId(String contractId) {
		this.contractId = contractId;
		return this;
	}

	public String getProfitSharing() {
		return profitSharing;
	}

	public WxPayApiConfig setProfitSharing(String profitSharing) {
		this.profitSharing = profitSharing;
		return this;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public WxPayApiConfig setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public WxPayApiConfig setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public String getFeeType() {
		return feeType;
	}

	public WxPayApiConfig setFeeType(String feeType) {
		this.feeType = feeType;
		return this;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public WxPayApiConfig setTimeStart(String timeStart) {
		this.timeStart = timeStart;
		return this;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public WxPayApiConfig setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
		return this;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public WxPayApiConfig setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
		return this;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public WxPayApiConfig setLimitPay(String limitPay) {
		this.limitPay = limitPay;
		return this;
	}

	public String getReceipt() {
		return receipt;
	}

	public WxPayApiConfig setReceipt(String receipt) {
		this.receipt = receipt;
		return this;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public WxPayApiConfig setBeginTime(String beginTime) {
		this.beginTime = beginTime;
		return this;
	}

	public String getEndTime() {
		return endTime;
	}

	public WxPayApiConfig setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public int getOffset() {
		return offset;
	}

	public WxPayApiConfig setOffset(int offset) {
		this.offset = offset;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public WxPayApiConfig setLimit(int limit) {
		this.limit = limit;
		return this;
	}

	public String getDeposit() {
		return deposit;
	}

	public WxPayApiConfig setDeposit(String deposit) {
		this.deposit = deposit;
		return this;
	}

	public String getFaceCode() {
		return faceCode;
	}

	public WxPayApiConfig setFaceCode(String faceCode) {
		this.faceCode = faceCode;
		return this;
	}

	public String getId() {
		return id;
	}

	public WxPayApiConfig setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public WxPayApiConfig setName(String name) {
		this.name = name;
		return this;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public WxPayApiConfig setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public WxPayApiConfig setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getConsumeFee() {
		return consumeFee;
	}

	public WxPayApiConfig setConsumeFee(String consumeFee) {
		this.consumeFee = consumeFee;
		return this;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public WxPayApiConfig setRefundFee(String refundFee) {
		this.refundFee = refundFee;
		return this;
	}

	public String getRefundFeeType() {
		return refundFeeType;
	}

	public WxPayApiConfig setRefundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
		return this;
	}

	public String getRefundDesc() {
		return refundDesc;
	}

	public WxPayApiConfig setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
		return this;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public WxPayApiConfig setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
		return this;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public WxPayApiConfig setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
		return this;
	}

	public String getRefundId() {
		return refundId;
	}

	public WxPayApiConfig setRefundId(String refundId) {
		this.refundId = refundId;
		return this;
	}
	
}
