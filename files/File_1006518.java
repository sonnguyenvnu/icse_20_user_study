package com.jpay.weixin.api;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.jpay.ext.kit.PaymentKit;
import com.jpay.util.HttpUtils;

/**
 * 
 * @author Javen 2017年4月15日 �?务商模�?�?商户模�?接�?�相�?��?�是请求�?�数�?�?�
 */
public class WxPayApi {
	// 统一下�?�接�?�
	private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 订�?�查询
	private static final String ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 关闭订�?�
	private static final String CLOSEORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// 撤销订�?�
	private static final String REVERSE_URL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
	// 申请退款
	private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 查询退款
	private static final String REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 下载对账�?�
	private static final String DOWNLOADBILLY_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	// 交易�?障
	private static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	// 转�?�短链接
	private static final String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	// 授�?��?查询openId接�?�
	private static final String AUTHCODETOOPENID_URL = "https://api.mch.weixin.qq.com/tools/authcodetoopenid";
	// 刷�?�支付
	private static final String MICROPAY_URL = "https://api.mch.weixin.qq.com/pay/micropay";
	// �?业付款
	private static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	// 查询�?业付款
	private static final String GETTRANSFERINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
	// �?业付款到银行
	private static final String PAY_BANK_URL = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";
	// 查询�?业付款
	private static final String QUERY_BANK_URL = "https://api.mch.weixin.qq.com/mmpaysptrans/query_bank";
	// 获�?�RSA加密公钥
	private static final String GETPUBLICKEY_URL = "https://fraud.mch.weixin.qq.com/risk/getpublickey";
	// 申请签约
	private static final String ENTRUSTWEB_URL = "https://api.mch.weixin.qq.com/papay/entrustweb";
	// 支付中签约接�?�
	private static final String CONTRACTORDER_URL = "https://api.mch.weixin.qq.com/pay/contractorder";
	// 查询签约关系
	private static final String QUERYCONTRACT_URL = "https://api.mch.weixin.qq.com/papay/querycontract";
	// 申请扣款
	private static final String PAPPAYAPPLY_URL = "https://api.mch.weixin.qq.com/pay/pappayapply";
	// 申请解约
	private static final String DELETECONTRACT_URL = "https://api.mch.weixin.qq.com/papay/deletecontract";
	// 查询签约关系对账�?�
	private static final String CONTRACTBILL_URL = "https://api.mch.weixin.qq.com/papay/contractbill";
	// 代扣查询订�?�
	private static final String PAPORDERQUERYL_URL = "https://api.mch.weixin.qq.com/pay/paporderquery";
	// 分账请求
	private static final String PROFITSHARING_URL = "https://api.mch.weixin.qq.com/secapi/pay/profitsharing";
	// 查询分账结果
	private static final String PROFITSHARINGQUERY_URL = "https://api.mch.weixin.qq.com/pay/profitsharingquery";
	// 添加分账接收方
	private static final String PROFITSHARINGADDRECEIVER_URL = "https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver";
	// 删除分账接收方
	private static final String PROFITSHARINGREMOVERECEIVER_URL = "https://api.mch.weixin.qq.com/pay/profitsharingremovereceiver";
	// 完结分账
	private static final String PROFITSHARINGFINISH_URL = "https://api.mch.weixin.qq.com/secapi/pay/profitsharingfinish";
	// �?�放代金券
	private static final String SEND_COUPON_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/send_coupon";
	// 查询代金券批次
	private static final String QUERY_COUPON_STOCK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/query_coupon_stock";
	// 查询代金券信�?�
	private static final String QUERY_COUPONSINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/querycouponsinfo";
	// 拉�?�订�?�评价数�?�
	private static final String BATCH_QUERY_COMMENT_URL = "https://api.mch.weixin.qq.com/billcommentsp/batchquerycomment";
	// 支付押金（人脸支付）
	private static final String DEPOSIT_FACEPAY_URL = "https://api.mch.weixin.qq.com/deposit/facepay";
	// 支付押金（付款�?支付）
	private static final String DEPOSIT_MICROPAY_URL = "https://api.mch.weixin.qq.com/deposit/micropay";
	// 查询订�?�
	private static final String DEPOSIT_ORDERQUERY_URL = "https://api.mch.weixin.qq.com/deposit/orderquery";
	// 撤销订�?�
	private static final String DEPOSIT_REVERSE_URL = "https://api.mch.weixin.qq.com/deposit/reverse";
	// 消费押金
	private static final String DEPOSIT_CONSUME_URL = "https://api.mch.weixin.qq.com/deposit/consume";
	// 申请退款（押金）
	private static final String DEPOSIT_REFUND_URL = "https://api.mch.weixin.qq.com/deposit/refund";
	// 查询退款（押金）
	private static final String DEPOSIT_REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/deposit/refundquery";

	// 获�?�沙箱秘钥
	private static final String GETSINGKEY = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
	// 统一下�?�接�?�
	private static final String UNIFIEDORDER_SANDBOXNEW_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
	// 刷�?�支付
	private static final String MICROPAY_SANDBOXNEW_RUL = "https://api.mch.weixin.qq.com/sandboxnew/pay/micropay";
	// 订�?�查询
	private static final String ORDERQUERY_SANDBOXNEW_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery";
	// 申请退款
	private static final String REFUND_SANDBOXNEW_URL = "https://api.mch.weixin.qq.com/sandboxnew/secapi/pay/refund";
	// 查询退款
	private static final String REFUNDQUERY_SANDBOXNEW_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/refundquery";
	// 下载对账�?�
	private static final String DOWNLOADBILLY_SANDBOXNEW_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/downloadbill";

	private WxPayApi() {
	}

	/**
	 * 交易类型枚举 统一下�?�接�?�trade_type的传�?��?��?�考这里 JSAPI--公众�?�支付�?�?程�?支付 NATIVE--原生扫�?支付 APP--APP支付
	 * MWEB--WAP支付 MICROPAY--刷�?�支付,刷�?�支付有�?�独的支付接�?�，�?调用统一下�?�接�?�
	 */
	public static enum TradeType {
		JSAPI, NATIVE, APP, WAP, MICROPAY, MWEB, PAP
	}

	/**
	 * 获�?�验签秘钥API
	 * 
	 * @param mch_id
	 *            商户�?�
	 * @param partnerKey
	 *            密钥
	 * @return {String}
	 */
	public static String getsignkey(String mch_id, String partnerKey) {
		Map<String, String> map = new HashMap<String, String>();
		String nonce_str = String.valueOf(System.currentTimeMillis());
		map.put("mch_id", mch_id);
		map.put("nonce_str", nonce_str);
		map.put("sign", PaymentKit.createSign(map, partnerKey));
		return doPost(GETSINGKEY, map);
	}

	/**
	 * 统一下�?�
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/native_sl.php?chapter=9_1
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	 * 
	 * @param isSandbox
	 *            是�?�是沙盒环境
	 * @param params
	 * @return {String}
	 */
	public static String pushOrder(boolean isSandbox, Map<String, String> params) {
		if (isSandbox)
			return doPost(UNIFIEDORDER_SANDBOXNEW_URL, params);
		return doPost(UNIFIEDORDER_URL, params);
	}

	/**
	 * 订�?�查询
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_2
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_2
	 * 
	 * @param isSandbox
	 *            是�?�是沙盒环境
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String orderQuery(boolean isSandbox, Map<String, String> params) {
		if (isSandbox)
			return doPost(ORDERQUERY_SANDBOXNEW_URL, params);
		return doPost(ORDERQUERY_URL, params);
	}

	/**
	 * 关闭订�?�
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_3
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3
	 * 
	 * @param params
	 * @return {String}
	 */
	public static String closeOrder(Map<String, String> params) {
		return doPost(CLOSEORDER_URL, params);
	}

	/**
	 * 撤销订�?�
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_11&index=3
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String orderReverse(Map<String, String> params, String certPath, String certPass) {
		return doPostSSL(REVERSE_URL, params, certPath, certPass);
	}

	public static String orderReverse(Map<String, String> params, InputStream certFile, String certPass) {
		return doPostSSL(REVERSE_URL, params, certFile, certPass);
	}

	/**
	 * 申请退款
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_4
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_4
	 * 
	 * @param isSandbox
	 *            是�?�是沙盒环境
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String orderRefund(boolean isSandbox, Map<String, String> params, String certPath, String certPass) {
		if (isSandbox)
			return doPostSSL(REFUND_SANDBOXNEW_URL, params, certPath, certPass);
		return doPostSSL(REFUND_URL, params, certPath, certPass);
	}

	public static String orderRefund(boolean isSandbox, Map<String, String> params, InputStream certFile,
			String certPass) {
		if (isSandbox)
			return doPostSSL(REFUND_SANDBOXNEW_URL, params, certFile, certPass);
		return doPostSSL(REFUND_URL, params, certFile, certPass);
	}

	/**
	 * 查询退款
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_5
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_5
	 * 
	 * @param isSandbox
	 *            是�?�是沙盒环境
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String orderRefundQuery(boolean isSandbox, Map<String, String> params) {
		if (isSandbox)
			return doPost(REFUNDQUERY_SANDBOXNEW_URL, params);
		return doPost(REFUNDQUERY_URL, params);
	}

	/**
	 * 下载对账�?�
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_6
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_6
	 * 
	 * @param isSandbox
	 *            是�?�是沙盒环境
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String downloadBill(boolean isSandbox, Map<String, String> params) {
		if (isSandbox)
			return doPost(DOWNLOADBILLY_SANDBOXNEW_URL, params);
		return doPost(DOWNLOADBILLY_URL, params);
	}

	/**
	 * 交易�?障
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_14&index=7
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_14&index=7
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String orderReport(Map<String, String> params) {
		return doPost(REPORT_URL, params);
	}

	/**
	 * 转�?�短链接
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_9&index=8
	 * 商户模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_9&index=8
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String toShortUrl(Map<String, String> params) {
		return doPost(SHORT_URL, params);
	}

	/**
	 * 授�?��?查询openId
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_12&index=9
	 * 商户模�?接入文档:
	 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String authCodeToOpenid(Map<String, String> params) {
		return doPost(AUTHCODETOOPENID_URL, params);
	}

	/**
	 * 刷�?�支付
	 * �?务商模�?接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_10&index=1
	 * 商户模�?接入文档:
	 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
	 * 
	 * @param isSandbox
	 *            是�?�是沙盒环境
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String micropay(boolean isSandbox, Map<String, String> params) {
		if (isSandbox)
			return WxPayApi.doPost(MICROPAY_SANDBOXNEW_RUL, params);
		return WxPayApi.doPost(MICROPAY_URL, params);
	}

	/**
	 * �?业付款到零钱
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPassword
	 *            �?书密�?
	 * @return {String}
	 */
	public static String transfers(Map<String, String> params, String certPath, String certPassword) {
		return WxPayApi.doPostSSL(TRANSFERS_URL, params, certPath, certPassword);
	}

	public static String transfers(Map<String, String> params, InputStream certFile, String certPassword) {
		return WxPayApi.doPostSSL(TRANSFERS_URL, params, certFile, certPassword);
	}

	/**
	 * 查询�?业付款到零钱
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPassword
	 *            �?书密�?
	 * @return {String}
	 */
	public static String getTransferInfo(Map<String, String> params, String certPath, String certPassword) {
		return WxPayApi.doPostSSL(GETTRANSFERINFO_URL, params, certPath, certPassword);
	}

	public static String getTransferInfo(Map<String, String> params, InputStream certFile, String certPassword) {
		return WxPayApi.doPostSSL(GETTRANSFERINFO_URL, params, certFile, certPassword);
	}

	/**
	 * �?业付款到银行
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPassword
	 *            �?书密�?
	 * @return {String}
	 */
	public static String payBank(Map<String, String> params, String certPath, String certPassword) {
		return WxPayApi.doPostSSL(PAY_BANK_URL, params, certPath, certPassword);
	}

	public static String payBank(Map<String, String> params, InputStream certFile, String certPassword) {
		return WxPayApi.doPostSSL(PAY_BANK_URL, params, certFile, certPassword);
	}

	/**
	 * 查询�?业付款到银行
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPassword
	 *            �?书密�?
	 * @return {String}
	 */
	public static String queryBank(Map<String, String> params, String certPath, String certPassword) {
		return WxPayApi.doPostSSL(QUERY_BANK_URL, params, certPath, certPassword);
	}

	public static String queryBank(Map<String, String> params, InputStream certFile, String certPassword) {
		return WxPayApi.doPostSSL(QUERY_BANK_URL, params, certFile, certPassword);
	}

	/**
	 * 获�?�RSA加密公钥
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPassword
	 *            �?书密�?
	 * @return {String}
	 */
	public static String getPublicKey(Map<String, String> params, String certPath, String certPassword) {
		return WxPayApi.doPostSSL(GETPUBLICKEY_URL, params, certPath, certPassword);
	}

	public static String getPublicKey(Map<String, String> params, InputStream certFile, String certPassword) {
		return WxPayApi.doPostSSL(GETPUBLICKEY_URL, params, certFile, certPassword);
	}

	/**
	 * 商户模�?下 扫�?模�?一之生�?二维�?
	 * 
	 * @param appid
	 * @param mch_id
	 * @param product_id
	 * @param partnerKey
	 * @param isToShortUrl
	 *            是�?�转化为短连接
	 * @return {String}
	 */
	public static String getCodeUrl(String appid, String mch_id, String product_id, String partnerKey,
			boolean isToShortUrl) {
		String url = "weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXX&time_stamp=XXXXX&nonce_str=XXXXX";
		String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
		String nonceStr = Long.toString(System.currentTimeMillis());
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("product_id", product_id);
		packageParams.put("time_stamp", timeStamp);
		packageParams.put("nonce_str", nonceStr);
		String packageSign = PaymentKit.createSign(packageParams, partnerKey);
		String qrCodeUrl = PaymentKit.replace(url, "XXXXX", packageSign, appid, mch_id, product_id, timeStamp,
				nonceStr);
		if (isToShortUrl) {
			String shortResult = WxPayApi
					.toShortUrl(PaymentKit.buildShortUrlParasMap(appid, null, mch_id, null, qrCodeUrl, partnerKey));
			Map<String, String> shortMap = PaymentKit.xmlToMap(shortResult);
			String return_code = shortMap.get("return_code");
			if (PaymentKit.codeIsOK(return_code)) {
				String result_code = shortMap.get("result_code");
				if (PaymentKit.codeIsOK(result_code)) {
					qrCodeUrl = shortMap.get("short_url");
				}
			}
		}

		return qrCodeUrl;
	}

	/**
	 * 申请签约 https://pay.weixin.qq.com/wiki/doc/api/pap.php?chapter=18_1&index=1
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String entrustweb(Map<String, String> params) {
		return doGet(ENTRUSTWEB_URL, params);
	}

	/**
	 * 支付中签约 https://pay.weixin.qq.com/wiki/doc/api/pap.php?chapter=18_13&index=2
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String contractorder(Map<String, String> params) {
		return doPost(CONTRACTORDER_URL, params);
	}

	/**
	 * 查询签约关系 https://pay.weixin.qq.com/wiki/doc/api/pap.php?chapter=18_2&index=3
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String querycontract(Map<String, String> params) {
		return doPost(QUERYCONTRACT_URL, params);
	}

	/**
	 * 申请扣款
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String pappayapply(Map<String, String> params) {
		return doPost(PAPPAYAPPLY_URL, params);
	}

	/**
	 * 申请解约
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String deletecontract(Map<String, String> params) {
		return doPost(DELETECONTRACT_URL, params);
	}

	/**
	 * 查询签约关系对账�?�
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String contractbill(Map<String, String> params) {
		return doPost(CONTRACTBILL_URL, params);
	}

	/**
	 * 代扣订�?�查询
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String paporderquery(Map<String, String> params) {
		return doPost(PAPORDERQUERYL_URL, params);
	}

	/**
	 * 分账请求
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String profitsharing(Map<String, String> params, String certPath, String certPassword) {
		return doPostSSL(PROFITSHARING_URL, params, certPath, certPassword);
	}

	public static String profitsharing(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(PROFITSHARING_URL, params, certFile, certPassword);
	}

	/**
	 * 查询分账结果
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String profitsharingquery(Map<String, String> params) {
		return doPost(PROFITSHARINGQUERY_URL, params);
	}

	/**
	 * 添加分账接收方
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String profitsharingaddreceiver(Map<String, String> params) {
		return doPost(PROFITSHARINGADDRECEIVER_URL, params);
	}

	/**
	 * 删除分账接收方
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String profitsharingremovereceiver(Map<String, String> params) {
		return doPost(PROFITSHARINGREMOVERECEIVER_URL, params);
	}

	/**
	 * 完结分账
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String profitsharingfinish(Map<String, String> params, String certPath, String certPassword) {
		return doPostSSL(PROFITSHARINGFINISH_URL, params, certPath, certPassword);
	}

	public static String profitsharingfinish(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(PROFITSHARINGFINISH_URL, params, certFile, certPassword);
	}

	/**
	 * �?�放代金券
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String sendCoupon(Map<String, String> params, String certPath, String certPassword) {
		return doPostSSL(SEND_COUPON_URL, params, certPath, certPassword);
	}

	public static String sendCoupon(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(SEND_COUPON_URL, params, certFile, certPassword);
	}

	/**
	 * 查询代金券批次
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String queryCouponStock(Map<String, String> params) {
		return doPost(QUERY_COUPON_STOCK_URL, params);
	}

	/**
	 * 查询代金券信�?�
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String queryCouponsInfo(Map<String, String> params) {
		return doPost(QUERY_COUPONSINFO_URL, params);
	}

	/**
	 * 拉�?�订�?�评价数�?�
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String batchquerycomment(Map<String, String> params, String certPath, String certPassword) {
		return doPostSSL(BATCH_QUERY_COMMENT_URL, params, certPath, certPassword);
	}

	public static String batchquerycomment(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(BATCH_QUERY_COMMENT_URL, params, certFile, certPassword);
	}

	/**
	 * 支付押金（人脸支付）
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String depositFacepay(Map<String, String> params) {
		return doPost(DEPOSIT_FACEPAY_URL, params);
	}

	/**
	 * 支付押金（付款�?支付）
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String depositMicropay(Map<String, String> params) {
		return doPost(DEPOSIT_MICROPAY_URL, params);
	}

	/**
	 * 查询订�?�
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String depositOrderQuery(Map<String, String> params) {
		return doPost(DEPOSIT_ORDERQUERY_URL, params);
	}

	/**
	 * 撤销订�?�
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String depositReverse(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(DEPOSIT_REVERSE_URL, params, certFile, certPassword);
	}

	/**
	 * 消费押金
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String depositConsume(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(DEPOSIT_CONSUME_URL, params, certFile, certPassword);
	}

	/**
	 * 申请退款（押金）
	 * 
	 * @param params
	 *            请求�?�数
	 * @param certPath
	 *            �?书文件目录
	 * @param certPass
	 *            �?书密�?
	 * @return {String}
	 */
	public static String depositRefund(Map<String, String> params, InputStream certFile, String certPassword) {
		return doPostSSL(DEPOSIT_REFUND_URL, params, certFile, certPassword);
	}

	/**
	 * 查询退款（押金）
	 * 
	 * @param params
	 *            请求�?�数
	 * @return {String}
	 */
	public static String depositRefundQuery(Map<String, String> params) {
		return doPost(DEPOSIT_REFUNDQUERY_URL, params);
	}

	public static String doGet(String url, Map<String, String> params) {
		return HttpUtils.get(url, params);
	}

	public static String doPost(String url, Map<String, String> params) {
		return HttpUtils.post(url, PaymentKit.toXml(params));
	}

	public static String doPostSSL(String url, Map<String, String> params, String certPath, String certPass) {
		return HttpUtils.postSSL(url, PaymentKit.toXml(params), certPath, certPass);
	}

	public static String doPostSSL(String url, Map<String, String> params, InputStream certFile, String certPass) {
		return HttpUtils.postSSL(url, PaymentKit.toXml(params), certFile, certPass);
	}

}
