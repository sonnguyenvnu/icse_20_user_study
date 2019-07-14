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

import zuo.biao.apijson.JSONResponse;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.ServerSettingActivity;
import zuo.biao.library.ui.TextClearSuit;
import zuo.biao.library.util.EditTextUtil;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.SettingUtil;
import zuo.biao.library.util.StringUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import apijson.demo.client.R;
import apijson.demo.client.application.APIJSONApplication;
import apijson.demo.client.manager.DataManager;
import apijson.demo.client.model.User;
import apijson.demo.client.util.HttpRequest;
import apijson.demo.server.model.Login;

/**ç™»å½•ç•Œé?¢
 * @author Lemon
 */
public class LoginActivity extends BaseActivity implements OnClickListener, OnTouchListener, OnBottomDragListener{
	private static final String TAG = "LoginActivity";


	//å?¯åŠ¨æ–¹æ³•<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**å?¯åŠ¨è¿™ä¸ªActivityçš„Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, LoginActivity.class);
	}

	//å?¯åŠ¨æ–¹æ³•>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	@Override
	public Activity getActivity() {
		return this;
	}

	public static final int RESULT_LOGIN = 41;
	public static final String RESULT_LOGINED = "RESULT_LOGINED";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity, this);

		//å¿…é¡»è°ƒç”¨<<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//å¿…é¡»è°ƒç”¨>>>>>>>>>>

	}


	//UIæ˜¾ç¤ºåŒº(æ“?ä½œUIï¼Œä½†ä¸?å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä¹Ÿä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private EditText etLoginPhone;
	private EditText etLoginPassword;
	@Override
	public void initView() {//å¿…é¡»è°ƒç”¨
		exitAnim = R.anim.bottom_push_out;//é€€å‡ºåŠ¨ç”»

		etLoginPhone = (EditText) findViewById(R.id.etLoginPhone);
		etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);

	}

	private void onLoginSucceed() {
		runUiThread(new Runnable() {

			@Override
			public void run() {
				intent = MainTabActivity.createIntent(context);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				toActivity(intent);
				enterAnim = exitAnim = R.anim.null_anim;
				finish();	
			}
		});
	}

	private void toPassword(int type, int requestCode) {
		toActivity(PasswordActivity.createIntent(context, type, StringUtil.getTrimedString(etLoginPhone)
				, StringUtil.getString(etLoginPassword)), requestCode);
	}

	private void showForget() {
		toActivity(BottomMenuWindow.createIntent(context, new String[]{"é‡?ç½®å¯†ç ?", "éªŒè¯?ç ?ç™»å½•"})
				.putExtra(INTENT_TITLE, "å¿˜è®°å¯†ç ?")
				, REQUEST_TO_BOTTOM_MUNU, false);		
	}

	//UIæ˜¾ç¤ºåŒº(æ“?ä½œUIï¼Œä½†ä¸?å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä¹Ÿä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//dataæ•°æ?®åŒº(å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä½†ä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private String phone;
	private String password;

	@Override
	public void initData() {//å¿…é¡»è°ƒç”¨

		phone = DataManager.getInstance().getLastUserPhone();
		if(StringUtil.isPhone(phone)) {
			etLoginPhone.setText("" + phone);
			etLoginPassword.requestFocus();
		}

	}


	private void login(int type) {
		if (EditTextUtil.isInputedCorrect(context, etLoginPhone, EditTextUtil.TYPE_PHONE) == false
				|| EditTextUtil.isInputedCorrect(context, etLoginPassword, type == Login.TYPE_VERIFY 
				? EditTextUtil.TYPE_VERIFY : EditTextUtil.TYPE_PASSWORD) == false) {
			return;
		}
		EditTextUtil.hideKeyboard(context, etLoginPassword);

		showProgressDialog("æ­£åœ¨ç™»å½•ï¼Œè¯·ç¨?å?Ž...");


		phone = StringUtil.getTrimedString(etLoginPhone);
		password = StringUtil.getString(etLoginPassword);

		//ç™»å½•è¯·æ±‚
		HttpRequest.login(phone, password, type, type, new OnHttpResponseListener() {
			@Override
			public void onHttpResponse(int requestCode, String resultJson, Exception e) {
				dismissProgressDialog();
				JSONResponse response = new JSONResponse(resultJson);
				User user = response.getObject(User.class);

				if (response.isSuccess() == false) {
					switch (response.getCode()) {
					case JSONResponse.CODE_NOT_FOUND:
						showShortToast("è´¦å?·ä¸?å­˜åœ¨ï¼Œè¯·å…ˆæ³¨å†Œ");
						onDragBottom(true);
						break;
					case JSONResponse.CODE_ILLEGAL_ARGUMENT:
						showShortToast("è´¦å?·æˆ–å¯†ç ?ä¸?å?ˆæ³•ï¼?");
						break;
					case JSONResponse.CODE_CONDITION_ERROR:
						showShortToast("è´¦å?·æˆ–å¯†ç ?é”™è¯¯ï¼?");
						showForget();
						break;
					default:
						showShortToast(R.string.login_faild);
						break;
					}
				} else {
					user.setPhone(phone);
					APIJSONApplication.getInstance().saveCurrentUser(user);
					if (APIJSONApplication.getInstance().isLoggedIn() == false) {
						showShortToast((requestCode == Login.TYPE_PASSWORD ? "å¯†ç ?" : "éªŒè¯?ç ?") + "é”™è¯¯");
						return;
					}

					onLoginSucceed();
				}
			}
		});

	}


	//Dataæ•°æ?®åŒº(å­˜åœ¨æ•°æ?®èŽ·å?–æˆ–å¤„ç?†ä»£ç ?ï¼Œä½†ä¸?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>









	//Eventäº‹ä»¶åŒº(å?ªè¦?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?å°±æ˜¯)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//å¿…é¡»è°ƒç”¨

		tvBaseTitle.setOnTouchListener(this);

		findViewById(R.id.tvLoginForget).setOnClickListener(this);
		findViewById(R.id.tvLoginLogin).setOnClickListener(this);

		new TextClearSuit().addClearListener(etLoginPhone, findViewById(R.id.ivLoginPhoneClear));
		new TextClearSuit().addClearListener(etLoginPassword, findViewById(R.id.ivLoginPasswordClear));
	}


	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {

			toPassword(PasswordActivity.TYPE_REGISTER, REQUEST_TO_REGISTER);
			return;
		}

		finish();
	}

	//ç³»ç»Ÿè‡ªå¸¦ç›‘å?¬æ–¹æ³•<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvLoginForget:
			showForget();
			break;
		case R.id.tvLoginLogin:
			login(Login.TYPE_PASSWORD);
			break;
		default:
			break;
		}
	}


	private long touchDownTime = 0;
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (v.getId() == R.id.tvBaseTitle) {
				touchDownTime = System.currentTimeMillis();
				Log.i(TAG, "onTouch MotionEvent.ACTION: touchDownTime=" + touchDownTime);
				return true;
			}
		case MotionEvent.ACTION_UP:
			if (v.getId() == R.id.tvBaseTitle) {
				long time = System.currentTimeMillis() - touchDownTime;
				if (time > 5000 && time < 8000) {
					toActivity(ServerSettingActivity.createIntent(context
							, SettingUtil.getServerAddress(false), SettingUtil.getServerAddress(true)
							, SettingUtil.APP_SETTING, Context.MODE_PRIVATE
							, SettingUtil.KEY_SERVER_ADDRESS_NORMAL, SettingUtil.KEY_SERVER_ADDRESS_TEST)
							, REQUEST_TO_SERVER_SETTING);
					return true;
				}
			}
			break;
		default:
			break;
		}

		return false;
	}



	//ç±»ç›¸å…³ç›‘å?¬<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void finish() {
		if (APIJSONApplication.getInstance().isLoggedIn() == false) {
			showShortToast("æœªç™»å½•ï¼Œæœ‰äº›å†…å®¹ä¼šåŠ è½½ä¸?å‡ºæ?¥~");
		}

		setResult(RESULT_OK, new Intent().putExtra(RESULT_LOGINED, APIJSONApplication.getInstance().isLoggedIn()));
		super.finish();
	}


	public static final int REQUEST_TO_BOTTOM_MUNU = 1;
	public static final int REQUEST_TO_SERVER_SETTING = 2;
	public static final int REQUEST_TO_REGISTER = 3;
	public static final int REQUEST_TO_VERIFY = 4;
	public static final int REQUEST_TO_RESET = 5;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_SERVER_SETTING:
			sendBroadcast(new Intent(ACTION_EXIT_APP));
			break;
		case REQUEST_TO_BOTTOM_MUNU:
			if (data != null) {
				switch (data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1)) {
				case 0:
					toPassword(PasswordActivity.TYPE_RESET, REQUEST_TO_RESET);
					break;
				case 1:
					toPassword(PasswordActivity.TYPE_VERIFY, REQUEST_TO_VERIFY);
					break;
				default:
					break;
				}
			}
			break;
		case REQUEST_TO_RESET:
		case REQUEST_TO_VERIFY:
		case REQUEST_TO_REGISTER:
			if (data != null) {
				String phone = data.getStringExtra(PasswordActivity.RESULT_PHONE);
				String password = data.getStringExtra(requestCode == REQUEST_TO_VERIFY
						? PasswordActivity.RESULT_VERIFY : PasswordActivity.RESULT_PASSWORD);
				if (StringUtil.isPhone(phone)) {
					etLoginPhone.setText(phone);
				}
				if (StringUtil.isNotEmpty(password, true)) {
					etLoginPassword.setText(password);
				}

				login(requestCode == REQUEST_TO_VERIFY ? Login.TYPE_VERIFY : Login.TYPE_PASSWORD);
			}
			break;
		default:
			break;
		}
	}

	//ç±»ç›¸å…³ç›‘å?¬>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//ç³»ç»Ÿè‡ªå¸¦ç›‘å?¬æ–¹æ³•>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Eventäº‹ä»¶åŒº(å?ªè¦?å­˜åœ¨äº‹ä»¶ç›‘å?¬ä»£ç ?å°±æ˜¯)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//å†…éƒ¨ç±»,å°½é‡?å°‘ç”¨<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


}
