package com.foxinmy.weixin4j.qy.suite;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.type.URLConsts;
import com.foxinmy.weixin4j.token.PerTicketManager;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;

/**
 * å¾®ä¿¡ä¼?ä¸šå?·tokenåˆ›å»º(æ°¸ä¹…æŽˆæ?ƒç ?)
 *
 * @className WeixinTokenSuiteCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015å¹´6æœˆ17æ—¥
 * @since JDK 1.6
 * @see <a href= "http://work.weixin.qq.com/api/doc#10975/èŽ·å?–ä¼?ä¸šæ°¸ä¹…æŽˆæ?ƒç ?">
 *      èŽ·å?–ä¼?ä¸šå?·access_token</a>
 * @see com.foxinmy.weixin4j.model.Token
 */
public class WeixinTokenSuiteCreator extends TokenCreator {

	private final PerTicketManager perTicketManager;
	private final TokenManager suiteTokenManager;

	/**
	 *
	 * @param perTicketManager
	 *            ç¬¬ä¸‰æ–¹å¥—ä»¶æ°¸ä¹…æŽˆæ?ƒç ?
	 * @param suiteTokenManager
	 *            ç¬¬ä¸‰æ–¹å¥—ä»¶å‡­è¯?token
	 */
	public WeixinTokenSuiteCreator(PerTicketManager perTicketManager,
			TokenManager suiteTokenManager) {
		this.perTicketManager = perTicketManager;
		this.suiteTokenManager = suiteTokenManager;
	}

	@Override
	public String name() {
		return String.format("qy_token_suite_%s_%s",
				perTicketManager.getThirdId(), perTicketManager.getAuthAppId());
	}

	@Override
	public String uniqueid() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Token create() throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("suite_id", perTicketManager.getThirdId());
		obj.put("auth_corpid", perTicketManager.getAuthAppId());
		obj.put("permanent_code", perTicketManager.getAccessTicket());
		WeixinResponse response = weixinExecutor
				.post(String.format(URLConsts.TOKEN_SUITE_URL,
						suiteTokenManager.getAccessToken()), obj.toJSONString());
		obj = response.getAsJson();
		return new Token(obj.getString("access_token"),
				obj.getLongValue("expires_in") * 1000l);
	}
}
