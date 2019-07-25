package com.foxinmy.weixin4j.qy.token;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.type.URLConsts;
import com.foxinmy.weixin4j.token.TokenCreator;

/**
 * å¾®ä¿¡ä¼?ä¸šå?·TOKENåˆ›å»º
 *
 * @className WeixinTokenCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015å¹´1æœˆ10æ—¥
 * @since JDK 1.6
 * @see <a href= "https://work.weixin.qq.com/api/doc#10013"> å¾®ä¿¡ä¼?ä¸šå?·èŽ·å?–tokenè¯´æ˜Ž</a>
 * @see com.foxinmy.weixin4j.model.Token
 */
public class WeixinTokenCreator extends TokenCreator {

	private final String corpid;
	private final String corpsecret;

	/**
	 *
	 * @param corpid
	 *            ä¼?ä¸šå?·ID
	 * @param corpsecret
	 *            ä¼?ä¸šå?·secret
	 */
	public WeixinTokenCreator(String corpid, String corpsecret) {
		this.corpid = corpid;
		this.corpsecret = corpsecret;
	}

	@Override
	public String name() {
		return "qy_token";
	}

	@Override
	public String uniqueid() {
		return corpid;
	}

	@Override
	public Token create() throws WeixinException {
		String tokenUrl = String.format(URLConsts.ASSESS_TOKEN_URL, corpid,
				corpsecret);
		WeixinResponse response = weixinExecutor.get(tokenUrl);
		JSONObject result = response.getAsJson();
		return new Token(result.getString("access_token"),
				result.getLongValue("expires_in") * 1000l);
	}
}
