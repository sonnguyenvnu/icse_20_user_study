package apijson.demo.server;

import static zuo.biao.apijson.RequestMethod.DELETE;
import static zuo.biao.apijson.RequestMethod.GET;
import static zuo.biao.apijson.RequestMethod.GETS;
import static zuo.biao.apijson.RequestMethod.HEAD;
import static zuo.biao.apijson.RequestMethod.HEADS;
import static zuo.biao.apijson.RequestMethod.POST;
import static zuo.biao.apijson.RequestMethod.PUT;

import com.jfinal.kit.HttpKit;

import apijson.demo.server.model.Privacy;
import apijson.demo.server.model.User;
import apijson.demo.server.model.Verify;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.apijson.RequestMethod;
import zuo.biao.apijson.server.JSONRequest;

public class Controller extends com.jfinal.core.Controller {

	//é€šç”¨æŽ¥å?£ï¼Œé?žäº‹åŠ¡åž‹æ“?ä½œ å’Œ ç®€å?•äº‹åŠ¡åž‹æ“?ä½œ éƒ½å?¯é€šè¿‡è¿™äº›æŽ¥å?£è‡ªåŠ¨åŒ–å®žçŽ°<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**èŽ·å?–
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#GET}
	 */
	public void get() {
		renderJson(new DemoParser(GET).setSession(getSession()).parse(HttpKit.readData(getRequest())));
	}

	/**è®¡æ•°
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#HEAD}
	 */
	public void head() {
		renderJson(new DemoParser(HEAD).setSession(getSession()).parse(HttpKit.readData(getRequest())));
	}

	/**é™?åˆ¶æ€§GETï¼Œrequestå’Œresponseéƒ½é?žæ˜Žæ–‡ï¼Œæµ?è§ˆå™¨çœ‹ä¸?åˆ°ï¼Œç”¨äºŽå¯¹å®‰å…¨æ€§è¦?æ±‚é«˜çš„GETè¯·æ±‚
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#GETS}
	 */
	public void gets() {
		renderJson(new DemoParser(GETS).setSession(getSession()).parse(HttpKit.readData(getRequest())));
	}

	/**é™?åˆ¶æ€§HEADï¼Œrequestå’Œresponseéƒ½é?žæ˜Žæ–‡ï¼Œæµ?è§ˆå™¨çœ‹ä¸?åˆ°ï¼Œç”¨äºŽå¯¹å®‰å…¨æ€§è¦?æ±‚é«˜çš„HEADè¯·æ±‚
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#HEADS}
	 */
	public void heads() {
		renderJson(new DemoParser(HEADS).setSession(getSession()).parse(HttpKit.readData(getRequest())));
	}

	/**æ–°å¢ž
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#POST}
	 */
	public void post() {
		renderJson(new DemoParser(POST).setSession(getSession()).parse(HttpKit.readData(getRequest())));
	}

	/**ä¿®æ”¹
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#PUT}
	 */
	public void put() {
		renderJson(new DemoParser(PUT).setSession(getSession()).parse(HttpKit.readData(getRequest())));
	}

	/**åˆ é™¤
	 * @param request å?ªç”¨Stringï¼Œé?¿å…?encodeå?Žæœªdecode
	 * @param session
	 * @return
	 * @see {@link RequestMethod#DELETE}
	 */
	public void delete() {
		renderJson(new DemoParser(DELETE).setSession(getSession()).parse(HttpKit.readData(getRequest())));
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

}
