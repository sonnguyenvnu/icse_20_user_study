package com.foxinmy.weixin4j.mp;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.foxinmy.weixin4j.cache.CacheStorager;
import com.foxinmy.weixin4j.cache.FileCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.model.card.CardCoupon;
import com.foxinmy.weixin4j.model.card.CardCoupons;
import com.foxinmy.weixin4j.model.card.CardQR;
import com.foxinmy.weixin4j.model.media.MediaCounter;
import com.foxinmy.weixin4j.model.media.MediaDownloadResult;
import com.foxinmy.weixin4j.model.media.MediaItem;
import com.foxinmy.weixin4j.model.media.MediaRecord;
import com.foxinmy.weixin4j.model.media.MediaUploadResult;
import com.foxinmy.weixin4j.model.paging.Pageable;
import com.foxinmy.weixin4j.model.paging.Pagedata;
import com.foxinmy.weixin4j.model.qr.QRParameter;
import com.foxinmy.weixin4j.model.qr.QRResult;
import com.foxinmy.weixin4j.mp.api.CardApi;
import com.foxinmy.weixin4j.mp.api.CommentApi;
import com.foxinmy.weixin4j.mp.api.CustomApi;
import com.foxinmy.weixin4j.mp.api.DataApi;
import com.foxinmy.weixin4j.mp.api.GroupApi;
import com.foxinmy.weixin4j.mp.api.HelperApi;
import com.foxinmy.weixin4j.mp.api.MassApi;
import com.foxinmy.weixin4j.mp.api.MediaApi;
import com.foxinmy.weixin4j.mp.api.MenuApi;
import com.foxinmy.weixin4j.mp.api.NotifyApi;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.api.QrApi;
import com.foxinmy.weixin4j.mp.api.TagApi;
import com.foxinmy.weixin4j.mp.api.TmplApi;
import com.foxinmy.weixin4j.mp.api.UserApi;
import com.foxinmy.weixin4j.mp.component.WeixinTokenComponentCreator;
import com.foxinmy.weixin4j.mp.message.NotifyMessage;
import com.foxinmy.weixin4j.mp.message.TemplateMessage;
import com.foxinmy.weixin4j.mp.model.ArticleComment;
import com.foxinmy.weixin4j.mp.model.ArticleComment.ArticleCommentType;
import com.foxinmy.weixin4j.mp.model.AutoReplySetting;
import com.foxinmy.weixin4j.mp.model.Following;
import com.foxinmy.weixin4j.mp.model.Group;
import com.foxinmy.weixin4j.mp.model.KfAccount;
import com.foxinmy.weixin4j.mp.model.KfChatRecord;
import com.foxinmy.weixin4j.mp.model.KfOnlineAccount;
import com.foxinmy.weixin4j.mp.model.KfSession;
import com.foxinmy.weixin4j.mp.model.KfSession.KfSessionCounter;
import com.foxinmy.weixin4j.mp.model.Menu;
import com.foxinmy.weixin4j.mp.model.MenuMatchRule;
import com.foxinmy.weixin4j.mp.model.MenuSetting;
import com.foxinmy.weixin4j.mp.model.SemQuery;
import com.foxinmy.weixin4j.mp.model.SemResult;
import com.foxinmy.weixin4j.mp.model.Tag;
import com.foxinmy.weixin4j.mp.model.TemplateMessageInfo;
import com.foxinmy.weixin4j.mp.model.User;
import com.foxinmy.weixin4j.mp.token.WeixinTicketCreator;
import com.foxinmy.weixin4j.mp.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.mp.type.DatacubeType;
import com.foxinmy.weixin4j.mp.type.IndustryType;
import com.foxinmy.weixin4j.mp.type.Lang;
import com.foxinmy.weixin4j.token.PerTicketManager;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.tuple.MassTuple;
import com.foxinmy.weixin4j.tuple.MpArticle;
import com.foxinmy.weixin4j.tuple.MpVideo;
import com.foxinmy.weixin4j.tuple.Tuple;
import com.foxinmy.weixin4j.type.MediaType;
import com.foxinmy.weixin4j.type.TicketType;
import com.foxinmy.weixin4j.util.Consts;
import com.foxinmy.weixin4j.util.Weixin4jConfigUtil;

/**
 * 微信公众平�?�接�?�实现
 *
 * @className WeixinProxy
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014年3月23日
 * @since JDK 1.6
 * @see <a href="http://mp.weixin.qq.com/wiki/index.php">api文档</a>
 */
public class WeixinProxy {
	/**
	 * 授�?�API
	 */
	private final OauthApi oauthApi;
	/**
	 * 媒体素�??API
	 */
	private final MediaApi mediaApi;
	/**
	 * 客�?消�?�API
	 */
	private final NotifyApi notifyApi;
	/**
	 * 多客�?API
	 */
	private final CustomApi customApi;
	/**
	 * 群�?�消�?�API
	 */
	private final MassApi massApi;
	/**
	 * 用户API
	 */
	private final UserApi userApi;
	/**
	 * 分组API
	 */
	private final GroupApi groupApi;
	/**
	 * �?��?�API
	 */
	private final MenuApi menuApi;
	/**
	 * 二维�?API
	 */
	private final QrApi qrApi;
	/**
	 * 模�?�消�?�API
	 */
	private final TmplApi tmplApi;
	/**
	 * 辅助API
	 */
	private final HelperApi helperApi;
	/**
	 * 数�?�统计API
	 */
	private final DataApi dataApi;
	/**
	 * 标签API
	 */
	private final TagApi tagApi;
	/**
	 * �?�券API
	 */
	private final CardApi cardApi;
	/**
	 * 文章评论API
	 */
	private final CommentApi commentApi;
	/**
	 * token管�?�
	 */
	private final TokenManager tokenManager;
	/**
	 * 账�?�信�?�
	 */
	private final WeixinAccount weixinAccount;
	/**
	 * token存储
	 */
	private final CacheStorager<Token> cacheStorager;

	/**
	 * 微信接�?�实现(使用weixin4j.properties�?置的account账�?�信�?�,
	 * 使用FileCacheStorager文件方�?缓存TOKEN)
	 */
	public WeixinProxy() {
		this(new FileCacheStorager<Token>());
	}

	/**
	 * 微信接�?�实现(使用weixin4j.properties�?置的account账�?�信�?�)
	 *
	 * @param cacheStorager
	 *            token管�?�
	 */
	public WeixinProxy(CacheStorager<Token> cacheStorager) {
		this(Weixin4jConfigUtil.getWeixinAccount(), cacheStorager);
	}

	/**
	 * 微信接�?�实现
	 *
	 * @param weixinAccount
	 *            账�?�信�?�
	 * @param cacheStorager
	 *            token管�?�
	 */
	public WeixinProxy(WeixinAccount weixinAccount, CacheStorager<Token> cacheStorager) {
		this(weixinAccount, new WeixinTokenCreator(weixinAccount.getId(), weixinAccount.getSecret()), cacheStorager);
	}

	/**
	 * 第三方组件方�?创建微信接�?�实现(永久刷新令牌机制)
	 *
	 * @param perTicketManager
	 *            第三方组件永久刷新token
	 * @param componentTokenManager
	 *            第三方组件凭�?token
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi#getPerCodeManager(String)
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi#getTokenManager
	 */
	public WeixinProxy(PerTicketManager perTicketManager, TokenManager componentTokenManager) {
		this(new WeixinAccount(perTicketManager.getThirdId(), perTicketManager.getThirdSecret()),
				new WeixinTokenComponentCreator(perTicketManager, componentTokenManager),
				perTicketManager.getCacheStorager());
	}

	/**
	 * 微信接�?�实现
	 *
	 * @param weixinAccount
	 *            微信账�?�
	 * @param tokenCreator
	 *            token的创建
	 * @param cacheStorager
	 *            token的存储
	 */
	private WeixinProxy(WeixinAccount weixinAccount, TokenCreator tokenCreator, CacheStorager<Token> cacheStorager) {
		if (weixinAccount == null) {
			throw new IllegalArgumentException("weixinAccount must not be empty");
		}
		if (tokenCreator == null) {
			throw new IllegalArgumentException("tokenCreator must not be empty");
		}
		if (cacheStorager == null) {
			throw new IllegalArgumentException("cacheStorager must not be empty");
		}
		this.tokenManager = new TokenManager(tokenCreator, cacheStorager);
		this.weixinAccount = weixinAccount;
		this.cacheStorager = cacheStorager;
		this.oauthApi = new OauthApi(weixinAccount);
		this.mediaApi = new MediaApi(tokenManager);
		this.notifyApi = new NotifyApi(tokenManager);
		this.customApi = new CustomApi(tokenManager);
		this.massApi = new MassApi(tokenManager);
		this.userApi = new UserApi(tokenManager);
		this.groupApi = new GroupApi(tokenManager);
		this.menuApi = new MenuApi(tokenManager);
		this.qrApi = new QrApi(tokenManager);
		this.tmplApi = new TmplApi(tokenManager);
		this.helperApi = new HelperApi(tokenManager);
		this.dataApi = new DataApi(tokenManager);
		this.tagApi = new TagApi(tokenManager);
		this.cardApi = new CardApi(tokenManager);
		this.commentApi = new CommentApi(tokenManager);
	}

	/**
	 * 获�?�微信账�?�信�?�
	 *
	 * @return
	 */
	public WeixinAccount getWeixinAccount() {
		return weixinAccount;
	}

	/**
	 * token管�?�
	 *
	 * @return
	 */
	public TokenManager getTokenManager() {
		return this.tokenManager;
	}

	/**
	 * 获�?�oauth授�?�API
	 *
	 * @see com.foxinmy.weixin4j.mp.api.OauthApi
	 * @return
	 */
	public OauthApi getOauthApi() {
		return oauthApi;
	}

	/**
	 * 获�?�JSSDK Ticket的tokenManager
	 *
	 * @param ticketType
	 *            票�?�类型
	 * @return
	 */
	public TokenManager getTicketManager(TicketType ticketType) {
		return new TokenManager(new WeixinTicketCreator(ticketType, this.tokenManager), this.cacheStorager);
	}

	/**
	 * 上传图文消�?�内的图片获�?�URL
	 * 请注�?，本接�?�所上传的图片�?�?�用公众�?�的素�??库中图片数�?的5000个的�?制。图片仅支�?jpg/png格�?，大�?必须在1MB以下。
	 *
	 * @param is
	 *            图片数�?��?
	 * @param fileName
	 *            文件�?? 为空时将自动生�?
	 * @return 图片URL �?�用于�?�续群�?�中，放置到图文消�?�中
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @throws WeixinException
	 */
	public String uploadImage(InputStream is, String fileName) throws WeixinException {
		return mediaApi.uploadImage(is, fileName);
	}

	/**
	 * 上传群�?�中的视频素�??
	 *
	 * @param is
	 *            图片数�?��?
	 * @param fileName
	 *            文件�?? 为空时将自动生�?
	 * @param title
	 *            视频标题 �?�空
	 * @param description
	 *            视频�??述 �?�为空
	 * @return 群�?�视频消�?�对象
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      高级群�?�</a>
	 * @see com.foxinmy.weixin4j.tuple.MpVideo
	 */
	public MpVideo uploadVideo(InputStream is, String fileName, String title, String description)
			throws WeixinException {
		return mediaApi.uploadVideo(is, fileName, title, description);
	}

	/**
	 * 上传媒体文件 </br>
	 * <font color="red">此接�?��?�包�?�图片�?语音�?缩略图�?视频(临时)四�?媒体类型的上传</font>
	 * </p>
	 *
	 * @param isMaterial
	 *            是�?�永久上传
	 * @param is
	 *            媒体数�?��?
	 * @param fileName
	 *            文件�?? 为空时将自动生�?
	 * @return 上传到微信�?务器返回的媒体标识
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738726&token=&lang=zh_CN">
	 *      上传临时素�??</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">
	 *      上传永久素�??</a>
	 * @see com.foxinmy.weixin4j.model.media.MediaUploadResult
	 * @see com.foxinmy.weixin4j.type.MediaType
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @throws WeixinException
	 */
	public MediaUploadResult uploadMedia(boolean isMaterial, InputStream is, String fileName) throws WeixinException {
		return mediaApi.uploadMedia(isMaterial, is, fileName);
	}

	/**
	 * 下载媒体文件
	 *
	 * @param mediaId
	 *            媒体ID
	 * @param isMaterial
	 *            是�?�永久素�??
	 * @return 媒体文件下载结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see com.foxinmy.weixin4j.model.media.MediaDownloadResult
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738727&token=&lang=zh_CN">
	 *      下载临时媒体素�??</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738730&token=&lang=zh_CN">
	 *      下载永久媒体素�??</a>
	 */
	public MediaDownloadResult downloadMedia(String mediaId, boolean isMaterial) throws WeixinException {
		return mediaApi.downloadMedia(mediaId, isMaterial);
	}

	/**
	 * 上传永久图文素�??
	 * <p>
	 * �?新增的永久素�??也�?�以在公众平�?�官网素�??管�?�模�?�中看到,永久素�??的数�?是有上�?的，请谨慎新增。图文消�?�素�??和图片素�??的上�?为5000，
	 * 其他类型为1000
	 * </P>
	 *
	 * @param articles
	 *            图文列表
	 * @return 上传到微信�?务器返回的媒体标识
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">
	 *      上传永久媒体素�??</a>
	 */
	public String uploadMaterialArticle(List<MpArticle> articles) throws WeixinException {
		return mediaApi.uploadMaterialArticle(articles);
	}

	/**
	 * 下载永久图文素�??
	 *
	 * @param mediaId
	 *            媒体ID
	 * @return 图文列表
	 * @throws WeixinException
	 * @see {@link #downloadMedia(String, boolean)}
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 */
	public List<MpArticle> downloadArticle(String mediaId) throws WeixinException {
		return mediaApi.downloadArticle(mediaId);
	}

	/**
	 * 更新永久图文素�??
	 *
	 * @param mediaId
	 *            �?修改的图文消�?�的id
	 * @param index
	 *            �?更新的文章在图文消�?�中的�?置（多图文消�?�时，此字段�?有�?义），第一篇为0
	 * @param article
	 *            图文对象
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738732&token=&lang=zh_CN">
	 *      更新永久图文素�??</a>
	 */
	public ApiResult updateMaterialArticle(String mediaId, int index, MpArticle article) throws WeixinException {
		return mediaApi.updateMaterialArticle(mediaId, index, article);
	}

	/**
	 * 删除永久媒体素�??
	 *
	 * @param mediaId
	 *            媒体素�??的media_id
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738731&token=&lang=zh_CN">
	 *      删除永久媒体素�??</a>
	 */
	public ApiResult deleteMaterialMedia(String mediaId) throws WeixinException {
		return mediaApi.deleteMaterialMedia(mediaId);
	}

	/**
	 * 上传永久视频素�??
	 *
	 * @param is
	 *            大�?�?超过1M且格�?为MP4的视频文件
	 * @param fileName
	 *            文件�?? 为空时将自动生�?
	 * @param title
	 *            视频标题
	 * @param introduction
	 *            视频�??述
	 * @return 上传到微信�?务器返回的媒体标识
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">
	 *      上传永久媒体素�??</a>
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @throws WeixinException
	 */
	public String uploadMaterialVideo(InputStream is, String fileName, String title, String introduction)
			throws WeixinException {
		return mediaApi.uploadMaterialVideo(is, fileName, title, introduction);
	}

	/**
	 * 获�?�永久媒体素�??的总数</br>
	 * .图片和图文消�?�素�??（包括�?�图文和多图文）的总数上�?为5000，其他素�??的总数上�?为1000
	 *
	 * @return 总数对象
	 * @throws WeixinException
	 * @see com.com.foxinmy.weixin4j.model.media.MediaCounter
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738733&token=&lang=zh_CN">
	 *      获�?�素�??总数</a>
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 */
	public MediaCounter countMaterialMedia() throws WeixinException {
		return mediaApi.countMaterialMedia();
	}

	/**
	 * 获�?�媒体素�??记录列表
	 *
	 * @param mediaType
	 *            素�??的类型，图片（image）�?视频（video）�?语音 （voice）�?图文（news）
	 * @param pageable
	 *            分页数�?�
	 * @return 媒体素�??的记录对象
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see com.foxinmy.weixin4j.model.media.MediaRecord
	 * @see com.foxinmy.weixin4j.type.MediaType
	 * @see com.foxinmy.weixin4j.model.media.MediaItem
	 * @see com.foxinmy.weixin4j.model.paging.Pageable
	 * @see com.foxinmy.weixin4j.model.paging.Pagedata
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738734&token=&lang=zh_CN">
	 *      获�?�素�??列表</a>
	 */
	public MediaRecord listMaterialMedia(MediaType mediaType, Pageable pageable) throws WeixinException {
		return mediaApi.listMaterialMedia(mediaType, pageable);
	}

	/**
	 * 获�?�全部的媒体素�??
	 *
	 * @param mediaType
	 *            媒体类型
	 * @return 素�??列表
	 * @see com.foxinmy.weixin4j.mp.api.MediaApi
	 * @see {@link #listMaterialMedia(MediaType, Pageable)}
	 * @throws WeixinException
	 */
	public List<MediaItem> listAllMaterialMedia(MediaType mediaType) throws WeixinException {
		return mediaApi.listAllMaterialMedia(mediaType);
	}

	/**
	 * �?��?客�?消�?�(在48�?时内�?�?制�?��?次数)
	 *
	 * @param notify
	 *            客�?消�?�对象
	 * @return 处�?�结果
	 * @see {@link #sendNotify(NotifyMessage,String) }
	 * @throws WeixinException
	 */
	public ApiResult sendNotify(NotifyMessage notify) throws WeixinException {
		return notifyApi.sendNotify(notify);
	}

	/**
	 * �?��?客�?消�?�(在48�?时内�?�?制�?��?次数)
	 *
	 * @param notify
	 *            客�?消�?�对象
	 * @param kfAccount
	 *            客�?账�?� �?�为空
	 * @throws WeixinException
	 * @return 处�?�结果
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547&token=&lang=zh_CN">
	 *      �?��?客�?消�?�</a>
	 * @see com.foxinmy.weixin4j.tuple.Text
	 * @see com.foxinmy.weixin4j.tuple.Image
	 * @see com.foxinmy.weixin4j.tuple.Voice
	 * @see com.foxinmy.weixin4j.tuple.Video
	 * @see com.foxinmy.weixin4j.tuple.Music
	 * @see com.foxinmy.weixin4j.tuple.News
	 * @see com.foxinmy.weixin4j.mp.api.NotifyApi
	 */
	public ApiResult sendNotify(NotifyMessage notify, String kfAccount) throws WeixinException {
		return notifyApi.sendNotify(notify, kfAccount);
	}

	/**
	 * 客�?�?�天记录
	 *
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结�?�时间 �?次查询�?能跨日查询
	 * @param number
	 *            最多10000�?�
	 * @see com.foxinmy.weixin4j.mp.model.CustomRecord
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href="http://dkf.qq.com/document-1_1.html">查询客�?�?�天记录</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044854&token=&lang=zh_CN">
	 *      查询客�?�?�天记录</a>
	 * @throws WeixinException
	 */
	public List<KfChatRecord> getKfChatRecord(Date startTime, Date endTime, int number) throws WeixinException {
		return customApi.getKfChatRecord(startTime, endTime, number);
	}

	/**
	 * 获�?�公众�?�中所设置的客�?基本信�?�，包括客�?工�?��?客�?昵称�?客�?登录账�?�
	 *
	 * @return 多客�?信�?�列表
	 * @see com.foxinmy.weixin4j.mp.model.KfAccount
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      获�?�客�?基本信�?�</a>
	 * @throws WeixinException
	 */
	public List<KfAccount> listKfAccount() throws WeixinException {
		return customApi.listKfAccount();
	}

	/**
	 * 获�?�在线客�?在线状�?（手机在线�?PC客户端在线�?手机和PC客户端全都在线）�?客�?自动接入最大值�? 客�?当�?接待客户数
	 *
	 * @return 多客�?在线信�?�列表
	 * @see com.foxinmy.weixin4j.mp.model.KfOnlineAccount
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      获�?�客�?在线信�?�</a>
	 * @throws WeixinException
	 */
	public List<KfOnlineAccount> listOnlineKfAccount() throws WeixinException {
		return customApi.listOnlineKfAccount();
	}

	/**
	 * 新增客�?账�?�
	 *
	 * @param id
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�，账�?��?缀最多10个字符，必须是英文或者数字字符。如果没有公众�?�微信�?�，
	 *            请�?往微信公众平�?�设置。
	 * @param name
	 *            客�?昵称，最长6个汉字或12个英文字符
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi 客�?管�?�接�?�返回�?</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      新增客�?账�?�</a>
	 */
	public ApiResult createKfAccount(String id, String name) throws WeixinException {
		return customApi.createKfAccount(id, name);
	}

	/**
	 * 更新客�?账�?�
	 *
	 * @param id
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�，账�?��?缀最多10个字符，必须是英文或者数字字符。如果没有公众�?�微信�?�，
	 *            请�?往微信公众平�?�设置。
	 * @param name
	 *            客�?昵称，最长6个汉字或12个英文字符
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      更新客�?账�?�</a>
	 */
	public ApiResult updateKfAccount(String id, String name) throws WeixinException {
		return customApi.updateKfAccount(id, name);
	}

	/**
	 * 邀请绑定客�?�?�?�
	 * 新添加的客�?�?�?�是�?能直接使用的，�?�有客�?人员用微信�?�绑定了客�?账�?��?�，方�?�登录Web客�?进行�?作。此接�?��?�起一个绑定邀请到客�?人员微信�?�
	 * ，客�?人员需�?在微信客户端上用该微信�?�确认�?��?�?��?�?�用。尚未绑定微信�?�的�?�?��?�以进行绑定邀请�?作，邀请未失效时�?能对该�?�?�进行�?次绑定微信�?�邀请。
	 *
	 * @param kfAccount
	 *            完整客�?�?�?�，格�?为：�?�?��?缀@公众�?�微信�?�
	 * @param inviteAccount
	 *            接收绑定邀请的客�?微信�?�
	 * @return 处�?�结果
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN"
	 *      >邀请绑定客�?�?�?�<a/>
	 * @throws WeixinException
	 */
	public ApiResult inviteKfAccount(String kfAccount, String inviteAccount) throws WeixinException {
		return customApi.inviteKfAccount(kfAccount, inviteAccount);
	}

	/**
	 * 上传客�?头�?
	 *
	 * @param accountId
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�
	 * @param is
	 *            头�?图片文件必须是jpg格�?，推�??使用640*640大�?的图片以达到最佳效果
	 * @param fileName
	 *            文件�?? 为空时将自动生�?
	 * @return 处�?�结果
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      上传客�?头�?</a>
	 */
	public ApiResult uploadKfAvatar(String accountId, InputStream is, String fileName) throws WeixinException {
		return customApi.uploadKfAvatar(accountId, is, fileName);
	}

	/**
	 * 删除客�?账�?�
	 *
	 * @param id
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�
	 * @return 处�?�结果
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      删除客�?账�?�</a>
	 */
	public ApiResult deleteKfAccount(String id) throws WeixinException {
		return customApi.deleteKfAccount(id);
	}

	/**
	 * 创建客�?会�?
	 * <p>
	 * 开�?�者�?�以使用本接�?�，为多客�?的客�?工�?�创建会�?，将�?个客户直接指定给客�?工�?�接待，需�?注�?此接�?��?会�?�客�?自动接入数以�?�自动接入开关�?制。
	 * �?�能为在线的客�?（PC客户端在线，或者已绑定多客�?助手）创建会�?。
	 * </p>
	 *
	 * @param userOpenId
	 *            用户的userOpenId
	 * @param kfAccount
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�
	 * @param text
	 *            附加信�?�，文本会展示在客�?人员的多客�?客户端
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      创建会�?</a>
	 */
	public ApiResult createKfSession(String userOpenId, String kfAccount, String text) throws WeixinException {
		return customApi.createKfSession(userOpenId, kfAccount, text);
	}

	/**
	 * 关闭客�?会�?
	 *
	 * @param userOpenId
	 *            用户的userOpenId
	 * @param kfAccount
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�
	 * @param text
	 *            附加信�?�，文本会展示在客�?人员的多客�?客户端
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">
	 *      关闭会�?</a>
	 */
	public ApiResult closeKfSession(String userOpenId, String kfAccount, String text) throws WeixinException {
		return customApi.closeKfSession(userOpenId, kfAccount, text);
	}

	/**
	 * 获�?�客户的会�?状�?:获�?�客户当�?的会�?状�?。
	 *
	 * @param userOpenId
	 *            用户的openid
	 * @return 会�?对象
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see com.foxinmy.weixin4j.mp.model.KfSession
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">
	 *      获�?�会�?状�?</a>
	 */
	public KfSession getKfSession(String userOpenId) throws WeixinException {
		return customApi.getKfSession(userOpenId);
	}

	/**
	 * 获�?�客�?的会�?列表:获�?��?个客�?正在接待的会�?列表。
	 *
	 * @param kfAccount
	 *            完整客�?账�?�，格�?为：账�?��?缀@公众�?�微信�?�，账�?��?缀最多10个字符，必须是英文或者数字字符。
	 * @return 会�?列表
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see com.foxinmy.weixin4j.mp.model.KfSession
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">
	 *      获�?�客�?的会�?列表</a>
	 */
	public List<KfSession> listKfSession(String kfAccount) throws WeixinException {
		return customApi.listKfSession(kfAccount);
	}

	/**
	 * 获�?�未接入会�?列表:获�?�当�?正在等待队列中的会�?列表，此接�?�最多返回最早进入队列的100个未接入会�?
	 *
	 * @return 会�?列表
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.CustomApi
	 * @see com.foxinmy.weixin4j.mp.model.KfSession
	 * @see com.foxinmy.weixin4j.mp.model.KfSession.KfSessionCounter
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">
	 *      获�?�客�?的会�?列表</a>
	 */
	public KfSessionCounter listKfWaitSession() throws WeixinException {
		return customApi.listKfWaitSession();
	}

	/**
	 * 上传群�?�的图文消�?�,一个图文消�?�支�?1到10�?�图文
	 *
	 * @param articles
	 *            图片消�?�
	 * @return 媒体ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      上传图文素�??</a>
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 */
	public String uploadMassArticle(List<MpArticle> articles) throws WeixinException {
		return massApi.uploadArticle(articles);
	}

	/**
	 * 群�?�消�?�
	 * <p>
	 * 在返回�?功时,�?味�?�群�?�任务�??交�?功,并�?�?味�?�此时群�?�已�?结�?�,所以,�?有�?�能在�?�续的�?��?过程中出现异常情况导致用户未收到消�?�,
	 * 如消�?�有时会进行审核�?�?务器�?稳定等,此外,群�?�任务一般需�?较长的时间�?能全部�?��?完毕
	 * </p>
	 *
	 * @param MassTuple
	 *            消�?�元件
	 * @param isToAll
	 *            用于设定是�?��?�全部用户�?��?，值为true或false，选择true该消�?�群�?�给所有用户，
	 *            选择false�?�根�?�group_id�?��?给指定群组的用户
	 * @param groupId
	 *            分组ID
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现,�?�以用于在图文分�?数�?�接�?�中
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.tuple.Text
	 * @see com.foxinmy.weixin4j.tuple.Image
	 * @see com.foxinmy.weixin4j.tuple.Voice
	 * @see com.foxinmy.weixin4j.tuple.MpVideo
	 * @see com.foxinmy.weixin4j.tuple.MpNews
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @see com.foxinmy.weixin4j.tuple.MassTuple
	 * @see {@link #getGroups()}
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�分组群�?�</a>
	 */
	public String[] massByGroupId(MassTuple tuple, boolean isToAll, int groupId) throws WeixinException {
		return massApi.massByGroupId(tuple, isToAll, groupId);
	}

	/**
	 * 分组ID群�?�图文消�?�
	 *
	 * @param articles
	 *            图文列表
	 * @param groupId
	 *            分组ID
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现,�?�以用于在图文分�?数�?�接�?�中
	 * @see {@link #massByGroupId(Tuple,int)}
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�分组群�?�</a>
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @throws WeixinException
	 */
	public String[] massArticleByGroupId(List<MpArticle> articles, int groupId) throws WeixinException {
		return massApi.massArticleByGroupId(articles, groupId);
	}

	/**
	 * 群�?�消�?�给所有粉�?
	 *
	 * @param tuple
	 *            消�?�元件
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现,�?�以用于在图文分�?数�?�接�?�中
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�标签群�?�</a>
	 */
	public String[] massToAll(MassTuple tuple) throws WeixinException {
		return massApi.massToAll(tuple);
	}

	/**
	 * 标签群�?�消�?�
	 *
	 * @param tuple
	 *            消�?�元件
	 * @param tagId
	 *            标签ID
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现,�?�以用于在图文分�?数�?�接�?�中
	 * @throws WeixinException
	 * @see Tag
	 * @see {@link TagApi#listTags()}
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�标签群�?�</a>
	 */
	public String[] massByTagId(MassTuple tuple, int tagId) throws WeixinException {
		return massApi.massByTagId(tuple, tagId);
	}

	/**
	 * 标签群�?�图文消�?�
	 *
	 * @param articles
	 *            图文列表
	 * @param tagId
	 *            标签ID
	 * @param ignoreReprint
	 *            图文消�?�被判定为转载时，是�?�继续群�?�
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现。
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�标签群�?�</a>
	 * @see {@link #massByTagId(Tuple,int)}
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @throws WeixinException
	 */
	public String[] massArticleByTagId(List<MpArticle> articles, int tagId, boolean ignoreReprint)
			throws WeixinException {
		return massApi.massArticleByTagId(articles, tagId, ignoreReprint);
	}

	/**
	 * openId群�?�消�?�
	 *
	 * @param tuple
	 *            消�?�元件
	 * @param openIds
	 *            openId列表
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现,�?�以用于在图文分�?数�?�接�?�中
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�openid群�?�</a>
	 * @see {@link UserApi#getUser(String)}
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 */
	public String[] massByOpenIds(MassTuple tuple, String... openIds) throws WeixinException {
		return massApi.massByOpenIds(tuple, openIds);
	}

	/**
	 * openid群�?�图文消�?�
	 *
	 * @param articles
	 *            图文列表
	 * @param ignoreReprint
	 *            图文消�?�被判定为转载时，是�?�继续群�?�
	 * @param openIds
	 *            openId列表
	 * @return 第一个元素为消�?��?��?任务的ID,第二个元素为消�?�的数�?�ID，该字段�?�有在群�?�图文消�?�时，�?会出现,�?�以用于在图文分�?数�?�接�?�中.
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      根�?�openid群�?�</a>
	 * @see {@link #massByOpenIds(Tuple,String...)}
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @throws WeixinException
	 */
	public String[] massArticleByOpenIds(List<MpArticle> articles, boolean ignoreReprint, String... openIds)
			throws WeixinException {
		return massApi.massArticleByOpenIds(articles, ignoreReprint, openIds);
	}

	/**
	 * 删除群�?�消�?�
	 *
	 * @param msgid
	 *            �?��?出去的消�?�ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      删除群�?�</a>
	 * @see #deleteMassNews(String, int)
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 */
	public ApiResult deleteMassNews(String msgid) throws WeixinException {
		return massApi.deleteMassNews(msgid);
	}

	/**
	 * 删除群�?�消�?�
	 * <p>
	 * 请注�?,�?�有已�?�?��?�?功的消�?��?能删除删除消�?��?�是将消�?�的图文详情页失效,已�?收到的用户,还是能在其本地看到消�?��?�片
	 * </p>
	 *
	 * @param msgid
	 *            �?��?出去的消�?�ID
	 * @param articleIndex
	 *            �?删除的文章在图文消�?�中的�?置，第一篇编�?�为1，该字段�?填或填0会删除全部文章
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      删除群�?�</a>
	 * @see {@link #massByTagId(Tuple, int)}
	 * @see {@link #massByOpenIds(Tuple, String...)
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 */
	public ApiResult deleteMassNews(String msgid, int articleIndex) throws WeixinException {
		return massApi.deleteMassNews(msgid, articleIndex);
	}

	/**
	 * 预览群�?�消�?�</br>
	 * 开�?�者�?�通过该接�?��?��?消�?�给指定用户，在手机端查看消�?�的样�?和排版
	 *
	 * @param toUser
	 *            接收用户的openID
	 * @param toWxName
	 *            接收用户的微信�?� towxname和touser�?�时赋值时，以towxname优先
	 * @param tuple
	 *            消�?�元件
	 * @return 处�?�结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @see com.foxinmy.weixin4j.tuple.MassTuple
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      预览群�?�消�?�</a>
	 */
	public ApiResult previewMassNews(String toUser, String toWxName, MassTuple tuple) throws WeixinException {
		return massApi.previewMassNews(toUser, toWxName, tuple);
	}

	/**
	 * 查询群�?��?��?状�?
	 *
	 * @param msgId
	 *            消�?�ID
	 * @return 消�?��?��?状�?
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.MassApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN">
	 *      查询群�?�状�?</a>
	 */
	public String getMassNewStatus(String msgId) throws WeixinException {
		return massApi.getMassNewStatus(msgId);
	}

	/**
	 * 获�?�用户信�?�
	 *
	 * @param openId
	 *            用户对应的ID
	 * @return 用户对象
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">
	 *      获�?�用户信�?�</a>
	 * @see com.foxinmy.weixin4j.mp.model.User
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @see {@link #getUser(String,Lang)}
	 */
	public User getUser(String openId) throws WeixinException {
		return userApi.getUser(openId);
	}

	/**
	 * 获�?�用户信�?�
	 * <p>
	 * 在关注者与公众�?�产生消�?�交互�?�,公众�?��?�获得关注者的OpenID（加密�?�的微信�?�,�?个用户对�?个公众�?�的OpenID是唯一的,对于�?�?�公众�?�,
	 * �?�一用户的openid�?�?�）,公众�?��?�通过本接�?��?�根�?�OpenID获�?�用户基本信�?�,包括昵称�?头�?�?性别�?所在城市�?语言和关注时间
	 * </p>
	 *
	 * @param openId
	 *            用户对应的ID
	 * @param lang
	 *            国家地区语言版本
	 * @return 用户对象
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">
	 *      获�?�用户信�?�</a>
	 * @see com.foxinmy.weixin4j.mp.type.Lang
	 * @see com.foxinmy.weixin4j.mp.model.User
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 */
	public User getUser(String openId, Lang lang) throws WeixinException {
		return userApi.getUser(openId, lang);
	}

	/**
	 * 批�?获�?�用户信�?�
	 *
	 * @param openIds
	 *            用户ID
	 * @return 用户列表
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">
	 *      获�?�用户信�?�</a>
	 * @see com.foxinmy.weixin4j.mp.model.User
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @throws WeixinException
	 * @see {@link #getUsers(Lang,String[])}
	 */
	public List<User> getUsers(String... openIds) throws WeixinException {
		return userApi.getUsers(openIds);
	}

	/**
	 * 批�?获�?�用户信�?�
	 *
	 * @param lang
	 *            国家地区语言版本
	 * @param openIds
	 *            用户ID
	 * @return 用户列表
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">
	 *      获�?�用户信�?�</a>
	 * @see com.foxinmy.weixin4j.mp.type.Lang
	 * @see com.foxinmy.weixin4j.mp.model.User
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @throws WeixinException
	 */
	public List<User> getUsers(Lang lang, String... openIds) throws WeixinException {
		return userApi.getUsers(lang, openIds);
	}

	/**
	 * 获�?�公众�?�一定数�?(10000)的关注者列表 <font corlor="red">请慎�?使用</font>
	 *
	 * @param nextOpenId
	 *            下一次拉�?�数�?�的openid �?填写则默认从头开始拉�?�
	 * @return 关注者信�?� <font color="red">包�?�用户的详细信�?�</font>
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">
	 *      获�?�关注者列表</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140840&token=&lang=zh_CN">
	 *      批�?获�?�用户信�?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @see com.foxinmy.weixin4j.mp.model.Following
	 * @see com.foxinmy.weixin4j.mp.model.User
	 */
	public Following getFollowing(String nextOpenId) throws WeixinException {
		return userApi.getFollowing(nextOpenId);
	}

	/**
	 * 获�?�公众�?�一定数�?(10000)的关注者列表
	 *
	 * @param nextOpenId
	 *            下一次拉�?�数�?�的openid �?填写则默认从头开始拉�?�
	 * @return 关注者信�?� <font color="red">�?包�?�用户的详细信�?�</font>
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140840&token=&lang=zh_CN">
	 *      获�?�关注者列表</a>
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @see com.foxinmy.weixin4j.mp.model.Following
	 */
	public Following getFollowingOpenIds(String nextOpenId) throws WeixinException {
		return userApi.getFollowingOpenIds(nextOpenId);
	}

	/**
	 * 获�?�公众�?�全部的关注者列表 <font corlor="red">请慎�?使用</font>
	 * <p>
	 * 当公众�?�关注者数�?超过10000时,�?�通过填写next_openid的值,从而多次拉�?�列表的方�?�?�满足需求,
	 * 将上一次调用得到的返回中的next_openid值,作为下一次调用中的next_openid值
	 * </p>
	 *
	 * @return 用户对象集�?�
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140840&token=&lang=zh_CN">
	 *      获�?�关注者列表</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">
	 *      批�?获�?�用户信�?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @see com.foxinmy.weixin4j.mp.model.Following
	 * @see com.foxinmy.weixin4j.mp.model.User
	 * @see #getFollowing(String)
	 */
	public List<User> getAllFollowing() throws WeixinException {
		return userApi.getAllFollowing();
	}

	/**
	 * 获�?�公众�?�全部的关注者列表 <font corlor="red">请慎�?使用</font>
	 * <p>
	 * 当公众�?�关注者数�?超过10000时,�?�通过填写next_openid的值,从而多次拉�?�列表的方�?�?�满足需求,
	 * 将上一次调用得到的返回中的next_openid值,作为下一次调用中的next_openid值
	 * </p>
	 *
	 * @return 用户openid集�?�
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140840&token=&lang=zh_CN">
	 *      获�?�关注者列表</a>
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 * @see #getFollowingOpenIds(String)
	 */
	public List<String> getAllFollowingOpenIds() throws WeixinException {
		return userApi.getAllFollowingOpenIds();
	}

	/**
	 * 设置用户备注�??
	 *
	 * @param openId
	 *            用户ID
	 * @param remark
	 *            备注�??
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140838&token=&lang=zh_CN">
	 *      设置用户备注�??</a>
	 * @see com.foxinmy.weixin4j.mp.api.UserApi
	 */
	public ApiResult remarkUserName(String openId, String remark) throws WeixinException {
		return userApi.remarkUserName(openId, remark);
	}

	/**
	 * 创建分组
	 *
	 * @param name
	 *            组�??称
	 * @return group对象
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      创建分组</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.model.Group#toCreateJson()
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public Group createGroup(String name) throws WeixinException {
		return groupApi.createGroup(name);
	}

	/**
	 * 查询所有分组
	 *
	 * @return 组集�?�
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      查询所有分组</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public List<Group> getGroups() throws WeixinException {
		return groupApi.getGroups();
	}

	/**
	 * 查询用户所在分组
	 *
	 * @param openId
	 *            用户对应的ID
	 * @return 组ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      查询用户所在分组</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public int getGroupByOpenId(String openId) throws WeixinException {
		return groupApi.getGroupByOpenId(openId);
	}

	/**
	 * 修改分组�??
	 *
	 * @param groupId
	 *            组ID
	 * @param name
	 *            组�??称
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      修改分组�??</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public ApiResult modifyGroup(int groupId, String name) throws WeixinException {
		return groupApi.modifyGroup(groupId, name);
	}

	/**
	 * 移动用户到分组
	 *
	 * @param groupId
	 *            组ID
	 * @param openId
	 *            用户对应的ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN4">
	 *      移动分组</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public ApiResult moveGroup(int groupId, String openId) throws WeixinException {
		return groupApi.moveGroup(groupId, openId);
	}

	/**
	 * 批�?移动分组
	 *
	 * @param groupId
	 *            组ID
	 * @param openIds
	 *            用户ID列表(�?能超过50个)
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      批�?移动分组</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public ApiResult moveGroup(int groupId, String... openIds) throws WeixinException {
		return groupApi.moveGroup(groupId, openIds);
	}

	/**
	 * 删除用户分组,所有该分组内的用户自动进入默认分组.
	 *
	 * @param groupId
	 *            组ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      删除用户分组</a>
	 * @see com.foxinmy.weixin4j.mp.model.Group
	 * @see com.foxinmy.weixin4j.mp.api.GroupApi
	 */
	public ApiResult deleteGroup(int groupId) throws WeixinException {
		return groupApi.deleteGroup(groupId);
	}

	/**
	 * 自定义�?��?�
	 *
	 * @param buttons
	 *            �?��?�列表
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN">
	 *      创建自定义�?��?�</a>
	 * @see com.foxinmy.weixin4j.model.Button
	 * @see com.foxinmy.weixin4j.type.ButtonType
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 */
	public ApiResult createMenu(List<Button> buttons) throws WeixinException {
		return menuApi.createMenu(buttons);
	}

	/**
	 * 查询�?��?�
	 *
	 * @return �?��?�集�?�
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN">
	 *      查询�?��?�</a>
	 * @see com.foxinmy.weixin4j.model.Button
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 */
	public List<Button> getMenu() throws WeixinException {
		return menuApi.getMenu();
	}

	/**
	 * 查询全部�?��?�(包�?�个性化�?��?�)
	 *
	 * @return �?��?�集�?�
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN">
	 *      普通�?��?�</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN">
	 *      个性化�?��?�</a>
	 * @see com.foxinmy.weixin4j.model.Button
	 * @see com.foxinmy.weixin4j.mp.model.Menu
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 */
	public List<Menu> getAllMenu() throws WeixinException {
		return menuApi.getAllMenu();
	}

	/**
	 * 删除�?��?�
	 *
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN">
	 *      删除�?��?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 * @return 处�?�结果
	 */
	public ApiResult deleteMenu() throws WeixinException {
		return menuApi.deleteMenu();
	}

	/**
	 * 创建个性化�?��?�
	 *
	 * @param buttons
	 *            �?��?�列表
	 * @param matchRule
	 *            匹�?规则 至少�?有一个匹�?信�?�是�?为空
	 * @return �?��?�ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN">
	 *      创建个性化�?��?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 * @see com.foxinmy.weixin4j.model.Button
	 */
	public String createCustomMenu(List<Button> buttons, MenuMatchRule matchRule) throws WeixinException {
		return menuApi.createCustomMenu(buttons, matchRule);
	}

	/**
	 * 删除个性化�?��?�
	 *
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN">
	 *      删除个性化�?��?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 * @return 处�?�结果
	 */
	public ApiResult deleteCustomMenu(String menuId) throws WeixinException {
		return menuApi.deleteCustomMenu(menuId);
	}

	/**
	 * 测试个性化�?��?�匹�?结果
	 *
	 * @param userId
	 *            �?�以是粉�?的OpenID，也�?�以是粉�?的微信�?�。
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN">
	 *      测试个性化�?��?�</a>
	 * @see com.foxinmy.weixin4j.model.Button
	 * @see com.foxinmy.weixin4j.mp.api.MenuApi
	 * @throws WeixinException
	 * @return 匹�?到的�?��?��?置
	 */
	public List<Button> matchCustomMenu(String userId) throws WeixinException {
		return menuApi.matchCustomMenu(userId);
	}

	/**
	 * 生�?带�?�数的二维�?
	 *
	 * @param parameter
	 *            二维�?�?�数
	 * @return 二维�?结果对象
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.model.qr.QRResult
	 * @see com.foxinmy.weixin4j.model.qr.QRParameter
	 * @see com.foxinmy.weixin4j.mp.api.QrApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1443433542&token=&lang=zh_CN">
	 *      生�?二维�?</a>
	 */
	public QRResult createQR(QRParameter parameter) throws WeixinException {
		return qrApi.createQR(parameter);
	}

	/**
	 * 设置所属行业(�?月�?�修改行业1次，账�?�仅�?�使用所属行业中相关的模�?�)
	 *
	 * @param industryTypes
	 *            所处行业 目�?�?超过两个
	 * @return �?作结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.type.IndustryType
	 * @see com.foxinmy.weixin4j.mp.api.TmplApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN">
	 *      设置所处行业</a>
	 */
	public ApiResult setTmplIndustry(IndustryType... industryTypes) throws WeixinException {
		return tmplApi.setTmplIndustry(industryTypes);
	}

	/**
	 * 获�?�模�?�ID
	 *
	 * @param shortId
	 *            模�?�库中模�?�的编�?�，有“TM**�?和“OPENTMTM**�?等形�?
	 * @return 模�?�ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN">
	 *      获得模�?�ID</a>
	 * @see com.foxinmy.weixin4j.mp.api.TmplApi
	 */
	public String getTemplateId(String shortId) throws WeixinException {
		return tmplApi.getTemplateId(shortId);
	}

	/**
	 * 获�?�模�?�列表
	 *
	 * @return 模�?�列表
	 * @see com.foxinmy.weixin4j.mp.model.TemplateMessageInfo
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN">
	 *      获�?�模�?�列表</a>
	 * @see com.foxinmy.weixin4j.mp.api.TmplApi
	 * @throws WeixinException
	 */
	public List<TemplateMessageInfo> getAllTemplates() throws WeixinException {
		return tmplApi.getAllTemplates();
	}

	/**
	 * 删除模�?�
	 *
	 * @param templateId
	 *            公众�?�?�下模�?�消�?�ID
	 * @return 处�?�结果
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN">
	 *      删除模�?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.TmplApi
	 * @throws WeixinException
	 */
	public ApiResult deleteTemplate(String templateId) throws WeixinException {
		return tmplApi.deleteTemplate(templateId);
	}

	/**
	 * �?��?模�?�消�?�
	 *
	 * @param tplMessage
	 *            模�?�消�?�主体
	 * @return �?��?的消�?�ID
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN">
	 *      模�?�消�?�</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751288&token=&lang=zh_CN"
	 *      >�?�?�规范</a>
	 * @see com.foxinmy.weixin4j.mp.message.TemplateMessage
	 * @seee com.foxinmy.weixin4j.msg.event.TemplatesendjobfinishMessage
	 * @see com.foxinmy.weixin4j.mp.api.TmplApi
	 */
	public String sendTmplMessage(TemplateMessage tplMessage) throws WeixinException {
		return tmplApi.sendTmplMessage(tplMessage);
	}

	/**
	 * 长链接转短链接
	 *
	 * @param url
	 *            待转�?�的链接
	 * @return 短链接
	 * @throws WeixinException
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1443433600&token=&lang=zh_CN">
	 *      长链接转短链接</a>
	 * @see com.foxinmy.weixin4j.mp.api.HelperApi
	 */
	public String getShorturl(String url) throws WeixinException {
		return helperApi.getShorturl(url);
	}

	/**
	 * 语义�?�解
	 *
	 * @param semQuery
	 *            语义�?�解�??议
	 * @return 语义�?�解结果
	 * @see com.foxinmy.weixin4j.mp.model.SemQuery
	 * @see com.foxinmy.weixin4j.mp.model.SemResult
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141241&token=&lang=zh_CN">
	 *      语义�?�解</a>
	 * @see com.foxinmy.weixin4j.mp.api.HelperApi
	 * @throws WeixinException
	 */
	public SemResult semantic(SemQuery semQuery) throws WeixinException {
		return helperApi.semantic(semQuery);
	}

	/**
	 * 获�?�微信�?务器IP地�?�
	 *
	 * @return IP地�?�
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140187&token=&lang=zh_CN">
	 *      获�?�IP地�?�</a>
	 * @see com.foxinmy.weixin4j.mp.api.HelperApi
	 * @throws WeixinException
	 */
	public List<String> getWechatServerIp() throws WeixinException {
		return helperApi.getWechatServerIp();
	}

	/**
	 * 接�?�调用次数调用清零：公众�?�调用接�?�并�?是无�?制的。为了防止公众�?�的程�?错误而引�?�微信�?务器负载异常，默认情况下，
	 * �?个公众�?�调用接�?�都�?能超过一定�?制 ，当超过一定�?制时，调用对应接�?�会收到{"errcode":45009,"errmsg":"api freq
	 * out of limit" }错误返回�?。
	 *
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433744592&token=&lang=zh_CN">
	 *      接�?�清零</a>
	 * @see com.foxinmy.weixin4j.mp.api.HelperApi
	 * @return �?作结果
	 * @throws WeixinException
	 */
	public ApiResult clearQuota() throws WeixinException {
		return helperApi.clearQuota(weixinAccount.getId());
	}

	/**
	 * 获�?�公众�?�当�?使用的自定义�?��?�的�?置，如果公众�?�是通过API调用设置的�?��?�，则返回�?��?�的开�?��?置，
	 * 而如果公众�?�是在公众平�?�官网通过网站功能�?�布�?��?�，则本接�?�返回�?�?�者设置的�?��?��?置。
	 *
	 * @return �?��?�集�?�
	 * @see {@link #getMenu()}
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1434698695&token=&lang=zh_CN">
	 *      获�?�自定义�?��?��?置</a>
	 * @see com.foxinmy.weixin4j.model.Button
	 * @se com.foxinmy.weixin4j.mp.model.MenuSetting
	 * @see com.foxinmy.weixin4j.tuple.MpArticle
	 * @see com.foxinmy.weixin4j.mp.api.HelperApi
	 * @throws WeixinException
	 */
	public MenuSetting getMenuSetting() throws WeixinException {
		return helperApi.getMenuSetting();
	}

	/**
	 * 获�?�公众�?�当�?使用的自动回�?规则，包括关注�?�自动回�?�?消�?�自动回�?（60分钟内触�?�一次）�?关键�?自动回�?。
	 *
	 * @see com.foxinmy.weixin4j.mp.model.AutoReplySetting
	 * @see com.foxinmy.weixin4j.mp.api.HelperApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751299&token=&lang=zh_CN">
	 *      获�?�自动回�?规则</a>
	 * @throws WeixinException
	 */
	public AutoReplySetting getAutoReplySetting() throws WeixinException {
		return helperApi.getAutoReplySetting();
	}

	/**
	 * 数�?�统计
	 *
	 * @param datacubeType
	 *            数�?�统计类型
	 * @param beginDate
	 *            获�?�数�?�的起始日期，begin_date和end_date的差值需�?于“最大时间跨度�?（比如最大时间跨度为1时，
	 *            begin_date和end_date的差值�?�能为0，�?能�?于1），�?�则会报错
	 * @param endDate
	 *            获�?�数�?�的结�?�日期，end_date�?许设置的最大值为昨日
	 * @see com.foxinmy.weixin4j.mp.api.DataApi
	 * @see com.foxinmy.weixin4j.mp.datacube.UserSummary
	 * @see com.foxinmy.weixin4j.mp.datacube.ArticleSummary
	 * @see com.foxinmy.weixin4j.mp.datacube.ArticleTotal
	 * @see com.foxinmy.weixin4j.mp.datacube.ArticleDatacubeShare
	 * @see com.foxinmy.weixin4j.mp.datacube.UpstreamMsg
	 * @see com.foxinmy.weixin4j.mp.datacube.UpstreamMsgDist
	 * @see com.foxinmy.weixin4j.mp.datacube.InterfaceSummary
	 * @return 统计结果
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">
	 *      用户分�?</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">
	 *      图文分�?</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">
	 *      消�?�分�?</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">
	 *      接�?�分�?</a>
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, Date beginDate, Date endDate) throws WeixinException {
		return dataApi.datacube(datacubeType, beginDate, endDate);
	}

	/**
	 * 数�?�统计
	 *
	 * @param datacubeType
	 *            统计类型
	 * @param beginDate
	 *            开始日期
	 * @param offset
	 *            增�? 表示�?��?几天 比如 offset=1 则查询 beginDate的�?�一天之间的数�?�
	 * @see {@link #datacube(DatacubeType, Date,Date)}
	 * @see com.foxinmy.weixin4j.mp.api.DataApi
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, Date beginDate, int offset) throws WeixinException {
		return dataApi.datacube(datacubeType, beginDate, offset);
	}

	/**
	 * 数�?�统计
	 *
	 * @param datacubeType
	 *            统计类型
	 * @param offset
	 *            增�? 表示�?��?�几天 比如 offset=1 则查询 beginDate的�?一天之间的数�?�
	 * @param endDate
	 *            截至日期
	 * @see {@link #datacube(DatacubeType, Date,Date)}
	 * @see com.foxinmy.weixin4j.mp.api.DataApi
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, int offset, Date endDate) throws WeixinException {
		return dataApi.datacube(datacubeType, offset, endDate);
	}

	/**
	 * 查询日期跨度为0的统计数�?�(当天)
	 *
	 * @param datacubeType
	 *            统计类型
	 * @param date
	 *            统计日期
	 * @see {@link #datacube(DatacubeType, Date,Date)}
	 * @see com.foxinmy.weixin4j.mp.api.DataApi
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, Date date) throws WeixinException {
		return dataApi.datacube(datacubeType, date);
	}

	/**
	 * 创建标签
	 *
	 * @param name
	 *            标签�??（30个字符以内）
	 * @return 标签对象
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see com.foxinmy.weixin4j.mp.model.Tag
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      创建标签</a>
	 */
	public Tag createTag(String name) throws WeixinException {
		return tagApi.createTag(name);
	}

	/**
	 * 获�?�标签
	 *
	 * @return 标签列表
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see com.foxinmy.weixin4j.mp.model.Tag
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      获�?�标签</a>
	 */
	public List<Tag> listTags() throws WeixinException {
		return tagApi.listTags();
	}

	/**
	 * 更新标签
	 *
	 * @param tag
	 *            标签对象
	 * @return �?作结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see com.foxinmy.weixin4j.mp.model.Tag
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      更新标签</a>
	 */
	public ApiResult updateTag(Tag tag) throws WeixinException {
		return tagApi.updateTag(tag);
	}

	/**
	 * 删除标签
	 *
	 * @param tagId
	 *            标签id
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      删除标签</a>
	 */
	public ApiResult deleteTag(int tagId) throws WeixinException {
		return tagApi.deleteTag(tagId);
	}

	/**
	 * 批�?为用户打标签:标签功能目�?支�?公众�?�为用户打上最多三个标签
	 *
	 * @param tagId
	 *            标签ID
	 * @param openIds
	 *            用户ID
	 * @return �?作结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      批�?为用户打标签</a>
	 */
	public ApiResult taggingUsers(int tagId, String... openIds) throws WeixinException {
		return tagApi.taggingUsers(tagId, openIds);
	}

	/**
	 * 批�?为用户�?�消标签
	 *
	 * @param tagId
	 *            标签ID
	 * @param openIds
	 *            用户ID
	 * @return �?作结果
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      批�?为用户�?�消标签</a>
	 */
	public ApiResult untaggingUsers(int tagId, String... openIds) throws WeixinException {
		return tagApi.untaggingUsers(tagId, openIds);
	}

	/**
	 * 获�?�标签下粉�?列表
	 *
	 * @param tagId
	 *            标签ID
	 * @param nextOpenId
	 *            第一个拉�?�的OPENID，�?填默认从头开始拉�?�
	 * @return 用户openid列表
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      获�?�标签下粉�?列表</a>
	 */
	public Following getTagFollowingOpenIds(int tagId, String nextOpenId) throws WeixinException {
		return tagApi.getTagFollowingOpenIds(tagId, nextOpenId);
	}

	/**
	 * 获�?�标签下粉�?列表 <font corlor="red">请慎�?使用</font>
	 *
	 * @param tagId
	 *            标签ID
	 * @param nextOpenId
	 *            第一个拉�?�的OPENID，�?填默认从头开始拉�?�
	 * @return 被打标签者信�?� <font color="red">包�?�用户的详细信�?�</font>
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      获�?�标签下粉�?列表</a>
	 */
	public Following getTagFollowing(int tagId, String nextOpenId) throws WeixinException {
		return tagApi.getTagFollowing(tagId, nextOpenId);
	}

	/**
	 * 获�?�标签下全部的粉�?列表 <font corlor="red">请慎�?使用</font>
	 *
	 * @param tagId
	 *            标签ID
	 * @return 用户openid列表
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see #getTagFollowingOpenIds(int,String)
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      获�?�标签下粉�?列表</a>
	 */
	public List<String> getAllTagFollowingOpenIds(int tagId) throws WeixinException {
		return tagApi.getAllTagFollowingOpenIds(tagId);
	}

	/**
	 * 获�?�标签下全部的粉�?列表 <font corlor="red">请慎�?使用</font>
	 *
	 * @param tagId
	 *            标签ID
	 * @return 被打标签者信�?� <font color="red">包�?�用户的详细信�?�</font>
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see #getTagFollowing(int,String)
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      获�?�标签下粉�?列表</a>
	 */
	public List<User> getAllTagFollowing(int tagId) throws WeixinException {
		return tagApi.getAllTagFollowing(tagId);
	}

	/**
	 * 获�?�用户身上的标签列表
	 *
	 * @param openId
	 *            用户ID
	 * @return 标签ID集�?�
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">
	 *      获�?�用户身上的标签列表</a>
	 */
	public Integer[] getUserTags(String openId) throws WeixinException {
		return tagApi.getUserTags(openId);
	}

	/**
	 * 获�?�公众�?�的黑�??�?�列表
	 *
	 * @param nextOpenId
	 *            下一次拉�?�数�?�的openid �?填写则默认从头开始拉�?�
	 * @return 拉黑用户列表 <font color="red">�?包�?�用户的详细信�?�</font>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1471422259_pJMWA&token=&lang=zh_CN"
	 *      >获�?�黑�??�?�列表</a>
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see com.foxinmy.weixin4j.mp.model.Following
	 * @throws WeixinException
	 */
	public Following getBalcklistOpenIds(String nextOpenId) throws WeixinException {
		return tagApi.getBalcklistOpenIds(nextOpenId);
	}

	/**
	 * 获�?�公众�?�全部的黑�??�?�列表 <font corlor="red">请慎�?使用</font>
	 * <p>
	 * 当公众�?�关注者数�?超过10000时,�?�通过填写next_openid的值,从而多次拉�?�列表的方�?�?�满足需求,
	 * 将上一次调用得到的返回中的next_openid值,作为下一次调用中的next_openid值
	 * </p>
	 *
	 * @return 用户openid集�?�
	 * @throws WeixinException
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1471422259_pJMWA&token=&lang=zh_CN">
	 *      获�?�黑�??�?�列表</a>
	 * @see #getFollowingOpenIds(String)
	 */
	public List<String> getAllBalcklistOpenIds() throws WeixinException {
		return tagApi.getAllBalcklistOpenIds();
	}

	/**
	 * 黑�??�?��?作
	 *
	 * @param blacklist
	 *            true=拉黑用户,false=�?�消拉黑用户
	 * @param openIds
	 *            用户ID列表
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.TagApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1471422259_pJMWA&token=&lang=zh_CN">
	 *      黑�??�?��?作</a>
	 * @throws WeixinException
	 */
	public ApiResult batchBlacklist(boolean blacklist, String... openIds) throws WeixinException {
		return tagApi.batchBlacklist(blacklist, openIds);
	}

	/**
	 * 创建�?�券:创建�?�券接�?�是微信�?�券的基础接�?�，用于创建一类新的�?�券，获�?�card_id，创建�?功并通过审核�?�，
	 * 商家�?�以通过文档�??供的其他接�?�将�?�券下�?�给用户，�?次�?功领�?�，库存数�?相应扣除。
	 *
	 * <li>1.需自定义Code�?的商家必须在创建�?�券时候，设定use_custom_code为true，且在调用投放�?�券接�?�时填入指定的Code�?。
	 * 指定OpenID�?��?�。特别注�?：在公众平�?�创建的�?�券�?�为�?�自定义Code类型。
	 * <li>2.can_share字段指领�?��?�券原生页�?�是�?��?�分享，建议指定Code�?�?指定OpenID等强�?制�?�件的�?�券填写false。
	 * <li>3.创建�?功�?�该�?�券会自动�??交审核
	 * ，审核结果将通过事件通知商户。开�?�者�?�调用设置白�??�?�接�?�设置用户白�??�?�，领�?�未通过审核的�?�券，测试整个�?�券的使用�?程。
	 *
	 * @param cardCoupon
	 *            �?�券对象
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025056&token=&lang=zh_CN">
	 *      创建�?�券</a>
	 * @see CardCoupons
	 * @see MediaApi#uploadImage(java.io.InputStream, String)
	 * @see com.foxinmy.weixin4j.mp.api.CardApi
	 * @return �?�券ID
	 * @throws WeixinException
	 */
	public String createCardCoupon(CardCoupon cardCoupon) throws WeixinException {
		return cardApi.createCardCoupon(cardCoupon);
	}

	/**
	 * 设置�?�券买�?�：创建�?�券之�?�，开�?�者�?�以通过设置微信买�?�接�?�设置该card_id支�?微信买�?�功能。值得开�?�者注�?的是，
	 * 设置买�?�的card_id必须已�?�?置了门店，�?�则会报错。
	 *
	 * @param cardId
	 *            �?�券ID
	 * @param isOpen
	 *            是�?�开�?�买�?�功能，填true/false
	 * @see #createCardCoupon(CardCoupon)
	 * @see com.foxinmy.weixin4j.mp.api.CardApi
	 * @return �?作结果
	 * @throws WeixinException
	 */
	public ApiResult setCardPayCell(String cardId, boolean isOpen) throws WeixinException {
		return cardApi.setCardPayCell(cardId, isOpen);
	}

	/**
	 * 设置自助核销:创建�?�券之�?�，开�?�者�?�以通过设置微信买�?�接�?�设置该card_id支�?自助核销功能。值得开�?�者注�?的是，
	 * 设置自助核销的card_id必须已�?�?置了门店，�?�则会报错。
	 *
	 * @param cardId
	 *            �?�券ID
	 * @param isOpen
	 *            是�?�开�?�买�?�功能，填true/false
	 * @see #createCardCoupon(CardCoupon)
	 * @see com.foxinmy.weixin4j.mp.api.CardApi
	 * @return �?作结果
	 * @throws WeixinException
	 */
	public ApiResult setCardSelfConsumeCell(String cardId, boolean isOpen) throws WeixinException {
		return cardApi.setCardSelfConsumeCell(cardId, isOpen);
	}

	/**
	 * 创建�?�券二维�?： 开�?�者�?�调用该接�?�生�?一张�?�券二维�?供用户扫�?�?�添加�?�券到�?�包。
	 *
	 * @param expireSeconds
	 *            指定二维�?的有效时间，范围是60 ~ 1800秒。�?填默认为365天有效
	 * @param cardQRs
	 *            二维�?�?�数:二维�?领�?��?�张�?�券/多张�?�券
	 * @return 二维�?结果对象
	 * @see com.foxinmy.weixin4j.model.qr.QRResult
	 * @see com.foxinmy.weixin4j.model.qr.QRParameter
	 * @see com.foxinmy.weixin4j.mp.api.CardApi
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025062&token=&lang=zh_CN">
	 *      投放�?�券</a>
	 * @throws WeixinException
	 */
	public QRResult createCardQR(Integer expireSeconds, CardQR... cardQRs) throws WeixinException {
		return cardApi.createCardQR(expireSeconds, cardQRs);
	}

	/**
	 * 打开/关闭已群�?�文章评论
	 *
	 * @param open
	 *            true为打开，false为关闭
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see {@link MassApi#massByTagId(com.foxinmy.weixin4j.tuple.MassTuple, int)}
	 * @see {@link MassApi#massByOpenIds(com.foxinmy.weixin4j.tuple.MassTuple, String...)}
	 * @throws WeixinException
	 */
	public ApiResult openComment(boolean open, String msgid, int index) throws WeixinException {
		return commentApi.openComment(open, msgid, index);
	}

	/**
	 * 获�?�评论列表
	 *
	 * @param page
	 *            分页信�?�
	 * @param commentType
	 *            评论类型 为空获�?�全部类型
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @return 分页数�?�
	 * @see ArticleComment
	 * @see ArticleCommentType
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see {@link MassApi#massByTagId(com.foxinmy.weixin4j.tuple.MassTuple, int)}
	 * @see {@link MassApi#massByOpenIds(com.foxinmy.weixin4j.tuple.MassTuple, String...)}
	 * @throws WeixinException
	 */
	public Pagedata<ArticleComment> listArticleComments(Pageable page, ArticleCommentType commentType, String msgid,
			int index) throws WeixinException {
		return commentApi.listArticleComments(page, commentType, msgid, index);
	}

	/**
	 * 获�?�评论列表
	 *
	 * @param commentType
	 *            评论类型 为空获�?�全部类型
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @return 分页数�?�
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see #listArticleComments(Pageable, ArticleCommentType, String, int)
	 * @throws WeixinException
	 */
	public List<ArticleComment> listAllArticleComments(ArticleCommentType commentType, String msgid, int index)
			throws WeixinException {
		return commentApi.listAllArticleComments(commentType, msgid, index);
	}

	/**
	 * 评论标记/�?�消精选
	 *
	 * @param markelect
	 *            true为标记，false为�?�消
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @param commentId
	 *            用户评论ID
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see #listArticleComments(Pageable, ArticleCommentType, String, int)
	 * @throws WeixinException
	 */
	public ApiResult markelectComment(boolean markelect, String msgid, int index, String commentId)
			throws WeixinException {
		return commentApi.markelectComment(markelect, msgid, index, commentId);
	}

	/**
	 * 删除评论
	 *
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @param commentId
	 *            用户评论ID
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see #listArticleComments(Pageable, ArticleCommentType, String, int)
	 * @throws WeixinException
	 */
	public ApiResult deleteComment(String msgid, int index, String commentId) throws WeixinException {
		return commentApi.deleteComment(msgid, index, commentId);
	}

	/**
	 * 回�?评论
	 *
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @param commentId
	 *            用户评论ID
	 * @param content
	 *            回�?内容
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see #listArticleComments(Pageable, ArticleCommentType, String, int)
	 * @throws WeixinException
	 */
	public ApiResult replyComment(String msgid, int index, String commentId, String content) throws WeixinException {
		return commentApi.replyComment(msgid, index, commentId, content);
	}

	/**
	 * 删除回�?
	 *
	 * @param msgid
	 *            群�?�返回的msg_data_id
	 * @param index
	 *            多图文时，用�?�指定第几篇图文，从0开始，�?带默认�?作该msg_data_id的第一篇图文
	 * @param commentId
	 *            用户评论ID
	 * @return �?作结果
	 * @see com.foxinmy.weixin4j.mp.api.ComponentApi
	 * @see #listArticleComments(Pageable, ArticleCommentType, String, int)
	 * @throws WeixinException
	 */
	public ApiResult deleteCommentReply(String msgid, int index, String commentId) throws WeixinException {
		return commentApi.deleteCommentReply(msgid, index, commentId);
	}

	public final static String VERSION = Consts.VERSION;
}
