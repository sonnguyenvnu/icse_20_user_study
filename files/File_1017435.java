package com.foxinmy.weixin4j.mp.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.tuple.MassTuple;
import com.foxinmy.weixin4j.tuple.MpArticle;
import com.foxinmy.weixin4j.tuple.MpNews;
import com.foxinmy.weixin4j.tuple.Tuple;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 * ç¾¤å?‘ç›¸å…³API
 *
 * @className MassApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014å¹´9æœˆ25æ—¥
 * @since JDK 1.6
 */
public class MassApi extends MpApi {

    private final TokenManager tokenManager;

    public MassApi(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    /**
     * ä¸Šä¼ å›¾æ–‡æ¶ˆæ?¯,ä¸€ä¸ªå›¾æ–‡æ¶ˆæ?¯æ”¯æŒ?1åˆ°10æ?¡å›¾æ–‡</br>
     * <font color=
     * "red">å…·å¤‡å¾®ä¿¡æ”¯ä»˜æ?ƒé™?çš„å…¬ä¼—å?·ï¼Œåœ¨ä½¿ç”¨é«˜çº§ç¾¤å?‘æŽ¥å?£ä¸Šä¼ ã€?ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯ç±»åž‹æ—¶ï¼Œå?¯ä½¿ç”¨&lta&gtæ ‡ç­¾åŠ å…¥å¤–é“¾</font>
     *
     * @param articles
     *            å›¾ç‰‡æ¶ˆæ?¯
     * @return åª’ä½“ID
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">ä¸Šä¼ å›¾æ–‡ç´ æ??</a>
     * @see com.foxinmy.weixin4j.tuple.MpArticle
     */
    public String uploadArticle(List<MpArticle> articles) throws WeixinException {
        String article_upload_uri = getRequestUri("article_upload_uri");
        Token token = tokenManager.getCache();
        JSONObject obj = new JSONObject();
        obj.put("articles", articles);
        WeixinResponse response = weixinExecutor.post(String.format(article_upload_uri, token.getAccessToken()),
                obj.toJSONString());

        return response.getAsJson().getString("media_id");
    }

    /**
     * åˆ†ç»„ç¾¤å?‘
     * <p>
     * åœ¨è¿”å›žæˆ?åŠŸæ—¶,æ„?å‘³ç?€ç¾¤å?‘ä»»åŠ¡æ??äº¤æˆ?åŠŸ,å¹¶ä¸?æ„?å‘³ç?€æ­¤æ—¶ç¾¤å?‘å·²ç»?ç»“æ?Ÿ,æ‰€ä»¥,ä»?æœ‰å?¯èƒ½åœ¨å?Žç»­çš„å?‘é€?è¿‡ç¨‹ä¸­å‡ºçŽ°å¼‚å¸¸æƒ…å†µå¯¼è‡´ç”¨æˆ·æœªæ”¶åˆ°æ¶ˆæ?¯,
     * å¦‚æ¶ˆæ?¯æœ‰æ—¶ä¼šè¿›è¡Œå®¡æ ¸ã€?æœ?åŠ¡å™¨ä¸?ç¨³å®šç­‰,æ­¤å¤–,ç¾¤å?‘ä»»åŠ¡ä¸€èˆ¬éœ€è¦?è¾ƒé•¿çš„æ—¶é—´æ‰?èƒ½å…¨éƒ¨å?‘é€?å®Œæ¯•
     * </p>
     *
     * @param tuple
     *            æ¶ˆæ?¯å…ƒä»¶
     * @param isToAll
     *            ç”¨äºŽè®¾å®šæ˜¯å?¦å?‘å…¨éƒ¨ç”¨æˆ·å?‘é€?ï¼Œå€¼ä¸ºtrueæˆ–falseï¼Œé€‰æ‹©trueè¯¥æ¶ˆæ?¯ç¾¤å?‘ç»™æ‰€æœ‰ç”¨æˆ·ï¼Œ
     *            é€‰æ‹©falseå?¯æ ¹æ?®group_idå?‘é€?ç»™æŒ‡å®šç¾¤ç»„çš„ç”¨æˆ·
     * @param groupId
     *            åˆ†ç»„ID
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°,å?¯ä»¥ç”¨äºŽåœ¨å›¾æ–‡åˆ†æž?æ•°æ?®æŽ¥å?£ä¸­
     * @throws WeixinException
     * @see com.foxinmy.weixin4j.mp.model.Group
     * @see com.foxinmy.weixin4j.tuple.Text
     * @see com.foxinmy.weixin4j.tuple.Image
     * @see com.foxinmy.weixin4j.tuple.Voice
     * @see com.foxinmy.weixin4j.tuple.MpVideo
     * @see com.foxinmy.weixin4j.tuple.MpNews
     * @see com.foxinmy.weixin4j.tuple.Card
     * @see com.foxinmy.weixin4j.tuple.MassTuple
     * @see {@link GroupApi#getGroups()}
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®åˆ†ç»„ç¾¤å?‘</a>
     */
    @Deprecated
    public String[] massByGroupId(MassTuple tuple, boolean isToAll, int groupId) throws WeixinException {
        if (tuple instanceof MpNews) {
            MpNews _news = (MpNews) tuple;
            List<MpArticle> _articles = _news.getArticles();
            if (StringUtil.isBlank(_news.getMediaId())) {
                if (_articles.isEmpty()) {
                    throw new WeixinException("mass fail:mediaId or articles is required");
                }
                tuple = new MpNews(uploadArticle(_articles));
            }
        }
        String msgtype = tuple.getMessageType();
        JSONObject obj = new JSONObject();
        JSONObject item = new JSONObject();
        item.put("is_to_all", isToAll);
        if (!isToAll) {
            item.put("group_id", groupId);
        }
        obj.put("filter", item);
        obj.put(msgtype, JSON.toJSON(tuple));
        obj.put("msgtype", msgtype);
        String mass_group_uri = getRequestUri("mass_group_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.post(String.format(mass_group_uri, token.getAccessToken()),
                obj.toJSONString());

        obj = response.getAsJson();
        return new String[] { obj.getString("msg_id"), obj.getString("msg_data_id") };
    }

    /**
     * åˆ†ç»„IDç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯
     *
     * @param articles
     *            å›¾æ–‡åˆ—è¡¨
     * @param groupId
     *            åˆ†ç»„ID
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°ã€‚
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®åˆ†ç»„ç¾¤å?‘</a>
     * @see {@link #massByGroupId(Tuple,int)}
     * @see com.foxinmy.weixin4j.tuple.MpArticle
     * @throws WeixinException
     */
    @Deprecated
    public String[] massArticleByGroupId(List<MpArticle> articles, int groupId) throws WeixinException {
        String mediaId = uploadArticle(articles);
        return massByGroupId(new MpNews(mediaId), false, groupId);
    }

    /**
     * ç¾¤å?‘æ¶ˆæ?¯
     * <p>
     * åœ¨è¿”å›žæˆ?åŠŸæ—¶,æ„?å‘³ç?€ç¾¤å?‘ä»»åŠ¡æ??äº¤æˆ?åŠŸ,å¹¶ä¸?æ„?å‘³ç?€æ­¤æ—¶ç¾¤å?‘å·²ç»?ç»“æ?Ÿ,æ‰€ä»¥,ä»?æœ‰å?¯èƒ½åœ¨å?Žç»­çš„å?‘é€?è¿‡ç¨‹ä¸­å‡ºçŽ°å¼‚å¸¸æƒ…å†µå¯¼è‡´ç”¨æˆ·æœªæ”¶åˆ°æ¶ˆæ?¯,
     * å¦‚æ¶ˆæ?¯æœ‰æ—¶ä¼šè¿›è¡Œå®¡æ ¸ã€?æœ?åŠ¡å™¨ä¸?ç¨³å®šç­‰,æ­¤å¤–,ç¾¤å?‘ä»»åŠ¡ä¸€èˆ¬éœ€è¦?è¾ƒé•¿çš„æ—¶é—´æ‰?èƒ½å…¨éƒ¨å?‘é€?å®Œæ¯•
     * </p>
     *
     * @param tuple
     *            æ¶ˆæ?¯å…ƒä»¶
     * @param filter
     *            è¿‡æ»¤æ?¡ä»¶
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°,å?¯ä»¥ç”¨äºŽåœ¨å›¾æ–‡åˆ†æž?æ•°æ?®æŽ¥å?£ä¸­
     * @throws WeixinException
     * @see Tag
     * @see com.foxinmy.weixin4j.tuple.Text
     * @see com.foxinmy.weixin4j.tuple.Image
     * @see com.foxinmy.weixin4j.tuple.Voice
     * @see com.foxinmy.weixin4j.tuple.MpVideo
     * @see com.foxinmy.weixin4j.tuple.MpNews
     * @see com.foxinmy.weixin4j.tuple.Card
     * @see com.foxinmy.weixin4j.tuple.MassTuple
     */
    private String[] mass(MassTuple tuple, JSONObject filter) throws WeixinException {
        if (tuple instanceof MpNews) {
            MpNews _news = (MpNews) tuple;
            List<MpArticle> _articles = _news.getArticles();
            if (StringUtil.isBlank(_news.getMediaId())) {
                if (_articles.isEmpty()) {
                    throw new WeixinException("mass fail:mediaId or articles is required");
                }
                tuple = new MpNews(uploadArticle(_articles));
            }
            if (!filter.containsKey("send_ignore_reprint")) {
                filter.put("send_ignore_reprint", 0);
            }
        }
        String msgtype = tuple.getMessageType();
        JSONObject obj = new JSONObject();
        obj.putAll(filter);
        obj.put(msgtype, JSON.toJSON(tuple));
        obj.put("msgtype", msgtype);
        String mass_group_uri = getRequestUri("mass_group_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.post(String.format(mass_group_uri, token.getAccessToken()),
                obj.toJSONString());

        obj = response.getAsJson();
        return new String[] { obj.getString("msg_id"), obj.getString("msg_data_id") };
    }

    /**
     * ç¾¤å?‘æ¶ˆæ?¯ç»™æ‰€æœ‰ç²‰ä¸?
     *
     * @param tuple
     *            æ¶ˆæ?¯å…ƒä»¶
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°,å?¯ä»¥ç”¨äºŽåœ¨å›¾æ–‡åˆ†æž?æ•°æ?®æŽ¥å?£ä¸­
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®æ ‡ç­¾ç¾¤å?‘</a>
     */
    public String[] massToAll(MassTuple tuple) throws WeixinException {
        String filter = String.format("{\"filter\":{\"is_to_all\":true}}");
        return mass(tuple, JSON.parseObject(filter));
    }

    /**
     * æ ‡ç­¾ç¾¤å?‘æ¶ˆæ?¯
     *
     * @param tuple
     *            æ¶ˆæ?¯å…ƒä»¶
     * @param tagId
     *            æ ‡ç­¾ID
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°,å?¯ä»¥ç”¨äºŽåœ¨å›¾æ–‡åˆ†æž?æ•°æ?®æŽ¥å?£ä¸­
     * @throws WeixinException
     * @see Tag
     * @see {@link TagApi#listTags()}
     * @see #mass(MassTuple, JSONObject, boolean)
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®æ ‡ç­¾ç¾¤å?‘</a>
     */
    public String[] massByTagId(MassTuple tuple, int tagId) throws WeixinException {
        String filter = String.format("{\"filter\":{\"is_to_all\":false,\"tag_id\":%d}}", tagId);
        return mass(tuple, JSON.parseObject(filter));
    }

    /**
     * æ ‡ç­¾ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯
     *
     * @param articles
     *            å›¾æ–‡åˆ—è¡¨
     * @param tagId
     *            æ ‡ç­¾ID
     * @param ignoreReprint
     *            å›¾æ–‡æ¶ˆæ?¯è¢«åˆ¤å®šä¸ºè½¬è½½æ—¶ï¼Œæ˜¯å?¦ç»§ç»­ç¾¤å?‘
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°ã€‚
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®æ ‡ç­¾ç¾¤å?‘</a>
     * @see {@link #massByTagId(Tuple,int)}
     * @see com.foxinmy.weixin4j.tuple.MpArticle
     * @throws WeixinException
     */
    public String[] massArticleByTagId(List<MpArticle> articles, int tagId, boolean ignoreReprint)
            throws WeixinException {
        String mediaId = uploadArticle(articles);
        String text = String.format("{\"filter\":{\"is_to_all\":false,\"tag_id\":%d}}", tagId);
        JSONObject filter = JSON.parseObject(text);
        filter.put("send_ignore_reprint", ignoreReprint ? 1 : 0);
        return mass(new MpNews(mediaId), filter);
    }

    /**
     * openIdç¾¤å?‘æ¶ˆæ?¯
     *
     * @param tuple
     *            æ¶ˆæ?¯å…ƒä»¶
     * @param openIds
     *            openIdåˆ—è¡¨
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°,å?¯ä»¥ç”¨äºŽåœ¨å›¾æ–‡åˆ†æž?æ•°æ?®æŽ¥å?£ä¸­
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®openidç¾¤å?‘</a>
     * @see {@link UserApi#getUser(String)}
     * @see #mass(MassTuple, JSONObject, boolean)
     */
    public String[] massByOpenIds(MassTuple tuple, String... openIds) throws WeixinException {
        JSONObject filter = new JSONObject();
        filter.put("touser", openIds);
        return mass(tuple, filter);
    }

    /**
     * openidç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯
     *
     * @param articles
     *            å›¾æ–‡åˆ—è¡¨
     * @param ignoreReprint
     *            å›¾æ–‡æ¶ˆæ?¯è¢«åˆ¤å®šä¸ºè½¬è½½æ—¶ï¼Œæ˜¯å?¦ç»§ç»­ç¾¤å?‘
     * @param openIds
     *            openIdåˆ—è¡¨
     * @return ç¬¬ä¸€ä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯å?‘é€?ä»»åŠ¡çš„ID,ç¬¬äºŒä¸ªå…ƒç´ ä¸ºæ¶ˆæ?¯çš„æ•°æ?®IDï¼Œè¯¥å­—æ®µå?ªæœ‰åœ¨ç¾¤å?‘å›¾æ–‡æ¶ˆæ?¯æ—¶ï¼Œæ‰?ä¼šå‡ºçŽ°,å?¯ä»¥ç”¨äºŽåœ¨å›¾æ–‡åˆ†æž?æ•°æ?®æŽ¥å?£ä¸­.
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æ ¹æ?®openidç¾¤å?‘</a>
     * @see {@link #massByOpenIds(Tuple,String...)}
     * @see com.foxinmy.weixin4j.tuple.MpArticle
     * @throws WeixinException
     */
    public String[] massArticleByOpenIds(List<MpArticle> articles, boolean ignoreReprint, String... openIds)
            throws WeixinException {
        String mediaId = uploadArticle(articles);
        JSONObject filter = new JSONObject();
        filter.put("touser", openIds);
        filter.put("send_ignore_reprint", ignoreReprint ? 1 : 0);
        return mass(new MpNews(mediaId), filter);
    }

    /**
     * åˆ é™¤ç¾¤å?‘æ¶ˆæ?¯
     *
     * @param msgid
     *            å?‘é€?å‡ºåŽ»çš„æ¶ˆæ?¯ID
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">åˆ é™¤ç¾¤å?‘</a>
     * @see #deleteMassNews(String, int)
     */
    public ApiResult deleteMassNews(String msgid) throws WeixinException {
        return deleteMassNews(msgid, 0);
    }

    /**
     * åˆ é™¤ç¾¤å?‘æ¶ˆæ?¯
     * <p>
     * è¯·æ³¨æ„?,å?ªæœ‰å·²ç»?å?‘é€?æˆ?åŠŸçš„æ¶ˆæ?¯æ‰?èƒ½åˆ é™¤åˆ é™¤æ¶ˆæ?¯å?ªæ˜¯å°†æ¶ˆæ?¯çš„å›¾æ–‡è¯¦æƒ…é¡µå¤±æ•ˆ,å·²ç»?æ”¶åˆ°çš„ç”¨æˆ·,è¿˜æ˜¯èƒ½åœ¨å…¶æœ¬åœ°çœ‹åˆ°æ¶ˆæ?¯å?¡ç‰‡
     * </p>
     *
     * @param msgid
     *            å?‘é€?å‡ºåŽ»çš„æ¶ˆæ?¯ID
     * @param articleIndex
     *            è¦?åˆ é™¤çš„æ–‡ç« åœ¨å›¾æ–‡æ¶ˆæ?¯ä¸­çš„ä½?ç½®ï¼Œç¬¬ä¸€ç¯‡ç¼–å?·ä¸º1ï¼Œè¯¥å­—æ®µä¸?å¡«æˆ–å¡«0ä¼šåˆ é™¤å…¨éƒ¨æ–‡ç« 
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">åˆ é™¤ç¾¤å?‘</a>
     * @see {@link #massByTagId(Tuple, int)}
     * @see {@link #massByOpenIds(Tuple, String...)
     *
     */
    public ApiResult deleteMassNews(String msgid, int articleIndex) throws WeixinException {
        JSONObject obj = new JSONObject();
        obj.put("msgid", msgid);
        if (articleIndex > 0)
            obj.put("article_idx", articleIndex);
        String mass_delete_uri = getRequestUri("mass_delete_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.post(String.format(mass_delete_uri, token.getAccessToken()),
                obj.toJSONString());

        return response.getAsResult();
    }

    /**
     * é¢„è§ˆç¾¤å?‘æ¶ˆæ?¯</br>
     * å¼€å?‘è€…å?¯é€šè¿‡è¯¥æŽ¥å?£å?‘é€?æ¶ˆæ?¯ç»™æŒ‡å®šç”¨æˆ·ï¼Œåœ¨æ‰‹æœºç«¯æŸ¥çœ‹æ¶ˆæ?¯çš„æ ·å¼?å’ŒæŽ’ç‰ˆ
     *
     * @param toUser
     *            æŽ¥æ”¶ç”¨æˆ·çš„openID
     * @param toWxName
     *            æŽ¥æ”¶ç”¨æˆ·çš„å¾®ä¿¡å?· towxnameå’Œtouserå?Œæ—¶èµ‹å€¼æ—¶ï¼Œä»¥towxnameä¼˜å…ˆ
     * @param tuple
     *            æ¶ˆæ?¯å…ƒä»¶
     * @return å¤„ç?†ç»“æžœ
     * @throws WeixinException
     * @see com.foxinmy.weixin4j.tuple.MassTuple
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">é¢„è§ˆç¾¤å?‘æ¶ˆæ?¯</a>
     */
    public ApiResult previewMassNews(String toUser, String toWxName, MassTuple tuple) throws WeixinException {
        String msgtype = tuple.getMessageType();
        JSONObject obj = new JSONObject();
        obj.put("touser", toUser);
        obj.put("towxname", toWxName);
        obj.put(msgtype, JSON.toJSON(tuple));
        obj.put("msgtype", msgtype);
        String mass_preview_uri = getRequestUri("mass_preview_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.post(String.format(mass_preview_uri, token.getAccessToken()),
                obj.toJSONString());

        return response.getAsResult();
    }

    /**
     * æŸ¥è¯¢ç¾¤å?‘å?‘é€?çŠ¶æ€?
     *
     * @param msgId
     *            æ¶ˆæ?¯ID
     * @return æ¶ˆæ?¯å?‘é€?çŠ¶æ€?,å¦‚sendsuccess:å?‘é€?æˆ?åŠŸã€?sendfail:å?‘é€?å¤±è´¥
     * @throws WeixinException
     * @see <a href=
     *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">æŸ¥è¯¢ç¾¤å?‘çŠ¶æ€?</a>
     */
    public String getMassNewStatus(String msgId) throws WeixinException {
        JSONObject obj = new JSONObject();
        obj.put("msg_id", msgId);
        String mass_get_uri = getRequestUri("mass_get_uri");
        Token token = tokenManager.getCache();
        WeixinResponse response = weixinExecutor.post(String.format(mass_get_uri, token.getAccessToken()),
                obj.toJSONString());

        String status = response.getAsJson().getString("msg_status");
        String desc = massStatusMap.get(status);
        return String.format("%s:%s", status, desc);
    }

    private final static Map<String, String> massStatusMap;
    static {
        massStatusMap = new HashMap<String, String>();
        massStatusMap.put("sendsuccess", "å?‘é€?æˆ?åŠŸ");
        massStatusMap.put("send_success", "å?‘é€?æˆ?åŠŸ");
        massStatusMap.put("success", "å?‘é€?æˆ?åŠŸ");
        massStatusMap.put("send success", "å?‘é€?æˆ?åŠŸ");
        massStatusMap.put("sendfail", "å?‘é€?å¤±è´¥");
        massStatusMap.put("send_fail", "å?‘é€?å¤±è´¥");
        massStatusMap.put("fail", "å?‘é€?å¤±è´¥");
        massStatusMap.put("send fail", "å?‘é€?å¤±è´¥");
        massStatusMap.put("err(10001)", "æ¶‰å«Œå¹¿å‘Š");
        massStatusMap.put("err(20001)", "æ¶‰å«Œæ”¿æ²»");
        massStatusMap.put("err(20004)", "æ¶‰å«Œç¤¾ä¼š");
        massStatusMap.put("err(20006)", "æ¶‰å«Œè¿?æ³•çŠ¯ç½ª");
        massStatusMap.put("err(20008)", "æ¶‰å«Œæ¬ºè¯ˆ");
        massStatusMap.put("err(20013)", "æ¶‰å«Œç‰ˆæ?ƒ");
        massStatusMap.put("err(22000)", "æ¶‰å«Œäº’æŽ¨(äº’ç›¸å®£ä¼ )");
        massStatusMap.put("err(21000)", "æ¶‰å«Œå…¶ä»–");
    }
}
