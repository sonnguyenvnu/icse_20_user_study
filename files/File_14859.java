/*Copyright Â©2016 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo.client.activity_fragment;

import static zuo.biao.apijson.JSONResponse.CODE_TIME_OUT;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.ui.TextClearSuit;
import zuo.biao.library.util.EditTextUtil;
import zuo.biao.library.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import apijson.demo.client.R;
import apijson.demo.client.application.APIJSONApplication;
import apijson.demo.client.model.User;
import apijson.demo.client.model.Verify;
import apijson.demo.client.util.HttpRequest;
import apijson.demo.server.model.Privacy;

/**æ³¨å†Œã€?éªŒè¯?ç ?ç™»å½•ã€?é‡?ç½®å¯†ç ?ç­‰å¯†ç ?ç›¸å…³ç•Œé?¢
 * @author Lemon
 */
public class PasswordActivity extends BaseActivity implements OnClickListener, OnHttpResponseListener, OnBottomDragListener {
	public static final String TAG = "PasswordActivity";

	//å?¯åŠ¨æ–¹æ³•<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	
	/**å?¯åŠ¨è¿™ä¸ªActivityçš„Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context, int type) {
		return createIntent(context, type, null, null);
	}
	/**å?¯åŠ¨è¿™ä¸ªActivityçš„Intent
	 * @param context
	 * @param phone
	 * @return
	 */
	public static Intent createIntent(Context context, int type, String phone) {
		return createIntent(context, type, phone, null);
	}
	/**å?¯åŠ¨è¿™ä¸ªActivityçš„Intent
	 * @param context
	 * @param phone
	 * @param password
	 * @return
	 */
	public static Intent createIntent(Context context, int type, String phone, String password) {
		return new Intent(context, PasswordActivity.class)
		.putExtra(INTENT_TYPE, type)
		.putExtra(INTENT_PHONE, phone)
		.putExtra(INTENT_PASSWORD, password);
	}

	//å?¯åŠ¨æ–¹æ³•>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public Activity getActivity() {
		return this;
	}

	public static final int TYPE_VERIFY = 0;
	public static final int TYPE_REGISTER = 1;
	public static final int TYPE_RESET = 2;

	private int type = TYPE_VERIFY;
	private String phone;
	private String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_activity, this);

		type = getIntent().getIntExtra(INTENT_TYPE, type);
		phone = getIntent().getStringExtra(INTENT_PHONE);
		password = getIntent().getStringExtra(INTENT_PASSWORD);

		//å¿…é¡»è°ƒç”¨<<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//å¿…é¡»è°ƒç”¨>>>>>>>>>>

	}


	//UIæ˜¾ç¤ºåŒº(æ“?ä½œUIï¼Œä½†ä¸?å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä¹Ÿä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private EditText etPasswordPhone;
	private View pbPasswordGettingVerify;
	private TextView btnPasswordGetVerify;

	private View llPasswordPassword;
	private EditText etPasswordVerify;
	private EditText etPasswordPassword0;
	private EditText etPasswordPassword1;

	@Override
	public void initView() {//å¿…é¡»è°ƒç”¨

		etPasswordPhone = (EditText) findViewById(R.id.etPasswordPhone);
		pbPasswordGettingVerify = findViewById(R.id.pbPasswordGettingVerify);
		btnPasswordGetVerify = (TextView) findViewById(R.id.btnPasswordGetVerify);

		llPasswordPassword = findViewById(R.id.llPasswordPassword);
		etPasswordVerify = (EditText) findViewById(R.id.etPasswordVerify);
		etPasswordPassword0 = (EditText) findViewById(R.id.etPasswordPassword0);
		etPasswordPassword1 = (EditText) findViewById(R.id.etPasswordPassword1);

	}


	/**
	 * @param isGetting
	 */
	private void showVerifyGet(final boolean isGetting) {
		pbPasswordGettingVerify.setVisibility(isGetting ? View.VISIBLE : View.GONE);
		btnPasswordGetVerify.setText(isGetting ? "èŽ·å?–ä¸­..." : "é‡?æ–°èŽ·å?–");
	}

	//UIæ˜¾ç¤ºåŒº(æ“?ä½œUIï¼Œä½†ä¸?å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä¹Ÿä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Dataæ•°æ?®åŒº(å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä½†ä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//å¿…é¡»è°ƒç”¨
		switch (type) {
		case TYPE_REGISTER:
			tvBaseTitle.setText("æ³¨å†Œ");
			break;
		case TYPE_RESET:
			tvBaseTitle.setText("é‡?ç½®å¯†ç ?");
			break;
		default:
			tvBaseTitle.setText("éªŒè¯?æ‰‹æœº");
			break;
		}
		autoSetTitle();

		llPasswordPassword.setVisibility(type == TYPE_VERIFY ? View.GONE : View.VISIBLE);


		if (StringUtil.isNotEmpty(password, true)) {
			etPasswordPassword0.setText(StringUtil.getString(password));
		}
		if (StringUtil.isPhone(phone)) {
			etPasswordPhone.setText("" + phone);
			getVerify();
		}

	}

	private TimeCount time;
	/**èŽ·å?–éªŒè¯?ç ?
	 */
	private void getVerify() {
		if (EditTextUtil.isInputedCorrect(context, etPasswordPhone, EditTextUtil.TYPE_PHONE) == false) {
			return;
		}

		if (type == TYPE_VERIFY) {
			etPasswordVerify.requestFocus();
		} else {
			if (StringUtil.isNotEmpty(etPasswordPassword0, true)) {
				etPasswordPassword1.requestFocus();
			} else {
				etPasswordPassword0.requestFocus();
			}
		}

		showVerifyGet(true);
		time = new TimeCount(60000, 1000);

		//åˆ¤æ–­æ‰‹æœºå?·æ˜¯å?¦å·²ç»?æ³¨å†Œ
		HttpRequest.checkRegister(StringUtil.getTrimedString(etPasswordPhone), HTTP_CHECK_REGISTER, this);
	}

	/**ä»Žæœ?åŠ¡å™¨èŽ·å?–éªŒè¯?ç ?
	 */
	private void getVerifyFromServer(int type) {
		runUiThread(new Runnable() {

			@Override
			public void run() {
				showVerifyGet(true);
			}
		});

		HttpRequest.getVerify(type, StringUtil.getTrimedString(etPasswordPhone), HTTP_GET_VERIFY, this);
	}

	/**ä¸‹ä¸€æ­¥
	 */
	private void toNextStep() {
		if (type != TYPE_VERIFY) {
			if (EditTextUtil.isInputedCorrect(context, etPasswordPassword0, EditTextUtil.TYPE_PASSWORD) == false
					|| EditTextUtil.isInputedCorrect(context, etPasswordPassword1, EditTextUtil.TYPE_PASSWORD) == false) {
				return;
			}

			final String password0 = StringUtil.getTrimedString(etPasswordPassword0);
			String password1 = StringUtil.getTrimedString(etPasswordPassword1);
			if (! password0.equals(password1)) {
				showShortToast("å¯†ç ?è¾“å…¥ä¸?ä¸€è‡´ï¼Œè¯·é‡?æ–°è¾“å…¥");
				return;
			}
		}

		switch (type) {
		case TYPE_REGISTER:
			register();
			break;
		case TYPE_VERIFY:
			checkVerify(Verify.TYPE_LOGIN, true);
			break;
		case TYPE_RESET:
			checkVerify(Verify.TYPE_PASSWORD, true);
			break;
		}
	}

	/**éªŒè¯?éªŒè¯?ç ?
	 * @param type
	 * @param fromServer
	 */
	private boolean checkVerify(int type, boolean fromServer) {
		if (EditTextUtil.isInputedCorrect(context, etPasswordPhone, EditTextUtil.TYPE_PHONE) == false
				|| EditTextUtil.isInputedCorrect(context, etPasswordVerify, EditTextUtil.TYPE_VERIFY) == false) {
			return false;
		}

		if (fromServer) {
			showProgressDialog();
			HttpRequest.checkVerify(type, StringUtil.getTrimedString(etPasswordPhone),
					StringUtil.getTrimedString(etPasswordVerify), HTTP_CHECK_VERIFY, this);
		}

		return true;
	}


	private void register() {
		if (checkVerify(Verify.TYPE_REGISTER, false) == false) {
			return;
		}
		showProgressDialog();
		HttpRequest.register(StringUtil.getTrimedString(etPasswordVerify)
				, StringUtil.getTrimedString(etPasswordPhone)
				, StringUtil.getString(etPasswordPassword0),
				"APIJSONUser", 0, HTTP_REGISTER, this); // æ³¨å†ŒæŽ¥å?£
	}

	private void setPassword() {
		showProgressDialog();
		HttpRequest.setPassword(StringUtil.getTrimedString(etPasswordVerify)
				, StringUtil.getTrimedString(etPasswordPhone)
				, StringUtil.getString(etPasswordPassword0),
				HTTP_RESET_PASSWORD, this); // æ³¨å†ŒæŽ¥å?£
	}


	private Intent newResult() {
		return new Intent()
		.putExtra(RESULT_PHONE, StringUtil.getTrimedString(etPasswordPhone))
		.putExtra(RESULT_VERIFY, StringUtil.getTrimedString(etPasswordVerify))
		.putExtra(RESULT_PASSWORD, StringUtil.getTrimedString(etPasswordPassword0));
	}

	private void saveAndExit(Intent intent) {
		setResult(RESULT_OK, intent);
		finish();
	}

	//Dataæ•°æ?®åŒº(å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä½†ä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Eventäº‹ä»¶åŒº(å?ªè¦?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?å°±æ˜¯)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//å¿…é¡»è°ƒç”¨

		findViewById(R.id.btnPasswordGetVerify).setOnClickListener(this);

		findViewById(R.id.btnPasswordNext).setOnClickListener(this);


		new TextClearSuit().addClearListener(etPasswordPhone, findViewById(R.id.ivPasswordPhoneClear));
		new TextClearSuit().addClearListener(etPasswordVerify, findViewById(R.id.ivPasswordVerifyClear));
		new TextClearSuit().addClearListener(etPasswordPassword0, findViewById(R.id.ivPasswordPassword0Clear));
		new TextClearSuit().addClearListener(etPasswordPassword1, findViewById(R.id.ivPasswordPassword1Clear));

	}


	public static final int HTTP_CHECK_REGISTER = 1;
	public static final int HTTP_CHECK_VERIFY = 2;
	public static final int HTTP_GET_VERIFY = 3;
	public static final int HTTP_REGISTER = 4;
	public static final int HTTP_RESET_PASSWORD = 5;

	@Override
	public void onHttpResponse(int requestCode, String resultJson, Exception e) {
		final JSONResponse response = new JSONResponse(resultJson);
		final JSONResponse response2;

		dismissProgressDialog();
		switch (requestCode) {
		case HTTP_CHECK_REGISTER:
			response2 = response.getJSONResponse(HttpRequest.PRIVACY_);
			Log.i(TAG, "checkPassword result = " + resultJson);
			runUiThread(new Runnable() {
				@Override
				public void run() {
					showVerifyGet(false);
					if (JSONResponse.isSuccess(response2) == false) {
						showShortToast(R.string.get_failed);
					} else if (JSONResponse.isExist(response2)) {
						if (type == TYPE_REGISTER) {
							showShortToast("æ‰‹æœºå?·å·²ç»?æ³¨å†Œ");
						} else {
							getVerifyFromServer(type == TYPE_VERIFY ? Verify.TYPE_LOGIN : Verify.TYPE_PASSWORD);
						}
					} else {//æ‰‹æœºå?·æœªè¢«æ³¨å†Œè¿‡
						if (type == TYPE_REGISTER) {
							getVerifyFromServer(Verify.TYPE_REGISTER);
						} else {
							showShortToast("æ‰‹æœºå?·æœªæ³¨å†Œ");
						}
					}
				}
			});
			break;
		case HTTP_CHECK_VERIFY:
			response2 = response.getJSONResponse(Verify.class.getSimpleName());
			runUiThread(new Runnable() {
				@Override
				public void run() {
					if (JSONResponse.isExist(response2)) {	//éªŒè¯?éªŒè¯?ç ?æˆ?åŠŸ
						switch (type) {
						case TYPE_REGISTER:
							register();
							break;
						case TYPE_RESET:
							setPassword();
							break;
						default:
							saveAndExit(newResult());
							break;
						}
					} else {//éªŒè¯?ç ?é”™è¯¯
						EditTextUtil.showInputedError(context, etPasswordVerify
								, response.getCode() == CODE_TIME_OUT ? "éªŒè¯?ç ?å·²è¿‡æœŸ" : "éªŒè¯?ç ?é”™è¯¯");
					}
				}
			});
			break;
		case HTTP_GET_VERIFY:
			final Verify verify = response.getObject(Verify.class);
			runUiThread(new Runnable() {
				@Override
				public void run() {
					showVerifyGet(false);
					if (verify == null || StringUtil.isNotEmpty(verify.getVerify(), true) == false) {
						showShortToast(R.string.get_failed);
					} else {
						Log.i(TAG, "å?‘é€?æˆ?åŠŸ");
						time.start();
						Toast.makeText(context, "éªŒè¯?ç ?æ˜¯\n" + verify.getVerify(), Toast.LENGTH_LONG).show();
					}
				}
			});
			break;
		case HTTP_REGISTER:
			User user = response.getObject(User.class);
			dismissProgressDialog();
			if (user == null || user.getId() <= 0 || JSONResponse.isSuccess(
					response.getJSONResponse(User.class.getSimpleName())) == false) {
				if (response.getCode() == CODE_TIME_OUT || response.getCode() == 412) {
					EditTextUtil.showInputedError(context, etPasswordVerify
							, response.getCode() == CODE_TIME_OUT ? "éªŒè¯?ç ?å·²è¿‡æœŸ" : "éªŒè¯?ç ?é”™è¯¯");
				} else {
					showShortToast("æ³¨å†Œå¤±è´¥ï¼Œè¯·æ£€æŸ¥ç½‘ç»œå?Žé‡?è¯•");
				}
			} else {
				showShortToast("æ³¨å†Œæˆ?åŠŸ");
				APIJSONApplication.getInstance().saveCurrentUser(user);

				saveAndExit(newResult().putExtra(INTENT_ID, user.getId()));
			}
			break;
		case HTTP_RESET_PASSWORD:
			response2 = response.getJSONResponse(Privacy.class.getSimpleName());
			dismissProgressDialog();
			if (JSONResponse.isSuccess(response2) == false) {
				EditTextUtil.showInputedError(context, etPasswordVerify
						, response.getCode() == CODE_TIME_OUT ? "éªŒè¯?ç ?å·²è¿‡æœŸ" : "éªŒè¯?ç ?é”™è¯¯");
			} else {
				showShortToast("ä¿®æ”¹å¯†ç ?æˆ?åŠŸï¼Œè¯·é‡?æ–°ç™»å½•");

				saveAndExit(newResult().putExtra(INTENT_PHONE, phone));
			}
			break;
		default:
			break;
		}
	}

	//ç³»ç»Ÿè‡ªå¸¦ç›‘å?¬æ–¹æ³•<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {

			return;
		}

		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPasswordGetVerify:
			getVerify();
			break;
		case R.id.btnPasswordNext:
			toNextStep();
			break;
		default:
			break;
		}
	}


	//ç±»ç›¸å…³ç›‘å?¬<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//ç±»ç›¸å…³ç›‘å?¬>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//ç³»ç»Ÿè‡ªå¸¦ç›‘å?¬æ–¹æ³•>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//Eventäº‹ä»¶åŒº(å?ªè¦?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?å°±æ˜¯)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//å†…éƒ¨ç±»,å°½é‡?å°‘ç”¨<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**å€’è®¡æ—¶ç±»
	 */
	private class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);//å?‚æ•°ä¾?æ¬¡ä¸ºæ€»æ—¶é•¿,å’Œè®¡æ—¶çš„æ—¶é—´é—´éš”
		}
		@Override
		public void onFinish() {//è®¡æ—¶å®Œæ¯•æ—¶è§¦å?‘
			btnPasswordGetVerify.setText("é‡?æ–°èŽ·å?–");
			btnPasswordGetVerify.setClickable(true);
		}
		@Override
		public void onTick(long millisUntilFinished){//è®¡æ—¶è¿‡ç¨‹æ˜¾ç¤º
			btnPasswordGetVerify.setClickable(false);
			btnPasswordGetVerify.setText((millisUntilFinished / 1000) +"ç§’");
		}
	}


	//å†…éƒ¨ç±»,å°½é‡?å°‘ç”¨>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
