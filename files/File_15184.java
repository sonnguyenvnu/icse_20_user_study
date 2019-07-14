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

import java.util.List;

import zuo.biao.library.R;
import zuo.biao.library.base.BaseBottomWindow;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.ContactUtil;
import zuo.biao.library.util.EditTextUtil;
import zuo.biao.library.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**通用编辑个人资料文本界�?�
 * @author Lemon
 * @use
 * <br> toActivity或startActivityForResult (EditTextInfoWindow.createIntent(...), requestCode);
 * <br> 然�?�在onActivityResult方法内
 * <br> data.getStringExtra(EditTextInfoWindow.RESULT_EDIT_TEXT_INFO); �?�得到输入框内容 
 */
public class EditTextInfoWindow extends BaseBottomWindow implements OnClickListener {
	//	private static final String TAG = "EditTextInfoWindow";

	/**
	 * @param context
	 * @param key
	 * @param value
	 * @return
	 */
	public static Intent createIntent(Context context, String key, String value) {
		return createIntent(context, key, value, "zuo.biao.library");
	}
	/**
	 * @param context
	 * @param key
	 * @param value
	 * @param packageName
	 * @return
	 */
	public static Intent createIntent(Context context, String key, String value, String packageName) {
		return createIntent(context, 0, key, value, packageName);
	}
	/**
	 * @param context
	 * @param type
	 * @param key
	 * @param value
	 * @return
	 */
	public static Intent createIntent(Context context, int type, String key, String value) {
		return createIntent(context, type, key, value, "zuo.biao.library");
	}
	/**
	 * @param context
	 * @param type
	 * @param key
	 * @param value
	 * @param packageName type == TYPE_MAILADDRESS || type == TYPE_USUALADDRESS时必须�?为空
	 * @return
	 */
	public static Intent createIntent(Context context, int type, String key, String value, String packageName) {
		return new Intent(context, EditTextInfoWindow.class).
				putExtra(INTENT_TYPE, type).
				putExtra(INTENT_KEY, key).
				putExtra(INTENT_VALUE, value).
				putExtra(INTENT_PACKAGE_NAME, packageName);
	}

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_text_info_window);

		//必须调用<<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private TextView tvEditTextInfoPlace;
	private EditText etEditTextInfo;
	private View ivEditTextInfoClear;
	private TextView tvEditTextInfoRemind;
	@Override
	public void initView() {//必须调用
		super.initView();

		tvEditTextInfoPlace = (TextView) findViewById(R.id.tvEditTextInfoPlace);
		tvEditTextInfoPlace.setVisibility(View.GONE);

		etEditTextInfo = (EditText) findViewById(R.id.etEditTextInfo);
		ivEditTextInfoClear = findViewById(R.id.ivEditTextInfoClear);
		tvEditTextInfoRemind = (TextView) findViewById(R.id.tvEditTextInfoRemind);
	}



	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	public static final String INTENT_PACKAGE_NAME = "INTENT_PACKAGE_NAME";

	public static final int TYPE_NICK = ContactUtil.TYPE_NICK;
	public static final int TYPE_NAME = ContactUtil.TYPE_NAME;

	public static final int TYPE_PHONE = ContactUtil.TYPE_PHONE;
	public static final int TYPE_WEBSITE = ContactUtil.TYPE_WEBSITE;
	public static final int TYPE_EMAIL = ContactUtil.TYPE_EMAIL;
	public static final int TYPE_FAX = ContactUtil.TYPE_FAX;

	public static final int TYPE_USUALADDRESS = ContactUtil.TYPE_USUALADDRESS;
	public static final int TYPE_MAILADDRESS = ContactUtil.TYPE_MAILADDRESS;
	public static final int TYPE_SCHOOL = ContactUtil.TYPE_SCHOOL;
	public static final int TYPE_COMPANY = ContactUtil.TYPE_COMPANY;

	public static final int TYPE_PROFESSION = ContactUtil.TYPE_PROFESSION;
	public static final int TYPE_NOTE = ContactUtil.TYPE_NOTE;
	//	public static final int TYPE_OTHER = ContactUtil.TYPE_OTHER;
	
	public static final int TYPE_NUMBER = 21;
	public static final int TYPE_DECIMAL = 22;

	public static final String INTENT_TYPE = "INTENT_TYPE";
	public static final String INTENT_KEY = "INTENT_KEY";
	public static final String INTENT_VALUE = "INTENT_VALUE";

	private String packageName;
	private int intentType = 0;
	private int maxEms = 30;
	@Override
	public void initData() {//必须调用
		super.initData();

		intent = getIntent();
		packageName = intent.getStringExtra(INTENT_PACKAGE_NAME);

		intentType = intent.getIntExtra(INTENT_TYPE, 0);
		if (StringUtil.isNotEmpty(intent.getStringExtra(INTENT_KEY), true)) {
			tvBaseTitle.setText(StringUtil.getCurrentString());
		}
		etEditTextInfo.setSingleLine(intentType != TYPE_NOTE);

		switch (intentType) {
		case TYPE_NICK:
			maxEms = 20;
			break;
		case TYPE_PHONE:
			maxEms = 11;
			etEditTextInfo.setInputType(InputType.TYPE_CLASS_PHONE);
			break;
		case TYPE_EMAIL:
			maxEms = 60;
			etEditTextInfo.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
			break;
		case TYPE_WEBSITE:
			maxEms = 200;
			etEditTextInfo.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
			break;
		case TYPE_MAILADDRESS:
			maxEms = 60;
			break;
		case TYPE_PROFESSION:
			tvEditTextInfoRemind.setText("所属行业");
			maxEms = 15;
		case TYPE_NOTE:
			maxEms = 100;
			break;
		case TYPE_NUMBER:
			maxEms = 10;
			etEditTextInfo.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
			break;
		case TYPE_DECIMAL:
			maxEms = 20;
			etEditTextInfo.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
			break;
		default:
			break;
		}
		etEditTextInfo.setMaxEms(maxEms);
		tvEditTextInfoRemind.setText("�?" + maxEms/2 + "个字（或" + maxEms + "个字符）");

		if (intentType == TYPE_MAILADDRESS || intentType == TYPE_USUALADDRESS) {
			tvEditTextInfoPlace.setVisibility(View.VISIBLE);
			CommonUtil.toActivity(context, PlacePickerWindow.createIntent(
					context, packageName, 2), REQUEST_TO_PLACE_PICKER, false);
		}

	}

	@Override
	protected void setResult() {
		intent = new Intent();
		intent.putExtra(RESULT_TYPE, getIntent().getIntExtra(INTENT_TYPE, -1));
		intent.putExtra(RESULT_KEY, getIntent().getStringExtra(INTENT_KEY));
		intent.putExtra(RESULT_VALUE, editedValue);
		setResult(RESULT_OK, intent);
	}


	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private String inputedString;
	@Override
	public void initEvent() {//必须调用
		super.initEvent();

		tvEditTextInfoPlace.setOnClickListener(this);

		etEditTextInfo.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				inputedString = StringUtil.getTrimedString(s);
				if (StringUtil.isNotEmpty(inputedString, true) == false) {
					ivEditTextInfoClear.setVisibility(View.GONE);
				} else {
					ivEditTextInfoClear.setVisibility(View.VISIBLE);
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		ivEditTextInfoClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				etEditTextInfo.setText("");
			}
		});

		etEditTextInfo.setText(StringUtil.getTrimedString(getIntent().getStringExtra(INTENT_VALUE)));
		etEditTextInfo.setSelection(StringUtil.getLength(etEditTextInfo, true));

	}

	private String editedValue;
	@Override
	public void onForwardClick(View v) {
		editedValue = StringUtil.getTrimedString(tvEditTextInfoPlace) + StringUtil.getTrimedString(etEditTextInfo);
		if (editedValue.equals("" + getIntent().getStringExtra(INTENT_VALUE))) {
			CommonUtil.showShortToast(context, "内容没有改�?�哦~");
			return;
		}
		super.onForwardClick(v);
	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	public static final String RESULT_TYPE = "RESULT_TYPE";
	public static final String RESULT_KEY = "RESULT_KEY";
	public static final String RESULT_VALUE = "RESULT_VALUE";
	public static final String RESULT_URL = "RESULT_URL";
	public static final String RESULT_ID = "RESULT_ID";
	public static final String RESULT_IMAGE_URL = "RESULT_IMAGE_URL";
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tvEditTextInfoPlace) {
			CommonUtil.toActivity(context, PlacePickerWindow.createIntent(
					context, packageName, 2), REQUEST_TO_PLACE_PICKER, false);
		}
	}




	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void finish() {
		super.finish();
		EditTextUtil.hideKeyboard(context, etEditTextInfo);
	}
	
	
	public static final int REQUEST_TO_PLACE_PICKER = 11;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_PLACE_PICKER:
			List<String> list = data == null ? null : data.getStringArrayListExtra(PlacePickerWindow.RESULT_PLACE_LIST);
			if (list == null || list.size() < 2) {
				CommonUtil.showShortToast(context, "请先选择地�?�哦~");
				CommonUtil.toActivity(context, PlacePickerWindow.createIntent(
						context, packageName, 2), REQUEST_TO_PLACE_PICKER, false);
				return;
			}
			String place = "";
			for (String s : list) {
				place += s;
			}
			tvEditTextInfoPlace.setText(place);
			break;
		default:
			break;
		}
	}



	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
