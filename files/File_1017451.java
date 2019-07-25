package com.foxinmy.weixin4j.qy.api;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import com.foxinmy.weixin4j.qy.model.Party;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.type.InviteType;
import com.foxinmy.weixin4j.qy.type.UserStatus;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.util.NameValue;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 * �?员API
 * 
 * @className UserApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014年11月19日
 * @since JDK 1.6
 * @see com.foxinmy.weixin4j.qy.model.User
 * @see <a href= "http://work.weixin.qq.com/api/doc#10018">管�?��?员说明</a>
 */
public class UserApi extends QyApi {
	private final MediaApi mediaApi;
	private final PartyApi partyApi;
	private final TokenManager tokenManager;

	public UserApi(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
		this.mediaApi = new MediaApi(tokenManager);
		this.partyApi = new PartyApi(tokenManager);
	}

	/**
	 * 创建�?员
	 * 
	 * @param user
	 *            �?员对象
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @see <a href= "http://work.weixin.qq.com/api/doc#10018"> 创建�?员说明</a>
	 * @return 处�?�结果
	 * @throws WeixinException
	 */
	public ApiResult createUser(User user) throws WeixinException {
		String user_create_uri = getRequestUri("user_create_uri");
		return excute(user_create_uri, user, null);
	}

	/**
	 * 创建�?员
	 * 
	 * @param user
	 *            �?员对象
	 * @param avatar
	 *            头�?文件 �?�为空
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @see <a href= "http://work.weixin.qq.com/api/doc#10018"> 创建�?员说明</a>
	 * @return 处�?�结果
	 * @throws WeixinException
	 */
	public ApiResult createUser(User user, InputStream avatar)
			throws WeixinException {
		String user_create_uri = getRequestUri("user_create_uri");
		return excute(user_create_uri, user, avatar);
	}

	/**
	 * 更新用户(如果�?�必须的字段未指定 则�?更新该字段之�?的设置值)
	 * 
	 * @param user
	 *            �?员对象
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @see <a href= "http://work.weixin.qq.com/api/doc#10020"> 更新�?员说明</a>
	 * @return 处�?�结果
	 * @throws WeixinException
	 */
	public ApiResult updateUser(User user) throws WeixinException {
		String user_update_uri = getRequestUri("user_update_uri");
		return excute(user_update_uri, user, null);
	}

	/**
	 * 更新用户(如果�?�必须的字段未指定 则�?更新该字段之�?的设置值)
	 * 
	 * @param user
	 *            �?员对象
	 * @param avatar
	 *            头�?文件
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @see <a href= "http://work.weixin.qq.com/api/doc#10020"> 更新�?员说明</a>
	 * @return 处�?�结果
	 * @throws WeixinException
	 */
	public ApiResult updateUser(User user, InputStream avatar)
			throws WeixinException {
		String user_update_uri = getRequestUri("user_update_uri");
		return excute(user_update_uri, user, avatar);
	}

	private ApiResult excute(String uri, User user, InputStream avatar)
			throws WeixinException {
		JSONObject obj = (JSONObject) JSON.toJSON(user);
		Object val = obj.remove("extattr");
		if (val != null) {
			JSONObject attrs = new JSONObject();
			attrs.put("attrs", val);
			obj.put("extattr", attrs);
		}
		val = obj.remove("status");
		if (val != null) {
			obj.put("enable", val);
		}
		if (avatar != null) {
			obj.put("avatar_mediaid", mediaApi.uploadMedia(0, avatar, null));
		} else {
			obj.put("avatar_mediaid", obj.remove("avatar"));
		}
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.post(
				String.format(uri, token.getAccessToken()), obj.toJSONString());
		return response.getAsResult();
	}

	/**
	 * 获�?��?员
	 * 
	 * @param userid
	 *            �?员唯一ID
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @see <a href= "http://work.weixin.qq.com/api/doc#10019">获�?��?员说明</a>
	 * @return �?员对象
	 * @throws WeixinException
	 */
	public User getUser(String userid) throws WeixinException {
		String user_get_uri = getRequestUri("user_get_uri");
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_get_uri, token.getAccessToken(), userid));
		JSONObject obj = response.getAsJson();
		Object attrs = obj.remove("extattr");
		User user = JSON.toJavaObject(obj, User.class);
		if (attrs != null) {
			user.setExtattr(JSON.parseArray(
					((JSONObject) attrs).getString("attrs"), NameValue.class));
		}
		return user;
	}

	/**
	 * 根�?�code获�?�用户信�?�
	 * 
	 * @param code
	 *            通过员工授�?�获�?�到的code，�?次员工授�?�带上的code将�?一样，code�?�能使用一次，5分钟未被使用自动过期
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @return �?员对象
	 * @see {@link #getUser(String)}
	 * @see {@link #getUserIdByCode(String)}
	 * @see <a href= "http://work.weixin.qq.com/api/doc#10028/根�?�code获�?��?员信�?�">
	 *      oauth授�?�获�?�用户信�?�</a>
	 * @throws WeixinException
	 */
	public User getUserByCode(String code) throws WeixinException {
		JSONObject result = getUserIdByCode(code);
		if (result.containsKey("user_ticket")) {
			String user_ticket_detail_uri = getRequestUri("user_ticket_detail_uri");
			Token token = tokenManager.getCache();
			WeixinResponse response = weixinExecutor.post(
					String.format(user_ticket_detail_uri,
							token.getAccessToken()),
					String.format("{\"user_ticket\":\"%s\"}",
							result.getString("user_ticket")));
			JSONObject obj = response.getAsJson();
			Object attrs = obj.remove("extattr");
			User user = JSON.toJavaObject(obj, User.class);
			if (attrs != null) {
				user.setExtattr(JSON.parseArray(
						((JSONObject) attrs).getString("attrs"),
						NameValue.class));
			}
			return user;
		} else {
			String userId = result.getString("UserId");
			if (StringUtil.isBlank(userId)) {
				userId = openid2userid(result.getString("OpenId"));
			}
			return getUser(userId);
		}
	}

	/**
	 * 根�?�code获�?��?员ID信�?�
	 * 
	 * @param code
	 *            通过员工授�?�获�?�到的code，�?次员工授�?�带上的code将�?一样，code�?�能使用一次，5分钟未被使用自动过期
	 * @return �?��?�结果
	 * @see <a href= "https://work.weixin.qq.com/api/doc#10028">
	 *      oauth授�?�获�?�用户信�?�</a>
	 * @throws WeixinException
	 */
	public JSONObject getUserIdByCode(String code) throws WeixinException {
		String user_getid_uri = getRequestUri("user_getid_uri");
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_getid_uri, token.getAccessToken(), code));
		return response.getAsJson();
	}

	/**
	 * 获�?��?业�?�管�?�员登录信�?�
	 * 
	 * @param authCode
	 *            oauth2.0授�?��?业�?�管�?�员登录产生的code
	 * @return 登陆信�?�
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E5%91%98%E7%99%BB%E5%BD%95%E4%BF%A1%E6%81%AF">
	 *      授�?�获�?��?业�?�管�?�员登录信�?�</a>
	 * @see com.foxinmy.weixin4j.qy.model.OUserInfo
	 * @throws WeixinException
	 */
	public OUserInfo getOUserInfoByCode(String authCode) throws WeixinException {
		Token token = tokenManager.getCache();
		String oauth_logininfo_uri = getRequestUri("oauth_logininfo_uri");
		WeixinResponse response = weixinExecutor.post(
				String.format(oauth_logininfo_uri, token.getAccessToken()),
				String.format("{\"auth_code\":\"%s\"}", authCode));
		return JSON.parseObject(response.getAsString(), OUserInfo.class);
	}

	/**
	 * 获�?�部门�?员
	 * 
	 * @param partyId
	 *            部门ID
	 * @param fetchChild
	 *            是�?�递归获�?��?部门下�?�的�?员
	 * @param userStatus
	 *            �?员状�? status�?��?�加 未填写则默认为未关注(4)
	 * @param findDetail
	 *            是�?�获�?�详细信�?�
	 * @see com.foxinmy.weixin4j.qy.model.User
	 * @see <a href= "https://work.weixin.qq.com/api/doc#10061"> 获�?�部门�?员说明</a>
	 * @return �?员列表
	 * @throws WeixinException
	 */
	public List<User> listUser(int partyId, boolean fetchChild,
			UserStatus userStatus, boolean findDetail) throws WeixinException {
		String user_list_uri = findDetail ? getRequestUri("user_list_uri")
				: getRequestUri("user_slist_uri");
		Token token = tokenManager.getCache();
		if (userStatus == null) {
			userStatus = UserStatus.UNFOLLOW;
		}
		WeixinResponse response = weixinExecutor.get(String.format(
				user_list_uri, token.getAccessToken(), partyId, fetchChild ? 1
						: 0, userStatus.getVal()));
		List<User> list = null;
		if (findDetail) {
			JSONArray arrays = response.getAsJson().getJSONArray("userlist");
			list = new ArrayList<User>(arrays.size());
			for (int i = 0; i < arrays.size(); i++) {
				JSONObject obj = arrays.getJSONObject(i);
				Object attrs = obj.remove("extattr");
				User user = JSON.toJavaObject(obj, User.class);
				if (attrs != null) {
					user.setExtattr(JSON.parseArray(
							((JSONObject) attrs).getString("attrs"),
							NameValue.class));
				}
				list.add(user);
			}
		} else {
			list = JSON.parseArray(response.getAsJson().getString("userlist"),
					User.class);
		}
		return list;
	}

	/**
	 * 获�?�部门下所有状�?�?员(�?进行递归)
	 * 
	 * @param partyId
	 *            部门ID
	 * @see {@link #listUser(int, boolean, UserStatus,boolean)}
	 * @return �?员列表
	 * @throws WeixinException
	 */
	public List<User> listUser(int partyId) throws WeixinException {
		return listUser(partyId, false, UserStatus.BOTH, false);
	}

	/**
	 * 获�?��?��?范围内的所有�?员列表
	 * 
	 * @param userStatus
	 *            �?员状�? 未填写则默认为全部状�?下的�?员
	 * @return �?员列表
	 * @see {@link #listUser(int, boolean, UserStatus,boolean)}
	 * @see {@link PartyApi#listParty(int)}
	 * @throws WeixinException
	 */
	public List<User> listAllUser(UserStatus userStatus) throws WeixinException {
		List<User> users = null;
		List<Party> parties = partyApi.listParty(0);
		if (!parties.isEmpty()) {
			if (userStatus == null) {
				userStatus = UserStatus.BOTH;
			}
			users = new ArrayList<User>();
			for (Party party : parties) {
				users.addAll(listUser(party.getId(), true, userStatus, true));
			}
		}
		return users;
	}

	/**
	 * 删除�?员
	 * 
	 * @param userid
	 *            �?员ID
	 * @see <a href= "https://work.weixin.qq.com/api/doc#10030"> 删除�?员说明</a>
	 * @return 处�?�结果
	 * @throws WeixinException
	 */
	public ApiResult deleteUser(String userid) throws WeixinException {
		String user_delete_uri = getRequestUri("user_delete_uri");
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_delete_uri, token.getAccessToken(), userid));
		return response.getAsResult();
	}

	/**
	 * 批�?删除�?员
	 * 
	 * @param userIds
	 *            �?员列表
	 * @see <a href= "https://work.weixin.qq.com/api/doc#10060" >批�?删除�?员说明</a>
	 * @return 处�?�结果
	 * @throws WeixinException
	 */
	public ApiResult batchDeleteUser(List<String> userIds)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("useridlist", userIds);
		String user_delete_uri = getRequestUri("user_batchdelete_uri");
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.post(
				String.format(user_delete_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsResult();
	}

	/**
	 * 开�?�二次验�?�?功时调用(管�?�员须拥有userid对应员工的管�?��?��?)
	 * 
	 * @param userid
	 *            �?员ID
	 * @return 调用结果
	 * @see <a href= "https://work.weixin.qq.com/api/doc#11378"> 二次验�?说明</a>
	 * @throws WeixinException
	 */
	public ApiResult authsucc(String userId) throws WeixinException {
		String user_authsucc_uri = getRequestUri("user_authsucc_uri");
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_authsucc_uri, token.getAccessToken(), userId));
		return response.getAsResult();
	}

	/**
	 * 邀请�?员关注(管�?�员须拥有该�?员的查看�?��?)
	 * 
	 * @param userId
	 *            �?员ID
	 * @param tips
	 *            推�?到微信上的�??示语（�?�有认�?�?��?�以使用）。当使用微信推�?时，该字段默认为“请关注XXX�?业�?��?，邮件邀请时，该字段无效。
	 * @return 邀请类型
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E9.82.80.E8.AF.B7.E6.88.90.E5.91.98.E5.85.B3.E6.B3.A8">
	 *      邀请�?员关注说明</a>
	 * @throws WeixinException
	 */
	public InviteType inviteUser(String userId, String tips)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("userid", userId);
		obj.put("invite_tips", tips);
		String invite_user_uri = getRequestUri("invite_user_uri");
		Token token = tokenManager.getCache();
		WeixinResponse response = weixinExecutor.post(
				String.format(invite_user_uri, token.getAccessToken()),
				obj.toJSONString());
		int type = response.getAsJson().getIntValue("type");
		if (type == 1) {
			return InviteType.WEIXIN;
		} else if (type == 2) {
			return InviteType.EMAIL;
		} else {
			return null;
		}
	}

	/**
	 * userid转�?��?openid:该接�?�使用场景为微信支付�?微信红包和�?业转账，�?业�?�用户在使用微信支付的功能时，
	 * 需�?自行将�?业�?�的userid转�?openid。 在使用微信红包功能时，需�?将应用id和userid转�?appid和openid�?能使用。
	 * 
	 * @param userid
	 *            �?业�?�内的�?员id 必填
	 * @param agentid
	 *            需�?�?��?红包的应用ID，若�?�是使用微信支付和�?业转账，则无需该�?�数 传入0或负数则忽略
	 * @return 结果数组 第一个元素为对应的openid 第二个元素则为应用的appid(如果有)
	 * @throws WeixinException
	 * @see <a href= "https://work.weixin.qq.com/api/doc#11279">
	 *      userid与openid互�?�</a>
	 */
	public String[] userid2openid(String userid, int agentid)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("userid", userid);
		if (agentid > 0) {
			obj.put("agentid", agentid);
		}
		String userid2openid_uri = getRequestUri("userid2openid_uri");
		WeixinResponse response = weixinExecutor
				.post(String.format(userid2openid_uri,
						tokenManager.getAccessToken()), obj.toJSONString());
		obj = response.getAsJson();
		return new String[] { obj.getString("openid"), obj.getString("appid") };
	}

	/**
	 * openid转�?��?userid:该接�?�主�?应用于使用微信支付�?微信红包和�?业转账之�?�的结果查询，
	 * 开�?�者需�?知�?��?个结果事件的openid对应�?业�?�内�?员的信�?�时，�?�以通过调用该接�?�进行转�?�查询。
	 * 
	 * @param openid
	 *            在使用微信支付�?微信红包和�?业转账之�?�，返回结果的openid
	 * @return 该openid在�?业�?�中对应的�?员userid
	 * @throws WeixinException
	 * @see <a href= "https://work.weixin.qq.com/api/doc#11279">
	 *      userid与openid互�?�</a>
	 */
	public String openid2userid(String openid) throws WeixinException {
		String openid2userid_uri = getRequestUri("openid2userid_uri");
		WeixinResponse response = weixinExecutor
				.post(String.format(openid2userid_uri,
						tokenManager.getAccessToken()),
						String.format("{\"openid\": \"%s\"}", openid));
		return response.getAsJson().getString("userid");
	}
}
