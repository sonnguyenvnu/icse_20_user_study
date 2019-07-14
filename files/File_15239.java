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
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**通用网页Activity
 * @author Lemon
 * @use toActivity(WebViewActivity.createIntent(...));
 */
public class WebViewActivity extends BaseActivity implements OnBottomDragListener, OnClickListener {
	public static final String TAG = "WebViewActivity";

	public static final String INTENT_RETURN = "INTENT_RETURN";
	public static final String INTENT_URL = "INTENT_URL";

	/**获�?��?�动这个Activity的Intent
	 * @param title
	 * @param url
	 */
	public static Intent createIntent(Context context, String title, String url) {
		return new Intent(context, WebViewActivity.class).
				putExtra(WebViewActivity.INTENT_TITLE, title).
				putExtra(WebViewActivity.INTENT_URL, url);
	}


	@Override
	public Activity getActivity() {
		return this;
	}

	private String url;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_activity, this);//传this是为了全局滑动返回

		url = StringUtil.getCorrectUrl(getIntent().getStringExtra(INTENT_URL));
		if (StringUtil.isNotEmpty(url, true) == false) {
			Log.e(TAG, "initData  StringUtil.isNotEmpty(url, true) == false >> finish(); return;");
			enterAnim = exitAnim = R.anim.null_anim;
			finish();
			return;
		}
		
		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private ProgressBar pbWebView;
	private WebView wvWebView;
	@Override
	public void initView() {
		autoSetTitle();

		pbWebView = (ProgressBar) findViewById(R.id.pbWebView);
		wvWebView = (WebView) findViewById(R.id.wvWebView);
	}



	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	@Override
	public void initData() {

		WebSettings webSettings = wvWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		wvWebView.requestFocus();

		// 设置setWebChromeClient对象  
		wvWebView.setWebChromeClient(new WebChromeClient() {  
			@Override  
			public void onReceivedTitle(WebView view, String title) {  
				super.onReceivedTitle(view, title);
				tvBaseTitle.setText(StringUtil.getTrimedString(title));
			}  
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				pbWebView.setProgress(newProgress);
			}
		});  

		wvWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				wvWebView.loadUrl(url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				tvBaseTitle.setText(StringUtil.getTrimedString(wvWebView.getUrl()));
				pbWebView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				tvBaseTitle.setText(StringUtil.getTrimedString(wvWebView.getTitle()));
				pbWebView.setVisibility(View.GONE);
			}
		});

		wvWebView.loadUrl(url);
	}



	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {

		tvBaseTitle.setOnClickListener(this);
	}

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			if (wvWebView.canGoForward()) {
				wvWebView.goForward();
			}
			return;
		}		
		onBackPressed();
	}

	@Override
	public void onReturnClick(View v) {
		finish();
	}


	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tvBaseTitle) {
			toActivity(EditTextInfoWindow.createIntent(context
					, EditTextInfoWindow.TYPE_WEBSITE
					, StringUtil.getTrimedString(tvBaseTitle)
					, wvWebView.getUrl()),
					REQUEST_TO_EDIT_TEXT_WINDOW, false);
		}
	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void onBackPressed() {
		if (wvWebView.canGoBack()) {
			wvWebView.goBack();
			return;
		}

		super.onBackPressed();
	}

	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	protected void onPause() {
		super.onPause();
		wvWebView.onPause();
	}

	@Override
	protected void onResume() {
		wvWebView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (wvWebView != null) {
			wvWebView.destroy();
			wvWebView = null;
		}
		wvWebView = null;
	}

	protected static final int REQUEST_TO_EDIT_TEXT_WINDOW = 1;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_EDIT_TEXT_WINDOW:
			if (data != null) {
				wvWebView.loadUrl(StringUtil.getCorrectUrl(data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));
			}
			break;
		}
	}

	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



}
