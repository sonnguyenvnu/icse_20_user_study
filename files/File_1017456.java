package com.foxinmy.weixin4j.qy.token;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.type.URLConsts;
import com.foxinmy.weixin4j.token.TokenCreator;

/**
 * å¾®ä¿¡ä¼?ä¸šå?·åº”ç”¨æ??ä¾›å•†å‡­è¯?åˆ›å»º
 *
 * @className WeixinProviderTokenCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015å¹´1æœˆ10æ—¥
 * @since JDK 1.6
 * @see <a href= "http://work.weixin.qq.com/api/doc#11791/æœ?åŠ¡å•†çš„å‡­è¯?">æœ?åŠ¡å•†çš„å‡­è¯?</a>
 * @see com.foxinmy.weixin4j.model.Token
 */
public class WeixinProviderTokenCreator extends TokenCreator {

	private final String corpid;
	private final String providersecret;

	/**
	 *
	 * @param corpid
	 *            ä¼?ä¸šå?·ID
	 * @param providersecret
	 *            ä¼?ä¸šå?·æ??ä¾›å•†çš„secret
	 */
	public WeixinProviderTokenCreator(String corpid, String providersecret) {
		this.corpid = corpid;
		this.providersecret = providersecret;
	}

	@Override
	public String name() {
		return "qy_provider_token";
	}

	@Override
	public String uniqueid() {
		return corpid;
	}

	@Override
	public Token create() throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("corpid", corpid);
		obj.put("provider_secret", providersecret);
		WeixinResponse response = weixinExecutor.post(
				URLConsts.PROVIDER_TOKEN_URL, obj.toJSONString());
		obj = response.getAsJson();
		return new Token(obj.getString("provider_access_token"),
				obj.getLongValue("expires_in") * 1000l);
	}
}
