package com.foxinmy.weixin4j.qy.suite;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.type.URLConsts;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;

/**
 * 微信�?业�?�应用套件预授�?��?创建
 *
 * @className WeixinSuitePreCodeCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年6月17日
 * @since JDK 1.6
 * @see <a href= "http://work.weixin.qq.com/api/doc#10975/获�?�预授�?��?">
 *      获�?�应用套件预授�?��?</a>
 * @see com.foxinmy.weixin4j.model.Token
 */
public class WeixinSuitePreCodeCreator extends TokenCreator {

	private final TokenManager suiteTokenManager;
	private final String suiteId;

	/**
	 *
	 * @param suiteTokenManager
	 *            应用套件的token
	 * @param suiteId
	 *            应用套件ID
	 */
	public WeixinSuitePreCodeCreator(TokenManager suiteTokenManager,
			String suiteId) {
		this.suiteTokenManager = suiteTokenManager;
		this.suiteId = suiteId;
	}

	@Override
	public String name() {
		return "qy_suite_precode";
	}

	@Override
	public String uniqueid() {
		return suiteId;
	}

	@Override
	public Token create() throws WeixinException {
		WeixinResponse response = weixinExecutor.post(
				String.format(URLConsts.SUITE_PRE_CODE_URL,
						suiteTokenManager.getAccessToken()),
				String.format("{\"suite_id\":\"%s\"}", suiteId));
		JSONObject result = response.getAsJson();
		return new Token(result.getString("pre_auth_code"),
				result.getLongValue("expires_in") * 1000l);
	}
}
