package com.foxinmy.weixin4j.mp.component;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.mp.type.URLConsts;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;

/**
 * 微信开放平�?�应用组件预授�?��?创建
 *
 * @className WeixinComponentPreCodeCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2016年7月5日
 * @since JDK 1.6
 */
public class WeixinComponentPreCodeCreator extends TokenCreator {

    private final TokenManager componentTokenManager;
    private final String componentId;

    /**
     *
     * @param componentTokenManager
     *            应用套件的token
     * @param componentId
     *            应用组件ID
     */
    public WeixinComponentPreCodeCreator(TokenManager componentTokenManager, String componentId) {
        this.componentTokenManager = componentTokenManager;
        this.componentId = componentId;
    }

    @Override
    public String name() {
        return "mp_component_precode";
    }

    @Override
    public String uniqueid() {
        return componentId;
    }

    @Override
    public Token create() throws WeixinException {
        WeixinResponse response = weixinExecutor.post(
                String.format(URLConsts.COMPONENET_PRE_CODE_URL, componentTokenManager.getAccessToken()),
                String.format("{\"component_appid\":\"%s\"}", componentId));
        JSONObject result = response.getAsJson();
        return new Token(result.getString("pre_auth_code"), result.getLongValue("expires_in") * 1000l);
    }

}
