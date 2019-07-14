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

import zuo.biao.apijson.JSONRequest;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.CacheManager;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.JSON;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import apijson.demo.client.R;
import apijson.demo.client.model.User;
import apijson.demo.client.util.HttpRequest;

import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

/**二维�?界�?�Activity
 * @author Lemon
 */
public class QRCodeActivity extends BaseActivity implements OnBottomDragListener {
	private static final String TAG = "QRCodeActivity";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**�?�动这个Activity的Intent
	 * @param context
	 * @param userId
	 * @return
	 */
	public static Intent createIntent(Context context, long userId) {
		return new Intent(context, QRCodeActivity.class).
				putExtra(INTENT_ID, userId);
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public Activity getActivity() {
		return this;
	}

	private long userId = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode_activity, this);

		intent = getIntent();
		userId = intent.getLongExtra(INTENT_ID, userId);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	private ImageView ivQRCodeHead;
	private TextView tvQRCodeName;

	private ImageView ivQRCodeCode;
	private View ivQRCodeProgress;
	@Override
	public void initView() {//必须调用
		autoSetTitle();
		
		ivQRCodeHead = (ImageView) findViewById(R.id.ivQRCodeHead);
		tvQRCodeName = (TextView) findViewById(R.id.tvQRCodeName);

		ivQRCodeCode = (ImageView) findViewById(R.id.ivQRCodeCode);
		ivQRCodeProgress = findViewById(R.id.ivQRCodeProgress);
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	private User user;
	@Override
	public void initData() {//必须调用
		
		ivQRCodeProgress.setVisibility(View.VISIBLE);
		runThread(TAG + "initData", new Runnable() {

			@Override
			public void run() {

				user = CacheManager.getInstance().get(User.class, "" + userId);
				if (user == null) {
					user = new User(userId);
				}
				runUiThread(new Runnable() {
					@Override
					public void run() {
						ImageLoaderUtil.loadImage(ivQRCodeHead, user.getHead());
						tvQRCodeName.setText(StringUtil.getTrimedString(user.getName()));
					}
				});

				setQRCode(user);
			}
		});

	}

	private Bitmap qRCodeBitmap;
	protected void setQRCode(User user) {
//		if (user == null) {
//			Log.e(TAG, "setQRCode  user == null" +
//					" || StringUtil.isNotEmpty(user.getPhone(), true) == false >> return;");
//			return;
//		}

		try {
			//�?能暴露用户�?�?
			qRCodeBitmap = EncodingHandler.createQRCode(HttpRequest.URL_GET
					+ JSON.toJSONString(new JSONRequest(new apijson.demo.server.model.User(userId)))
					, (int) (2 * getResources().getDimension(R.dimen.qrcode_size)));
		} catch (WriterException e) {
			e.printStackTrace();
			Log.e(TAG, "initData  try {Bitmap qrcode = EncodingHandler.createQRCode(contactJson, ivQRCodeCode.getWidth());" +
					" >> } catch (WriterException e) {" + e.getMessage());
		}

		runUiThread(new Runnable() {
			@Override
			public void run() {
					ivQRCodeProgress.setVisibility(View.GONE);
					ivQRCodeCode.setImageBitmap(qRCodeBitmap);						
			}
		});	
	}

	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用

	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {

			return;
		}

		finish();
	}



	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	protected void onDestroy() {
		super.onDestroy();

		ivQRCodeProgress = null;
		ivQRCodeCode = null;
		user = null;

		if (qRCodeBitmap != null) {
			if (qRCodeBitmap.isRecycled() == false) {
				qRCodeBitmap.recycle();
			}
			qRCodeBitmap = null;
		}
		if (ivQRCodeCode != null) {
			ivQRCodeCode.setImageBitmap(null);
			ivQRCodeCode = null;
		}
	}


	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
