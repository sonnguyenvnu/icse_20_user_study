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

import java.io.File;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.DownloadUtil;
import zuo.biao.library.util.SettingUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import apijson.demo.client.R;
import apijson.demo.client.application.APIJSONApplication;
import apijson.demo.client.util.Constant;
import apijson.demo.client.util.HttpRequest;

import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

/**关于界�?�
 * @author Lemon
 */
public class AboutActivity extends BaseActivity implements OnClickListener, OnLongClickListener, OnBottomDragListener {
	private static final String TAG = "AboutActivity";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**�?�动这个Activity的Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, AboutActivity.class);
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity, this);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		if (SettingUtil.isOnTestMode) {
			showShortToast("测试�?务器\n" + HttpRequest.URL_BASE);
		}
	}

	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private ImageView ivAboutGesture;

	private TextView tvAboutAppInfo;

	private ImageView ivAboutQRCode;
	@Override
	public void initView() {
		
		ivAboutGesture = (ImageView) findViewById(R.id.ivAboutGesture);
		ivAboutGesture.setVisibility(SettingUtil.isFirstStart ? View.VISIBLE : View.GONE);
		if (SettingUtil.isFirstStart) {
			ivAboutGesture.setImageResource(R.drawable.gesture_left);
		}

		tvAboutAppInfo = (TextView) findViewById(R.id.tvAboutAppInfo);

		ivAboutQRCode = findViewById(R.id.ivAboutQRCode, this);
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {
		
		tvAboutAppInfo.setText(APIJSONApplication.getInstance().getAppName()
				+ "\n" + APIJSONApplication.getInstance().getAppVersion());

		setQRCode();
	}


	private Bitmap qRCodeBitmap;
	/**显示二维�?
	 */
	protected void setQRCode() {
		runThread(TAG + "setQRCode", new Runnable() {

			@Override
			public void run() {

				try {
					qRCodeBitmap = EncodingHandler.createQRCode(Constant.APP_DOWNLOAD_WEBSITE
							, (int) (2 * getResources().getDimension(R.dimen.qrcode_size)));
				} catch (WriterException e) {
					e.printStackTrace();
					Log.e(TAG, "initData  try {Bitmap qrcode = EncodingHandler.createQRCode(contactJson, ivContactQRCodeCode.getWidth());" +
							" >> } catch (WriterException e) {" + e.getMessage());
				}

				runUiThread(new Runnable() {
					@Override
					public void run() {
						ivAboutQRCode.setImageBitmap(qRCodeBitmap);
					}
				});		
			}
		});
	}

	/**下载应用
	 */
	private void downloadApp() {
		showProgressDialog("正在下载...");
		runThread(TAG + "downloadApp", new Runnable() {
			@Override
			public void run() {
				File file = DownloadUtil.downLoadFile(context, "APIJSONApp", ".apk", Constant.APP_DOWNLOAD_WEBSITE);
				dismissProgressDialog();
				DownloadUtil.openFile(context, file);
			}
		});
	}

	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {
		
		findViewById(R.id.llAboutUpdate).setOnClickListener(this);
		findViewById(R.id.llAboutShare).setOnClickListener(this);
		findViewById(R.id.llAboutComment).setOnClickListener(this);

		findViewById(R.id.llAboutDeveloper, this).setOnLongClickListener(this);
		findViewById(R.id.llAboutWeibo, this).setOnLongClickListener(this);
		findViewById(R.id.llAboutContactUs, this).setOnLongClickListener(this);
	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			toActivity(WebViewActivity.createIntent(context, "�?�客", Constant.APP_OFFICIAL_BLOG));

			ivAboutGesture.setImageResource(R.drawable.gesture_right);
			return;
		}

		if (SettingUtil.isFirstStart) {
			runThread(TAG + "onDragBottom", new Runnable() {
				@Override
				public void run() {
					Log.i(TAG, "onDragBottom  >> SettingUtil.putBoolean(context, SettingUtil.KEY_IS_FIRST_IN, false);");
					SettingUtil.putBoolean(SettingUtil.KEY_IS_FIRST_START, false);
				}
			});
		}

		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.llAboutUpdate:
			toActivity(WebViewActivity.createIntent(context, "更新日志", Constant.UPDATE_LOG_WEBSITE));
			break;
		case R.id.llAboutShare:
			CommonUtil.shareInfo(context, getString(R.string.share_app)
					+ "\n 点击链接直接下载体验APIJSON\n" + Constant.APP_DOWNLOAD_WEBSITE);
			break;
		case R.id.llAboutComment:
			showShortToast("应用未上线�?能查看");
			startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("market://details?id=" + getPackageName())));
			break;

		case R.id.llAboutDeveloper:
			toActivity(WebViewActivity.createIntent(context, "开�?�者", Constant.APP_DEVELOPER_WEBSITE));
			break;
		case R.id.llAboutWeibo:
			toActivity(WebViewActivity.createIntent(context, "�?�客", Constant.APP_OFFICIAL_BLOG));
			break;
		case R.id.llAboutContactUs:
			CommonUtil.sendEmail(context, Constant.APP_OFFICIAL_EMAIL);
			break;

		case R.id.ivAboutQRCode:
			downloadApp();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onLongClick(View v) {
		switch (v.getId()) {
		case R.id.llAboutDeveloper:
			CommonUtil.copyText(context, Constant.APP_DEVELOPER_WEBSITE);
			return true;
		case R.id.llAboutWeibo:
			CommonUtil.copyText(context, Constant.APP_OFFICIAL_BLOG);
			return true;
		case R.id.llAboutContactUs:
			CommonUtil.copyText(context, Constant.APP_OFFICIAL_EMAIL);
			return true;
		default:
			break;
		}
		return false;
	}



	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
