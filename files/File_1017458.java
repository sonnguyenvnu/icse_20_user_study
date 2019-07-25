package com.foxinmy.weixin4j.qy.token;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.type.URLConsts;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.type.TicketType;

/**
 * 微信�?业�?�TICKET创建(包括jsticket�?其它JSSDK所需的ticket的创建
 *
 * @className WeixinTicketCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年12月25日
 * @since JDK 1.6
 * @see <a href= "https://work.weixin.qq.com/api/doc#10029/附录1-JS-SDK使用�?��?签�??算法">JSTICKET</a>
 */
public class WeixinTicketCreator extends TokenCreator {

	private final TicketType ticketType;
	private final TokenManager weixinTokenManager;

	/**
	 * @param ticketType
	 *            票�?�类型
	 * @param weixinTokenManager
	 *            <font color="red">�?业�?�的access_token</font>
	 */
	public WeixinTicketCreator(TicketType ticketType,
			TokenManager weixinTokenManager) {
		this.ticketType = ticketType;
		this.weixinTokenManager = weixinTokenManager;
	}

	@Override
	public String name() {
		return String.format("qy_ticket_%s", ticketType.name());
	}

	@Override
	public String uniqueid() {
		return weixinTokenManager.getWeixinId();
	}

	@Override
	public Token create() throws WeixinException {
		WeixinResponse response = null;
		if (ticketType == TicketType.jsapi) {
			response = weixinExecutor.get(String.format(
					URLConsts.JS_TICKET_URL, weixinTokenManager.getCache()
							.getAccessToken()));
		} else {
			response = weixinExecutor.get(String.format(
					URLConsts.SUITE_TICKET_URL, weixinTokenManager.getCache()
							.getAccessToken(), ticketType.name()));
		}
		JSONObject result = response.getAsJson();
		return new Token(result.getString("ticket"),
				result.getLong("expires_in") * 1000l).pushExtra("group_id",
				result.getString("group_id"));
	}
}
