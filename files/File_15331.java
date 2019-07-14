/*Copyright Â©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo.server;

import static zuo.biao.apijson.RequestMethod.DELETE;
import static zuo.biao.apijson.RequestMethod.GET;
import static zuo.biao.apijson.RequestMethod.GETS;
import static zuo.biao.apijson.RequestMethod.HEAD;
import static zuo.biao.apijson.RequestMethod.HEADS;
import static zuo.biao.apijson.RequestMethod.POST;
import static zuo.biao.apijson.RequestMethod.PUT;

import java.net.URLDecoder;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import apijson.demo.server.model.BaseModel;
import apijson.demo.server.model.Privacy;
import apijson.demo.server.model.User;
import apijson.demo.server.model.Verify;
import zuo.biao.apijson.JSON;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.apijson.Log;
import zuo.biao.apijson.RequestMethod;
import zuo.biao.apijson.StringUtil;
import zuo.biao.apijson.server.JSONRequest;
import zuo.biao.apijson.server.exception.ConditionErrorException;
import zuo.biao.apijson.server.exception.ConflictException;
import zuo.biao.apijson.server.exception.NotExistException;
import zuo.biao.apijson.server.exception.OutOfRangeException;


/**request controller
 * <br > å»ºè®®å…¨é€šè¿‡HTTP POSTæ?¥è¯·æ±‚:
 * <br > 1.å‡?å°‘ä»£ç ? - å®¢æˆ·ç«¯æ— éœ€å†™HTTP GET,PUTç­‰å?„ç§?æ–¹å¼?çš„è¯·æ±‚ä»£ç ?
 * <br > 2.æ??é«˜æ€§èƒ½ - æ— éœ€URL encodeå’Œdecode
 * <br > 3.è°ƒè¯•æ–¹ä¾¿ - å»ºè®®ä½¿ç”¨ APIJSONåœ¨çº¿æµ‹è¯•å·¥å…· æˆ– Postman
 * @author Lemon
 */
@RestController
@RequestMapping("")
public class Controller {
	private static final String TAG = "Controller";

	//é€šç”¨æŽ¥å?£ï¼Œé?žäº‹åŠ¡åž‹æ“?ä½œ å’Œ ç®€å?•äº‹åŠ¡åž‹æ“?ä½œ éƒ½å?¯é€šè¿‡è¿™äº›æŽ¥å?£è‡ªåŠ¨åŒ–å®žçŽ°<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**èŽ·å?–
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#GET}
	 */
	@PostMapping(value = "get")
	public String get(@RequestBody String request, HttpSession session) {
		return new DemoParser(GET).setSession(session).parse(request);
	}

	/**è®¡æ•°
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#HEAD}
	 */
	@PostMapping("head")
	public String head(@RequestBody String request, HttpSession session) {
		return new DemoParser(HEAD).setSession(session).parse(request);
	}

	/**é™?åˆ¶æ€§GETï¼Œrequestå’Œresponseéƒ½é?žæ˜Žæ–‡ï¼Œæµ?è§ˆå™¨çœ‹ä¸?åˆ°ï¼Œç”¨äºŽå¯¹å®‰å…¨æ€§è¦?æ±‚é«˜çš„GETè¯·æ±‚
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#GETS}
	 */
	@PostMapping("gets")
	public String gets(@RequestBody String request, HttpSession session) {
		return new DemoParser(GETS).setSession(session).parse(request);
	}

	/**é™?åˆ¶æ€§HEADï¼Œrequestå’Œresponseéƒ½é?žæ˜Žæ–‡ï¼Œæµ?è§ˆå™¨çœ‹ä¸?åˆ°ï¼Œç”¨äºŽå¯¹å®‰å…¨æ€§è¦?æ±‚é«˜çš„HEADè¯·æ±‚
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#HEADS}
	 */
	@PostMapping("heads")
	public String heads(@RequestBody String request, HttpSession session) {
		return new DemoParser(HEADS).setSession(session).parse(request);
	}

	/**æ–°å¢ž
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#POST}
	 */
	@PostMapping("post")
	public String post(@RequestBody String request, HttpSession session) {
		return new DemoParser(POST).setSession(session).parse(request);
	}

	/**ä¿®æ”¹
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#PUT}
	 */
	@PostMapping("put")
	public String put(@RequestBody String request, HttpSession session) {
		return new DemoParser(PUT).setSession(session).parse(request);
	}

	/**åˆ é™¤
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#DELETE}
	 */
	@PostMapping("delete")
	public String delete(@RequestBody String request, HttpSession session) {
		return new DemoParser(DELETE).setSession(session).parse(request);
	}





	/**èŽ·å?–
	 * å?ªä¸ºå…¼å®¹HTTP GETè¯·æ±‚ï¼ŒæŽ¨è??ç”¨HTTP POSTï¼Œå?¯åˆ é™¤
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#GET}
	 */
	@RequestMapping("get/{request}")
	public String openGet(@PathVariable String request, HttpSession session) {
		try {
			request = URLDecoder.decode(request, StringUtil.UTF_8);
		} catch (Exception e) {
			// Parserä¼šæŠ¥é”™
		}
		return get(request, session);
	}

	/**è®¡æ•°
	 * å?ªä¸ºå…¼å®¹HTTP GETè¯·æ±‚ï¼ŒæŽ¨è??ç”¨HTTP POSTï¼Œå?¯åˆ é™¤
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#HEAD}
	 */
	@RequestMapping("head/{request}")
	public String openHead(@PathVariable String request, HttpSession session) {
		try {
			request = URLDecoder.decode(request, StringUtil.UTF_8);
		} catch (Exception e) {
			// Parserä¼šæŠ¥é”™
		}
		return head(request, session);
	}


	//é€šç”¨æŽ¥å?£ï¼Œé?žäº‹åŠ¡åž‹æ“?ä½œ å’Œ ç®€å?•äº‹åŠ¡åž‹æ“?ä½œ éƒ½å?¯é€šè¿‡è¿™äº›æŽ¥å?£è‡ªåŠ¨åŒ–å®žçŽ°>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>













	public static final String USER_;
	public static final String PRIVACY_;
	public static final String VERIFY_; //åŠ ä¸‹åˆ’çº¿å?Žç¼€æ˜¯ä¸ºäº†é?¿å…? Verify å’Œ verify éƒ½å?«VERIFYï¼Œåˆ†ä¸?æ¸…
	static {
		USER_ = User.class.getSimpleName();
		PRIVACY_ = Privacy.class.getSimpleName();
		VERIFY_ = Verify.class.getSimpleName();
	}

	public static final String VERSION = JSONRequest.KEY_VERSION;
	public static final String FORMAT = JSONRequest.KEY_FORMAT;
	public static final String COUNT = JSONResponse.KEY_COUNT;
	public static final String TOTAL = JSONResponse.KEY_TOTAL;

	public static final String ID = "id";
	public static final String USER_ID = "userId";
	public static final String CURRENT_USER_ID = "currentUserId";

	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String PASSWORD = "password";
	public static final String _PASSWORD = "_password";
	public static final String _PAY_PASSWORD = "_payPassword";
	public static final String OLD_PASSWORD = "oldPassword";
	public static final String VERIFY = "verify";

	public static final String TYPE = "type";



	/**ç”Ÿæˆ?éªŒè¯?ç ?,ä¿®æ”¹ä¸ºpostè¯·æ±‚
	 * @param request
	 * @return
	 */
	@PostMapping("post/verify")
	public JSONObject postVerify(@RequestBody String request) {
		JSONObject requestObject = null;
		int type;
		String phone;
		try {
			requestObject = DemoParser.parseRequest(request);
			type = requestObject.getIntValue(TYPE);
			phone = requestObject.getString(PHONE);
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}

		new DemoParser(DELETE, true).parse(newVerifyRequest(type, phone, null));

		JSONObject response = new DemoParser(POST, true).parseResponse(
				newVerifyRequest(type, phone, "" + (new Random().nextInt(9999) + 1000))
				);

		JSONObject verify = null;
		try {
			verify = response.getJSONObject(StringUtil.firstCase(VERIFY_));
		} catch (Exception e) {}

		if (verify == null || JSONResponse.isSuccess(verify.getIntValue(JSONResponse.KEY_CODE)) == false) {
			new DemoParser(DELETE, true).parseResponse(new JSONRequest(new Verify(type, phone)));
			return response;
		}

		//TODO è¿™é‡Œç›´æŽ¥è¿”å›žéªŒè¯?ç ?ï¼Œæ–¹ä¾¿æµ‹è¯•ã€‚å®žé™…ä¸Šåº”è¯¥å?ªè¿”å›žæˆ?åŠŸä¿¡æ?¯ï¼ŒéªŒè¯?ç ?é€šè¿‡çŸ­ä¿¡å?‘é€?
		JSONObject object = new JSONObject();
		object.put(TYPE, type);
		object.put(PHONE, phone);
		return getVerify(JSON.toJSONString(object));
	}

	/**èŽ·å?–éªŒè¯?ç ?
	 * @param request
	 * @return
	 */
	@PostMapping("gets/verify")
	public JSONObject getVerify(@RequestBody String request) {
		JSONObject requestObject = null;
		int type;
		String phone;
		try {
			requestObject = DemoParser.parseRequest(request);
			type = requestObject.getIntValue(TYPE);
			phone = requestObject.getString(PHONE);
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}
		return new DemoParser(GETS, true).parseResponse(newVerifyRequest(type, phone, null));
	}

	/**æ ¡éªŒéªŒè¯?ç ?
	 * @param request
	 * @return
	 */
	@PostMapping("heads/verify")
	public JSONObject headVerify(@RequestBody String request) {
		JSONObject requestObject = null;
		int type;
		String phone;
		String verify;
		try {
			requestObject = DemoParser.parseRequest(request);
			type = requestObject.getIntValue(TYPE);
			phone = requestObject.getString(PHONE);
			verify = requestObject.getString(VERIFY);
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}
		return headVerify(type, phone, verify);
	}

	/**æ ¡éªŒéªŒè¯?ç ?
	 * @author Lemon
	 * @param type
	 * @param phone
	 * @param code
	 * @return
	 */
	public JSONObject headVerify(int type, String phone, String code) {
		JSONResponse response = new JSONResponse(
				new DemoParser(GETS, true).parseResponse(
						new JSONRequest(
								new Verify(type, phone)
								.setVerify(code)
								).setTag(VERIFY_)
						)
				);
		Verify verify = response.getObject(Verify.class);
		if (verify == null) {
			return DemoParser.newErrorResult(StringUtil.isEmpty(code, true)
					? new NotExistException("éªŒè¯?ç ?ä¸?å­˜åœ¨ï¼?") : new ConditionErrorException("æ‰‹æœºå?·æˆ–éªŒè¯?ç ?é”™è¯¯ï¼?"));
		}

		//éªŒè¯?ç ?è¿‡æœŸ
		long time = BaseModel.getTimeMillis(verify.getDate());
		long now = System.currentTimeMillis();
		if (now > 60*1000 + time) {
			new DemoParser(DELETE, true).parseResponse(
					new JSONRequest(new Verify(type, phone)).setTag(VERIFY_)
					);
			return DemoParser.newErrorResult(new TimeoutException("éªŒè¯?ç ?å·²è¿‡æœŸï¼?"));
		}

		return new JSONResponse(
				new DemoParser(HEADS, true).parseResponse(
						new JSONRequest(new Verify(type, phone).setVerify(code)).setFormat(true)
						)
				);
	}



	/**æ–°å»ºä¸€ä¸ªéªŒè¯?ç ?è¯·æ±‚
	 * @param phone
	 * @param verify
	 * @return
	 */
	private zuo.biao.apijson.JSONRequest newVerifyRequest(int type, String phone, String verify) {
		return new JSONRequest(new Verify(type, phone).setVerify(verify)).setTag(VERIFY_).setFormat(true);
	}


	public static final String LOGIN = "login";
	public static final String REMEMBER = "remember";
	public static final String DEFAULTS = "defaults";

	public static final int LOGIN_TYPE_PASSWORD = 0;//å¯†ç ?ç™»å½•
	public static final int LOGIN_TYPE_VERIFY = 1;//éªŒè¯?ç ?ç™»å½•
	/**ç”¨æˆ·ç™»å½•
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @return
	 * @see
	 * <pre>
		{
			"type": 0,  //ç™»å½•æ–¹å¼?ï¼Œé?žå¿…é¡»  0-å¯†ç ? 1-éªŒè¯?ç ?
			"phone": "13000082001",
			"password": "1234567",
			"version": 1 //å…¨å±€ç‰ˆæœ¬å?·ï¼Œé?žå¿…é¡»
		}
	 * </pre>
	 */
	@PostMapping(LOGIN)
	public JSONObject login(@RequestBody String request, HttpSession session) {
		JSONObject requestObject = null;
		boolean isPassword;
		String phone;
		String password;
		int version;
		Boolean format;
		boolean remember;
		JSONObject defaults;
		try {
			requestObject = DemoParser.parseRequest(request);

			isPassword = requestObject.getIntValue(TYPE) == LOGIN_TYPE_PASSWORD;//ç™»å½•æ–¹å¼?
			phone = requestObject.getString(PHONE);//æ‰‹æœº
			password = requestObject.getString(PASSWORD);//å¯†ç ?

			if (StringUtil.isPhone(phone) == false) {
				throw new IllegalArgumentException("æ‰‹æœºå?·ä¸?å?ˆæ³•ï¼?");
			}

			if (isPassword) {
				if (StringUtil.isPassword(password) == false) {
					throw new IllegalArgumentException("å¯†ç ?ä¸?å?ˆæ³•ï¼?");
				}
			} else {
				if (StringUtil.isVerify(password) == false) {
					throw new IllegalArgumentException("éªŒè¯?ç ?ä¸?å?ˆæ³•ï¼?");
				}
			}

			version = requestObject.getIntValue(VERSION);
			format = requestObject.getBoolean(FORMAT);
			remember = requestObject.getBooleanValue(REMEMBER);
			defaults = requestObject.getJSONObject(DEFAULTS); //é»˜è®¤åŠ åˆ°æ¯?ä¸ªè¯·æ±‚æœ€å¤–å±‚çš„å­—æ®µ
			requestObject.remove(VERSION);
			requestObject.remove(FORMAT);
			requestObject.remove(REMEMBER);
			requestObject.remove(DEFAULTS);
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}



		//æ‰‹æœºå?·æ˜¯å?¦å·²æ³¨å†Œ
		JSONObject phoneResponse = new DemoParser(HEADS, true).parseResponse(
				new JSONRequest(
						new Privacy().setPhone(phone)
						)
				);
		if (JSONResponse.isSuccess(phoneResponse) == false) {
			return DemoParser.newResult(phoneResponse.getIntValue(JSONResponse.KEY_CODE), phoneResponse.getString(JSONResponse.KEY_MSG));
		}
		JSONResponse response = new JSONResponse(phoneResponse).getJSONResponse(PRIVACY_);
		if(JSONResponse.isExist(response) == false) {
			return DemoParser.newErrorResult(new NotExistException("æ‰‹æœºå?·æœªæ³¨å†Œ"));
		}

		//æ ¹æ?®phoneèŽ·å?–User
		JSONObject privacyResponse = new DemoParser(GETS, true).parseResponse(
				new JSONRequest(
						new Privacy().setPhone(phone)
						).setFormat(true)
				);
		response = new JSONResponse(privacyResponse);

		Privacy privacy = response == null ? null : response.getObject(Privacy.class);
		long userId = privacy == null ? 0 : BaseModel.value(privacy.getId());
		if (userId <= 0) {
			return privacyResponse;
		}

		//æ ¡éªŒå‡­è¯? 
		if (isPassword) {//passwordå¯†ç ?ç™»å½•
			response = new JSONResponse(
					new DemoParser(HEADS, true).parseResponse(
							new JSONRequest(new Privacy(userId).setPassword(password))
							)
					);
		} else {//verifyæ‰‹æœºéªŒè¯?ç ?ç™»å½•
			response = new JSONResponse(headVerify(Verify.TYPE_LOGIN, phone, password));
		}
		if (JSONResponse.isSuccess(response) == false) {
			return response;
		}
		response = response.getJSONResponse(isPassword ? PRIVACY_ : VERIFY_);
		if (JSONResponse.isExist(response) == false) {
			return DemoParser.newErrorResult(new ConditionErrorException("è´¦å?·æˆ–å¯†ç ?é”™è¯¯"));
		}

		response = new JSONResponse(
				new DemoParser(GETS, true).parseResponse(
						new JSONRequest(new User(userId)).setFormat(true)
						)
				);
		User user = response.getObject(User.class);
		if (user == null || BaseModel.value(user.getId()) != userId) {
			return DemoParser.newErrorResult(new NullPointerException("æœ?åŠ¡å™¨å†…éƒ¨é”™è¯¯"));
		}

		//ç™»å½•çŠ¶æ€?ä¿?å­˜è‡³session
		session.setAttribute(USER_ID, userId); //ç”¨æˆ·id
		session.setAttribute(TYPE, isPassword ? LOGIN_TYPE_PASSWORD : LOGIN_TYPE_VERIFY); //ç™»å½•æ–¹å¼?
		session.setAttribute(USER_, user); //ç”¨æˆ·
		session.setAttribute(PRIVACY_, privacy); //ç”¨æˆ·éš?ç§?ä¿¡æ?¯
		session.setAttribute(VERSION, version); //å…¨å±€é»˜è®¤ç‰ˆæœ¬å?·
		session.setAttribute(FORMAT, format); //å…¨å±€é»˜è®¤æ ¼å¼?åŒ–é…?ç½®
		session.setAttribute(REMEMBER, remember); //æ˜¯å?¦è®°ä½?ç™»å½•
		session.setAttribute(DEFAULTS, defaults); //ç»™æ¯?ä¸ªè¯·æ±‚JSONæœ€å¤–å±‚åŠ çš„å­—æ®µ
		session.setMaxInactiveInterval(60*60*24*(remember ? 7 : 1)); //è®¾ç½®sessionè¿‡æœŸæ—¶é—´

		response.put(REMEMBER, remember);
		response.put(DEFAULTS, defaults);
		return response;
	}

	/**é€€å‡ºç™»å½•ï¼Œæ¸…ç©ºsession
	 * @param session
	 * @return
	 */
	@PostMapping("logout")
	public JSONObject logout(HttpSession session) {
		long userId;
		try {
			userId = DemoVerifier.getVisitorId(session);//å¿…é¡»åœ¨session.invalidate();å‰?ï¼?
			Log.d(TAG, "logout  userId = " + userId + "; session.getId() = " + (session == null ? null : session.getId()));
			session.invalidate();
		} catch (Exception e) {
			return DemoParser.newErrorResult(e);
		}

		JSONObject result = DemoParser.newSuccessResult();
		JSONObject user = DemoParser.newSuccessResult();
		user.put(ID, userId);
		user.put(COUNT, 1);
		result.put(StringUtil.firstCase(USER_), user);

		return result;
	}


	private static final String REGISTER = "register";
	/**æ³¨å†Œ
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @return
	 * @see
	 * <pre>
		{
			"Privacy": {
				"phone": "13000082222",
				"_password": "123456"
			},
			"User": {
				"name": "APIJSONUser"
			},
			"verify": "1234"
		}
	 * </pre>
	 */
	@PostMapping(REGISTER)
	public JSONObject register(@RequestBody String request) {
		JSONObject requestObject = null;

		JSONObject privacyObj;

		String phone;
		String verify;
		String password;
		try {
			requestObject = DemoParser.parseRequest(request);
			privacyObj = requestObject.getJSONObject(PRIVACY_);

			phone = StringUtil.getString(privacyObj.getString(PHONE));
			verify = requestObject.getString(VERIFY);
			password = privacyObj.getString(_PASSWORD);

			if (StringUtil.isPhone(phone) == false) {
				return newIllegalArgumentResult(requestObject, PRIVACY_ + "/" + PHONE);
			}
			if (StringUtil.isPassword(password) == false) {
				return newIllegalArgumentResult(requestObject, PRIVACY_ + "/" + _PASSWORD);
			}
			if (StringUtil.isVerify(verify) == false) {
				return newIllegalArgumentResult(requestObject, VERIFY);
			}
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}


		JSONResponse response = new JSONResponse(headVerify(Verify.TYPE_REGISTER, phone, verify));
		if (JSONResponse.isSuccess(response) == false) {
			return response;
		}
		//æ‰‹æœºå?·æˆ–éªŒè¯?ç ?é”™è¯¯
		if (JSONResponse.isExist(response.getJSONResponse(VERIFY_)) == false) {
			return DemoParser.extendErrorResult(response, new ConditionErrorException("æ‰‹æœºå?·æˆ–éªŒè¯?ç ?é”™è¯¯ï¼?"));
		}



		//ç”Ÿæˆ?Userå’ŒPrivacy
		if (StringUtil.isEmpty(requestObject.getString(JSONRequest.KEY_TAG), true)) {
			requestObject.put(JSONRequest.KEY_TAG, REGISTER);
		}
		requestObject.put(JSONRequest.KEY_FORMAT, true);
		response = new JSONResponse( 
				new DemoParser(POST).setNoVerifyLogin(true).parseResponse(requestObject)
				);

		//éªŒè¯?Userå’ŒPrivacy
		User user = response.getObject(User.class);
		long userId = user == null ? 0 : BaseModel.value(user.getId());
		Privacy privacy = response.getObject(Privacy.class);
		long userId2 = privacy == null ? 0 : BaseModel.value(privacy.getId());
		Exception e = null;
		if (userId <= 0 || userId != userId2) { //idä¸?å?Œ
			e = new Exception("æœ?åŠ¡å™¨å†…éƒ¨é”™è¯¯ï¼?å†™å…¥Useræˆ–Privacyå¤±è´¥ï¼?");
		}

		if (e != null) { //å‡ºçŽ°é”™è¯¯ï¼Œå›žé€€
			new DemoParser(DELETE, true).parseResponse(
					new JSONRequest(new User(userId))
					);
			new DemoParser(DELETE, true).parseResponse(
					new JSONRequest(new Privacy(userId2))
					);
		}

		return response;
	}


	/**
	 * @param requestObject
	 * @param key
	 * @return
	 */
	public static JSONObject newIllegalArgumentResult(JSONObject requestObject, String key) {
		return newIllegalArgumentResult(requestObject, key, null);
	}
	/**
	 * @param requestObject
	 * @param key
	 * @param msg è¯¦ç»†è¯´æ˜Ž
	 * @return
	 */
	public static JSONObject newIllegalArgumentResult(JSONObject requestObject, String key, String msg) {
		return DemoParser.extendErrorResult(requestObject
				, new IllegalArgumentException(key + ":value ä¸­valueä¸?å?ˆæ³•ï¼?" + StringUtil.getString(msg)));
	}


	/**è®¾ç½®å¯†ç ?
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @return
	 * @see
	 * <pre>
	    ä½¿ç”¨æ—§å¯†ç ?ä¿®æ”¹
		{
			"oldPassword": 123456,
			"Privacy":{
			  "id": 13000082001,
			  "_password": "1234567"
			}
		}
		æˆ–ä½¿ç”¨æ‰‹æœºå?·+éªŒè¯?ç ?ä¿®æ”¹
		{
			"verify": "1234",
			"Privacy":{
			  "phone": "13000082001",
			  "_password": "1234567"
			}
		}
	 * </pre>
	 */
	@PostMapping("put/password")
	public JSONObject putPassword(@RequestBody String request){
		JSONObject requestObject = null;
		String oldPassword;
		String verify;

		int type = Verify.TYPE_PASSWORD;

		JSONObject privacyObj;
		long userId;
		String phone;
		String password;
		try {
			requestObject = DemoParser.parseRequest(request);
			oldPassword = StringUtil.getString(requestObject.getString(OLD_PASSWORD));
			verify = StringUtil.getString(requestObject.getString(VERIFY));

			requestObject.remove(OLD_PASSWORD);
			requestObject.remove(VERIFY);

			privacyObj = requestObject.getJSONObject(PRIVACY_);
			if (privacyObj == null) {
				throw new IllegalArgumentException(PRIVACY_ + " ä¸?èƒ½ä¸ºç©ºï¼?");
			}
			userId = privacyObj.getLongValue(ID);
			phone = privacyObj.getString(PHONE);
			password = privacyObj.getString(_PASSWORD);

			if (StringUtil.isEmpty(password, true)) { //æ”¯ä»˜å¯†ç ?
				type = Verify.TYPE_PAY_PASSWORD;
				password = privacyObj.getString(_PAY_PASSWORD);
				if (StringUtil.isNumberPassword(password) == false) {
					throw new IllegalArgumentException(PRIVACY_ + "/" + _PAY_PASSWORD + ":value ä¸­valueä¸?å?ˆæ³•ï¼?");
				}
			} else { //ç™»å½•å¯†ç ?
				if (StringUtil.isPassword(password) == false) {
					throw new IllegalArgumentException(PRIVACY_ + "/" + _PASSWORD + ":value ä¸­valueä¸?å?ˆæ³•ï¼?");
				}
			}
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}


		if (StringUtil.isPassword(oldPassword)) {
			if (userId <= 0) { //æ‰‹æœºå?·+éªŒè¯?ç ?ä¸?éœ€è¦?userId
				return DemoParser.extendErrorResult(requestObject, new IllegalArgumentException(ID + ":value ä¸­valueä¸?å?ˆæ³•ï¼?"));
			}
			if (oldPassword.equals(password)) {
				return DemoParser.extendErrorResult(requestObject, new ConflictException("æ–°æ—§å¯†ç ?ä¸?èƒ½ä¸€æ ·ï¼?"));
			}

			//éªŒè¯?æ—§å¯†ç ?
			Privacy privacy = new Privacy(userId);
			if (type == Verify.TYPE_PASSWORD) {
				privacy.setPassword(oldPassword);
			} else {
				privacy.setPayPassword(oldPassword);
			}
			JSONResponse response = new JSONResponse( 
					new DemoParser(HEAD, true).parseResponse(
							new JSONRequest(privacy).setFormat(true)
							)
					);
			if (JSONResponse.isExist(response.getJSONResponse(PRIVACY_)) == false) {
				return DemoParser.extendErrorResult(requestObject, new ConditionErrorException("è´¦å?·æˆ–åŽŸå¯†ç ?é”™è¯¯ï¼Œè¯·é‡?æ–°è¾“å…¥ï¼?"));
			}
		}
		else if (StringUtil.isPhone(phone) && StringUtil.isVerify(verify)) {
			JSONResponse response = new JSONResponse(headVerify(type, phone, verify));
			if (JSONResponse.isSuccess(response) == false) {
				return response;
			}
			if (JSONResponse.isExist(response.getJSONResponse(VERIFY_)) == false) {
				return DemoParser.extendErrorResult(response, new ConditionErrorException("æ‰‹æœºå?·æˆ–éªŒè¯?ç ?é”™è¯¯ï¼?"));
			}
			response = new JSONResponse(
					new DemoParser(GET, true).parseResponse(
							new JSONRequest(
									new Privacy().setPhone(phone)
									)
							)
					);
			Privacy privacy = response.getObject(Privacy.class);
			long id = privacy == null ? 0 : BaseModel.value(privacy.getId());
			privacyObj.remove(PHONE);
			privacyObj.put(ID, id);

			requestObject.put(PRIVACY_, privacyObj);
		} else {
			return DemoParser.extendErrorResult(requestObject, new IllegalArgumentException("è¯·è¾“å…¥å?ˆæ³•çš„ æ—§å¯†ç ? æˆ– æ‰‹æœºå?·+éªŒè¯?ç ? ï¼?"));
		}
		//TODO ä¸Šçº¿ç‰ˆåŠ ä¸Š   password = MD5Util.MD5(password);


		//		requestObject.put(JSONRequest.KEY_TAG, "Password");
		requestObject.put(JSONRequest.KEY_FORMAT, true);
		//ä¿®æ”¹å¯†ç ?
		return new DemoParser(PUT, true).parseResponse(requestObject);
	}



	/**å……å€¼/æ??çŽ°
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see
	 * <pre>
		{
			"Privacy": {
				"id": 82001,
				"balance+": 100,
				"_payPassword": "123456"
			}
		}
	 * </pre>
	 */
	@PostMapping("put/balance")
	public JSONObject putBalance(@RequestBody String request, HttpSession session) {
		JSONObject requestObject = null;
		JSONObject privacyObj;
		long userId;
		String payPassword;
		double change;
		try {
			DemoVerifier.verifyLogin(session);
			requestObject = new DemoParser(PUT).setRequest(DemoParser.parseRequest(request)).parseCorrectRequest();

			privacyObj = requestObject.getJSONObject(PRIVACY_);
			if (privacyObj == null) {
				throw new NullPointerException("è¯·è®¾ç½® " + PRIVACY_ + "ï¼?");
			}
			userId = privacyObj.getLongValue(ID);
			payPassword = privacyObj.getString(_PAY_PASSWORD);
			change = privacyObj.getDoubleValue("balance+");

			if (userId <= 0) {
				throw new IllegalArgumentException(PRIVACY_ + "." + ID + ":value ä¸­valueä¸?å?ˆæ³•ï¼?");
			}
			if (StringUtil.isPassword(payPassword) == false) {
				throw new IllegalArgumentException(PRIVACY_ + "." + _PAY_PASSWORD + ":value ä¸­valueä¸?å?ˆæ³•ï¼?");
			}
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}

		//éªŒè¯?å¯†ç ?<<<<<<<<<<<<<<<<<<<<<<<

		privacyObj.remove("balance+");
		JSONResponse response = new JSONResponse(
				new DemoParser(HEADS, true).setSession(session).parseResponse(
						new JSONRequest(PRIVACY_, privacyObj)
						)
				);
		response = response.getJSONResponse(PRIVACY_);
		if (JSONResponse.isExist(response) == false) {
			return DemoParser.extendErrorResult(requestObject, new ConditionErrorException("æ”¯ä»˜å¯†ç ?é”™è¯¯ï¼?"));
		}

		//éªŒè¯?å¯†ç ?>>>>>>>>>>>>>>>>>>>>>>>>


		//éªŒè¯?é‡‘é¢?èŒƒå›´<<<<<<<<<<<<<<<<<<<<<<<

		if (change == 0) {
			return DemoParser.extendErrorResult(requestObject, new OutOfRangeException("balance+çš„å€¼ä¸?èƒ½ä¸º0ï¼?"));
		}
		if (Math.abs(change) > 10000) {
			return DemoParser.extendErrorResult(requestObject, new OutOfRangeException("å?•æ¬¡ å……å€¼/æ??çŽ° çš„é‡‘é¢?ä¸?èƒ½è¶…è¿‡10000å…ƒï¼?"));
		}

		//éªŒè¯?é‡‘é¢?èŒƒå›´>>>>>>>>>>>>>>>>>>>>>>>>

		if (change < 0) {//æ??çŽ°
			response = new JSONResponse(
					new DemoParser(GETS, true).parseResponse(
							new JSONRequest(
									new Privacy(userId)
									)
							)
					);
			Privacy privacy = response == null ? null : response.getObject(Privacy.class);
			long id = privacy == null ? 0 : BaseModel.value(privacy.getId());
			if (id != userId) {
				return DemoParser.extendErrorResult(requestObject, new Exception("æœ?åŠ¡å™¨å†…éƒ¨é”™è¯¯ï¼?"));
			}

			if (BaseModel.value(privacy.getBalance()) < -change) {
				return DemoParser.extendErrorResult(requestObject, new OutOfRangeException("ä½™é¢?ä¸?è¶³ï¼?"));
			}
		}


		privacyObj.remove(_PAY_PASSWORD);
		privacyObj.put("balance+", change);
		requestObject.put(PRIVACY_, privacyObj);
		requestObject.put(JSONRequest.KEY_TAG, PRIVACY_);
		requestObject.put(JSONRequest.KEY_FORMAT, true);
		//ä¸?å…?éªŒè¯?ï¼Œé‡Œé?¢ä¼šéªŒè¯?èº«ä»½
		return new DemoParser(PUT).setSession(session).parseResponse(requestObject);
	}


}
