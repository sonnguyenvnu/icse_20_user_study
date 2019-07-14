/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon)

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
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.ui.EditTextManager;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import apijson.demo.client.R;
import apijson.demo.client.util.HttpRequest;
import apijson.demo.server.model.Privacy;

/**支付密�?设置界�?�activity
 * @author Lemon
 * @use toActivity(PasswordSettingActivity.createIntent(...));
 */
public class NumberPasswordActivity extends BaseActivity implements OnClickListener, OnHttpResponseListener {
	private static final String TAG = "NumberPasswordActivity";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String INTENT_IS_TO_SETTING = "INTENT_IS_TO_SETTING";
	public static final String INTENT_USER_ID = "INTENT_USER_ID";
	public static final String INTENT_PASSWORD = "INTENT_PASSWORD";
	public static final String INTENT_REMIND = "INTENT_REMIND";

	public static final String RESULT_PASSWORD = "RESULT_PASSWORD";

	public static final String RESULT_IS_CHANGE_PASSWORD = "RESULT_IS_CHANGE_PASSWORD";

	/**�?�动这个Activity的Intent
	 * 返回输入结果,isToSetting = false;
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return createIntent(context, false);
	}

	/**�?�动这个Activity的Intent
	 * @param context
	 * @param isToSetting true-设置密�?；false-返回输入结果
	 * @return
	 */
	public static Intent createIntent(Context context, boolean isToSetting) {
		return createIntent(context, null).putExtra(INTENT_IS_TO_SETTING, isToSetting);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param password
	 * @return
	 */
	public static Intent createIntent(Context context, String password) {
		return createIntent(context, null, null, null);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param password
	 * @param title
	 * @return
	 */
	public static Intent createIntent(Context context, String password, String remind, String title) {
		return new Intent(context, NumberPasswordActivity.class).
				putExtra(INTENT_PASSWORD, password).
				putExtra(INTENT_REMIND, remind).
				putExtra(INTENT_TITLE, title);
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public Activity getActivity() {
		return this;
	}


	private boolean isToSetting;
	private boolean isToConfirm;
	private String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.number_password_activity);

		password = StringUtil.getNumber(getIntent().getStringExtra(INTENT_PASSWORD));

		isToConfirm = StringUtil.getLength(password, true) == 6;
		isToSetting = isToConfirm || getIntent().getBooleanExtra(INTENT_IS_TO_SETTING, false);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		toInput();
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final int PASSWORD_LENGTH = 6;

	private TextView tvPasswordSettingTitle;

	private TextView tvPasswordSettingRemind;
	private EditText etPasswordSetting;
	private TextView[] tvPasswordSettingNumbers;

	@Override
	public void initView() {//必须调用

		exitAnim = R.anim.bottom_push_out;

		tvPasswordSettingTitle = (TextView) findViewById(R.id.tvPasswordSettingTitle);


		//pay<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

		tvPasswordSettingRemind = (TextView) findViewById(R.id.tvPasswordSettingPayRemind);

		etPasswordSetting = (EditText) findViewById(R.id.etPasswordSettingPay);

		tvPasswordSettingNumbers = new TextView[PASSWORD_LENGTH];
		tvPasswordSettingNumbers[0]  = (TextView) findViewById(R.id.tvPasswordSettingPayNumber0);
		tvPasswordSettingNumbers[1]  = (TextView) findViewById(R.id.tvPasswordSettingPayNumber1);
		tvPasswordSettingNumbers[2]  = (TextView) findViewById(R.id.tvPasswordSettingPayNumber2);
		tvPasswordSettingNumbers[3]  = (TextView) findViewById(R.id.tvPasswordSettingPayNumber3);
		tvPasswordSettingNumbers[4]  = (TextView) findViewById(R.id.tvPasswordSettingPayNumber4);
		tvPasswordSettingNumbers[5]  = (TextView) findViewById(R.id.tvPasswordSettingPayNumber5);
		//pay>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	}

	private void toInput() {
		toInput(null, null);
	}
	private String phone;
	private String verify;
	/**
	 * @param verify
	 */
	private void toInput(String phone, String verify) {
		this.phone = phone;
		this.verify = verify;
		if (isToSetting == false || isToConfirm == true) {
			return;
		}

		Log.i(TAG, "toInput phone = " + phone
				+ "\n verify = " + verify);
		if (StringUtil.isNotEmpty(phone, true) == false || StringUtil.isNotEmpty(verify, true) == false) {
			toActivity(PasswordActivity.createIntent(context, PasswordActivity.TYPE_VERIFY)
					.putExtra(INTENT_TITLE, "验�?手机"), REQUEST_TO_VERIFY_PHONE);
			return;
		}

		EditTextManager.showKeyboard(context, etPasswordSetting);
	}

	private void next() {
		if (isToSetting == false || isToConfirm) {
			saveAndExit();
		} else {
			startActivityForResult(NumberPasswordActivity.createIntent(
					context, inputedString, "请�?次输入以确认", ""), REQUEST_TO_PASSWORD_SETTING);
			overridePendingTransition(R.anim.bottom_push_in, R.anim.hold);
		}
	}

	private void saveAndExit() {
		saveAndExit(false);
	}
	private void saveAndExit(boolean isChangePassword) {
		if (isToConfirm && inputedString.equals(password) == false) {
			showShortToast(R.string.password_not_equal);
			return;
		}

		setResult(RESULT_OK, new Intent().
				putExtra(RESULT_PASSWORD, inputedString).
				putExtra(RESULT_IS_CHANGE_PASSWORD, isChangePassword));
		finish();
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void initData() {//必须调用

		if (StringUtil.isNotEmpty(getIntent().getStringExtra(INTENT_TITLE), false)) {
			tvPasswordSettingTitle.setText("" + StringUtil.getCurrentString());
		}
		if (StringUtil.isNotEmpty(getIntent().getStringExtra(INTENT_REMIND), false)) {
			tvPasswordSettingRemind.setText("" + StringUtil.getCurrentString());
		}

		tvPasswordSettingTitle.setText(isToSetting ? "设置支付密�?" : "输入支付密�?");
		if (isToSetting) {

		} else {
			if (isToConfirm == false) {
				password = null;
			}
		}

	}


	//data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//listener事件监�?�区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private String inputedString = "";
	private int inputedLength;
	@Override
	public void initEvent() {//必须调用

		findViewById(R.id.llPasswordSettingPayNumberContainer).setOnClickListener(this);

		for (int i = 0; i < tvPasswordSettingNumbers.length; i++) {
			tvPasswordSettingNumbers[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					EditTextManager.showKeyboard(context, etPasswordSetting, true);
				}
			});
		}

		etPasswordSetting.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				inputedString = StringUtil.getNoBlankString(s);
				inputedLength = inputedString.length();

				for (int i = 0; i < inputedLength; i++) {
					tvPasswordSettingNumbers[i].setText(inputedString.substring(i, i + 1));
				}
				for (int j = inputedString.length(); j < PASSWORD_LENGTH; j++) {
					tvPasswordSettingNumbers[j].setText("");
				}

				if (inputedLength >= PASSWORD_LENGTH) {
					next();
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			@Override
			public void afterTextChanged(Editable s) { }
		});
	}


	private static final int HTTP_SET_PASSWORD = 1;

	@Override
	public void onHttpResponse(int requestCode, String resultJson, Exception e) {
		if (isAlive() == false) {
			return;
		}
		JSONResponse response = new JSONResponse(resultJson);
		
		dismissProgressDialog();
		switch (requestCode) {
		case HTTP_SET_PASSWORD:
			if (response.isSuccess()) {
				showShortToast(R.string.change_succeed);
				saveAndExit(true);
			} else {
				showShortToast(R.string.change_failed);
			}
			break;
		default:
			break;
		}
	}


	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.llPasswordSettingPayNumberContainer://设置�?�?应，解决抢�?�焦点
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_ENTER && inputedLength >= 6) {
			next();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final int REQUEST_TO_PASSWORD_SETTING = 10;
	private static final int REQUEST_TO_VERIFY_PHONE = 11;
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			enterAnim = exitAnim = R.anim.null_anim;
			finish();
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_VERIFY_PHONE:
			if (data != null) {
				toInput(data.getStringExtra(PasswordActivity.RESULT_PHONE)
						, data.getStringExtra(PasswordActivity.RESULT_VERIFY));
			}
			break;
		case REQUEST_TO_PASSWORD_SETTING:
			if (data != null) {
				password = data.getStringExtra(NumberPasswordActivity.RESULT_PASSWORD);
				if (StringUtil.isNotEmpty(password, true)) {
					HttpRequest.setPassword(Privacy.PASSWORD_TYPE_PAY, password, phone, verify
							, HTTP_SET_PASSWORD, this);
				}
			}
			break;
		default:
			break;
		}
	}



	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//listener事件监�?�区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
