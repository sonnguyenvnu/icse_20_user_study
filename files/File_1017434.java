package com.foxinmy.weixin4j.mp.api;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.mp.model.AutoReplySetting;
import com.foxinmy.weixin4j.mp.model.MenuSetting;
import com.foxinmy.weixin4j.mp.model.SemQuery;
import com.foxinmy.weixin4j.mp.model.SemResult;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.tuple.MpArticle;

/**
 * è¾…åŠ©ç›¸å…³API
 *
 * @className HelperApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014å¹´9æœˆ26æ—¥
 * @since JDK 1.6
 * @see
 */
public class HelperApi extends MpApi {

    private final TokenManager tokenManager;

    public HelperApi(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    /**
     * é•¿é“¾æŽ¥è½¬çŸ­é“¾æŽ¥
     *
     * @param url
     *            å¾…è½¬æ?¢çš„é“¾æŽ¥
     * @return çŸ­é“¾æŽ¥
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1443433600&token=&lang=zh_CN">é•¿é“¾æŽ¥è½¬çŸ­é“¾æŽ¥</a>
     */
    public String getShorturl(String url) throws WeixinException {
        String shorturl_uri = getRequestUri("shorturl_uri");
        Token token = tokenManager.getCache();
        JSONObject obj = new JSONObject();
        obj.put("action", "long2short");
        obj.put("long_url", url);
        WeixinResponse response = weixinExecutor.post(String.format(shorturl_uri, token.getAccessToken()),
                obj.toJSONString());

        return response.getAsJson().getString("short_url");
    }

    /**
     * è¯­ä¹‰ç?†è§£
     *
     * @param semQuery
     *            è¯­ä¹‰ç?†è§£å??è®®
     * @return è¯­ä¹‰ç?†è§£ç»“æžœ
     * @see com.foxinmy.weixin4j.mp.model.SemQuery
     * @see com.foxinmy.weixin4j.mp.model.SemResult
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141241&token=&lang=zh_CN">è¯­ä¹‰ç?†è§£</a>
     * @throws WeixinException
     */
    public SemResult semantic(SemQuery semQuery) throws WeixinException {
        String semantic_uri = getRequestUri("semantic_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.post(String.format(semantic_uri, token.getAccessToken()),
                semQuery.toJson());
        return response.getAsObject(new TypeReference<SemResult>() {
        });
    }

    /**
     * èŽ·å?–å¾®ä¿¡æœ?åŠ¡å™¨IPåœ°å?€
     *
     * @return IPåœ°å?€
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140187&token=&lang=zh_CN">èŽ·å?–IPåœ°å?€</a>
     * @throws WeixinException
     */
    public List<String> getWechatServerIp() throws WeixinException {
        String getcallbackip_uri = getRequestUri("getcallbackip_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.get(String.format(getcallbackip_uri, token.getAccessToken()));
        return JSON.parseArray(response.getAsJson().getString("ip_list"), String.class);
    }

    /**
     * èŽ·å?–å…¬ä¼—å?·å½“å‰?ä½¿ç”¨çš„è‡ªå®šä¹‰è?œå?•çš„é…?ç½®ï¼Œå¦‚æžœå…¬ä¼—å?·æ˜¯é€šè¿‡APIè°ƒç”¨è®¾ç½®çš„è?œå?•ï¼Œåˆ™è¿”å›žè?œå?•çš„å¼€å?‘é…?ç½®ï¼Œ
     * è€Œå¦‚æžœå…¬ä¼—å?·æ˜¯åœ¨å…¬ä¼—å¹³å?°å®˜ç½‘é€šè¿‡ç½‘ç«™åŠŸèƒ½å?‘å¸ƒè?œå?•ï¼Œåˆ™æœ¬æŽ¥å?£è¿”å›žè¿?è?¥è€…è®¾ç½®çš„è?œå?•é…?ç½®ã€‚
     *
     * @return è?œå?•é…?ç½®ä¿¡æ?¯
     * @see {@link MenuApi#getMenu()}
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1434698695&token=&lang=zh_CN">èŽ·å?–è‡ªå®šä¹‰è?œå?•é…?ç½®</a>
     * @see com.foxinmy.weixin4j.model.Button
     * @see com.foxinmy.weixin4j.mp.model.MenuSetting
     * @see com.foxinmy.weixin4j.tuple.MpArticle
     * @throws WeixinException
     */
    public MenuSetting getMenuSetting() throws WeixinException {
        String menu_get_selfmenu_uri = getRequestUri("menu_get_selfmenu_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.get(String.format(menu_get_selfmenu_uri, token.getAccessToken()));
        JSONObject result = response.getAsJson();
        JSONArray buttons = result.getJSONObject("selfmenu_info").getJSONArray("button");
        List<Button> buttonList = new ArrayList<Button>(buttons.size());
        JSONObject buttonObj = null;
        for (int i = 0; i < buttons.size(); i++) {
            buttonObj = buttons.getJSONObject(i);
            if (buttonObj.containsKey("sub_button")) {
                buttonObj.put("sub_button", buttonObj.getJSONObject("sub_button").getJSONArray("list"));
                buttonObj.put("type", "popups");
            }
            buttonList.add(JSON.parseObject(buttonObj.toJSONString(), Button.class, ButtonExtraProcessor.global));
        }
        return new MenuSetting(result.getBooleanValue("is_menu_open"), buttonList);
    }

    private static final class ButtonExtraProcessor implements ExtraProcessor {
        private static ButtonExtraProcessor global = new ButtonExtraProcessor();
        private static final String KEY = "news_info";

        private ButtonExtraProcessor() {
        }

        @Override
        public void processExtra(Object object, String key, Object value) {
            if (KEY.equalsIgnoreCase(key)) {
                JSONArray news = ((JSONObject) value).getJSONArray("list");
                List<MpArticle> newsList = new ArrayList<MpArticle>(news.size());
                JSONObject article = null;
                for (int i = 0; i < news.size(); i++) {
                    article = news.getJSONObject(i);
                    article.put("show_cover_pic", article.remove("show_cover"));
                    article.put("thumb_url", article.remove("cover_url"));
                    article.put("url", article.remove("content_url"));
                    article.put("content_source_url", article.remove("source_url"));
                    newsList.add(JSON.toJavaObject(article, MpArticle.class));
                }
                ((Button) object).setExtra(newsList);
            } else {
                ((Button) object).setContent(String.valueOf(value));
            }
        }
    };

    /**
     * èŽ·å?–å…¬ä¼—å?·å½“å‰?ä½¿ç”¨çš„è‡ªåŠ¨å›žå¤?è§„åˆ™ï¼ŒåŒ…æ‹¬å…³æ³¨å?Žè‡ªåŠ¨å›žå¤?ã€?æ¶ˆæ?¯è‡ªåŠ¨å›žå¤?ï¼ˆ60åˆ†é’Ÿå†…è§¦å?‘ä¸€æ¬¡ï¼‰ã€?å…³é”®è¯?è‡ªåŠ¨å›žå¤?ã€‚
     *
     * @see com.foxinmy.weixin4j.mp.model.AutoReplySetting
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751299&token=&lang=zh_CN">èŽ·å?–è‡ªåŠ¨å›žå¤?è§„åˆ™</a>
     * @return è‡ªå®šä¹‰å›žå¤?é…?ç½®ä¿¡æ?¯
     * @throws WeixinException
     */
    public AutoReplySetting getAutoReplySetting() throws WeixinException {
        String autoreply_setting_get_uri = getRequestUri("autoreply_setting_get_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.get(String.format(autoreply_setting_get_uri, token.getAccessToken()));

        JSONObject result = response.getAsJson();

        AutoReplySetting replySetting = JSON.toJavaObject(result, AutoReplySetting.class);
        List<AutoReplySetting.Rule> ruleList = null;
        if (result.containsKey("keyword_autoreply_info")) {
            JSONArray keywordList = result.getJSONObject("keyword_autoreply_info").getJSONArray("list");
            ruleList = new ArrayList<AutoReplySetting.Rule>(keywordList.size());
            JSONObject keywordObj = null;
            JSONArray replyList = null;
            JSONObject replyObj = null;
            for (int i = 0; i < keywordList.size(); i++) {
                keywordObj = keywordList.getJSONObject(i);
                AutoReplySetting.Rule rule = JSON.toJavaObject(keywordObj, AutoReplySetting.Rule.class);
                replyList = keywordObj.getJSONArray("reply_list_info");
                List<AutoReplySetting.Entry> entryList = new ArrayList<AutoReplySetting.Entry>(replyList.size());
                for (int j = 0; j < replyList.size(); j++) {
                    replyObj = replyList.getJSONObject(j);
                    if (replyObj.getString("type").equals("news")) {
                        entryList.add(JSON.parseObject(replyObj.toJSONString(), AutoReplySetting.Entry.class,
                                ButtonExtraProcessor.global));
                    } else {
                        entryList.add(JSON.toJavaObject(replyObj, AutoReplySetting.Entry.class));
                    }
                }
                rule.setReplyList(entryList);
                ruleList.add(rule);
            }
        }
        replySetting.setKeywordReplyList(ruleList);
        return replySetting;
    }

    /**
     * æŽ¥å?£è°ƒç”¨æ¬¡æ•°è°ƒç”¨æ¸…é›¶ï¼šå…¬ä¼—å?·è°ƒç”¨æŽ¥å?£å¹¶ä¸?æ˜¯æ— é™?åˆ¶çš„ã€‚ä¸ºäº†é˜²æ­¢å…¬ä¼—å?·çš„ç¨‹åº?é”™è¯¯è€Œå¼•å?‘å¾®ä¿¡æœ?åŠ¡å™¨è´Ÿè½½å¼‚å¸¸ï¼Œé»˜è®¤æƒ…å†µä¸‹ï¼Œ
     * æ¯?ä¸ªå…¬ä¼—å?·è°ƒç”¨æŽ¥å?£éƒ½ä¸?èƒ½è¶…è¿‡ä¸€å®šé™?åˆ¶ ï¼Œå½“è¶…è¿‡ä¸€å®šé™?åˆ¶æ—¶ï¼Œè°ƒç”¨å¯¹åº”æŽ¥å?£ä¼šæ”¶åˆ°{"errcode":45009,"errmsg":"api freq
     * out of limit" }é”™è¯¯è¿”å›žç ?ã€‚
     *
     * @param appId
     *            å…¬ä¼—å?·ID
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433744592&token=&lang=zh_CN">æŽ¥å?£æ¸…é›¶</a>
     * @return æ“?ä½œç»“æžœ
     * @throws WeixinException
     */
    public ApiResult clearQuota(String appId) throws WeixinException {
        String clearquota_uri = getRequestUri("clearquota_uri");
        String body = String.format("{\"appid\":\"%s\"}", appId);
        WeixinResponse response = weixinExecutor.post(String.format(clearquota_uri, tokenManager.getAccessToken()),
                body);
        return response.getAsResult();
    }
}
