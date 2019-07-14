/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.library.ui;

import zuo.biao.library.R;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.util.DataKeeper;
import zuo.biao.library.util.SettingUtil;
import zuo.biao.library.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**�?务器设置activity
 * @author Lemon
 * @use toActivity(ServerSettingActivity.createIntent(...));
 */
public class ServerSettingActivity extends BaseActivity implements OnClickListener, OnBottomDragListener {
	//	private static final String TAG = "ServerSettingActivity";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String INTENT_NORMAL_ADDRESS = "INTENT_NORMAL_ADDRESS";
	public static final String INTENT_TEST_ADDRESS = "INTENT_TEST_ADDRESS";
	public static final String INTENT_SHARED_PREFERENCES_PATH = "INTENT_SHARED_PREFERENCES_PATH";
	public static final String INTENT_PATH_MODE = "INTENT_PATH_MODE";
	public static final String INTENT_NORMAL_KEY = "INTENT_NORMAL_KEY";
	public static final String INTENT_TEST_KEY = "INTENT_TEST_KEY";

	public static final String RESULT_NORMAL_ADDRESS = "RESULT_NORMAL_ADDRESS";
	public static final String RESULT_TEST_ADDRESS = "RESULT_TEST_ADDRESS";

	/**�?�动这个Activity的Intent
	 * 通过setResult返回结果,而�?是直接在这个界�?��?存设置
	 * @param context
	 * @param normalAddress
	 * @param testAddress
	 * @return
	 */
	public static Intent createIntent(Context context, String normalAddress, String testAddress) {
		return createIntent(context, normalAddress, testAddress, null, 0, null, null);
	}
	/**�?�动这个Activity的Intent
	 * �?�有一个�?务器
	 * @param context
	 * @param address
	 * @param sharedPreferencesPath
	 * @param pathMode
	 * @param key
	 * @return
	 */
	public static Intent createIntent(Context context, String address, String sharedPreferencesPath, int pathMode, String key) {
		return createIntent(context, address, null, sharedPreferencesPath, pathMode, key, null);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param normalAddress
	 * @param testAddress
	 * @param sharedPreferencesPath
	 * @param pathMode
	 * @param normalKey
	 * @param testKey
	 * @return
	 */
	public static Intent createIntent(Context context, String normalAddress, String testAddress
			, String sharedPreferencesPath, int pathMode, String normalKey, String testKey) {
		return new Intent(context, ServerSettingActivity.class).
				putExtra(INTENT_NORMAL_ADDRESS, normalAddress).
				putExtra(INTENT_TEST_ADDRESS, testAddress).
				putExtra(INTENT_SHARED_PREFERENCES_PATH, sharedPreferencesPath).
				putExtra(INTENT_PATH_MODE, pathMode).
				putExtra(INTENT_NORMAL_KEY, normalKey).
				putExtra(INTENT_TEST_KEY, testKey);
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public Activity getActivity() {
		return this;
	}

	private String normalAddress;
	private String testAddress;
	private String sharedPreferencesPath;
	private int pathMode = Context.MODE_PRIVATE;
	private String normalKey;
	private String testKey;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server_setting_activity, this);

		intent = getIntent();
		normalAddress = intent.getStringExtra(INTENT_NORMAL_ADDRESS);
		testAddress = intent.getStringExtra(INTENT_TEST_ADDRESS);
		sharedPreferencesPath = intent.getStringExtra(INTENT_SHARED_PREFERENCES_PATH);
		pathMode = intent.getIntExtra(INTENT_PATH_MODE, pathMode);
		normalKey = intent.getStringExtra(INTENT_NORMAL_KEY);
		testKey = intent.getStringExtra(INTENT_TEST_KEY);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private TextView tvServerSettingNormalName;
	private TextView tvServerSettingTestName;

	private EditText etServerSettingNormal;
	private EditText etServerSettingTest;
	@Override
	public void initView() {//必须调用
		
		tvServerSettingNormalName = (TextView) findViewById(R.id.tvServerSettingNormalName);
		tvServerSettingTestName = (TextView) findViewById(R.id.tvServerSettingTestName);

		etServerSettingNormal = (EditText) findViewById(R.id.etServerSettingNormal);
		etServerSettingTest = (EditText) findViewById(R.id.etServerSettingTest);

	}



	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final String[] SERVER_NAMES = {
			"正�?�?务器", "测试�?务器"
	};

	@Override
	public void initData() {//必须调用
		
		//获�?�并显网�?�
		etServerSettingNormal.setText(StringUtil.getNoBlankString(normalAddress));
		etServerSettingTest.setText(StringUtil.getNoBlankString(testAddress));

		tvServerSettingNormalName.setText(SERVER_NAMES[0]
				+ (SettingUtil.isOnTestMode == false ? "[正在使用]" : ""));
		tvServerSettingTestName.setText(SERVER_NAMES[1]
				+ (SettingUtil.isOnTestMode ? "[正在使用]" : ""));
	}


	/**�?存并退出
	 * @param isTest
	 */
	private void saveAndExit(boolean isTest) {
		if (StringUtil.isNotEmpty(sharedPreferencesPath, true)
				&& StringUtil.isNotEmpty(isTest ? testKey : normalKey, true)) {
			SettingUtil.putBoolean(SettingUtil.KEY_IS_ON_TEST_MODE, isTest);
			DataKeeper.save(sharedPreferencesPath, pathMode, isTest ? testKey : normalKey
					, StringUtil.getNoBlankString(isTest ? etServerSettingTest : etServerSettingNormal));
			showShortToast("已�?存并切�?�至" + SERVER_NAMES[SettingUtil.isOnTestMode ? 1 : 0]
					+ "，请�?�?退出登录。�?�?��?�生效");
		}
		setResult(RESULT_OK, new Intent().putExtra(isTest ? RESULT_TEST_ADDRESS : RESULT_NORMAL_ADDRESS
				, StringUtil.getNoBlankString(isTest ? etServerSettingTest : etServerSettingNormal)));
		finish();
	}

	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用
		
		findViewById(R.id.tvServerSettingNormalSet).setOnClickListener(this);
		findViewById(R.id.tvServerSettingNormalOpen).setOnClickListener(this);

		findViewById(R.id.tvServerSettingTestSet).setOnClickListener(this);
		findViewById(R.id.tvServerSettingTestOpen).setOnClickListener(this);

	}

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			etServerSettingNormal.setText(StringUtil.getTrimedString(SettingUtil.URL_SERVER_ADDRESS_NORMAL_HTTP));
			etServerSettingTest.setText(StringUtil.getTrimedString(SettingUtil.URL_SERVER_ADDRESS_TEST));
			return;
		}	
		
		finish();
	}

	
	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.getCurrentServerAddress:
//				finish();
//				break;
//			case R.id.tvServerSettingForward:
//				etServerSettingNormal.setText(StringUtil.getTrimedString(normalAddress));
//				etServerSettingTest.setText(StringUtil.getTrimedString(testAddress));
//				break;
//
//			case R.id.tvServerSettingNormalSet:
//				saveAndExit(false);
//				break;
//			case R.id.tvServerSettingTestSet:
//				saveAndExit(true);
//				break;
//
//			case R.id.tvServerSettingNormalOpen:
//				toActivity(WebViewActivity.createIntent(context, "正�?�?务器", StringUtil.getNoBlankString(etServerSettingNormal)));
//				break;
//			case R.id.tvServerSettingTestOpen:
//				toActivity(WebViewActivity.createIntent(context, "测试�?务器", StringUtil.getNoBlankString(etServerSettingTest)));
//				break;
//			default:
//				break;
//		}
//	}
	//Library内switch方法中case R.id.idx会报错
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tvServerSettingNormalSet) {
			saveAndExit(false);
		} else if (v.getId() == R.id.tvServerSettingTestSet) {
			saveAndExit(true);
		} else if (v.getId() == R.id.tvServerSettingNormalOpen) {
			toActivity(WebViewActivity.createIntent(context
					, "正�?�?务器", StringUtil.getString(etServerSettingNormal)));
		} else if (v.getId() == R.id.tvServerSettingTestOpen) {
			toActivity(WebViewActivity.createIntent(context
					, "测试�?务器", StringUtil.getString(etServerSettingTest)));
		}
	}




	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
