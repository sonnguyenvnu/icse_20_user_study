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

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import apijson.demo.client.R;
import apijson.demo.client.application.APIJSONApplication;
import apijson.demo.client.base.BaseBottomTabActivity;
import apijson.demo.client.interfaces.TopBarMenuCallback;
import apijson.demo.client.model.User;
import apijson.demo.client.util.ActionUtil;
import apijson.demo.client.util.HttpRequest;
import zuo.biao.apijson.JSON;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.library.base.BaseBroadcastReceiver;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.SettingUtil;
import zuo.biao.library.util.StringUtil;

/**应用主页
 * @author Lemon
 * @use MainTabActivity.createIntent(...)
 */
public class MainTabActivity extends BaseBottomTabActivity implements OnBottomDragListener {
	private static final String TAG = "MainTabActivity";


	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**�?�动这个Activity的Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, MainTabActivity.class);
	}


	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_activity, this);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>


		BaseBroadcastReceiver.register(context, receiver
				, new String[]{ACTION_EXIT_APP, ActionUtil.ACTION_RELOAD_CURRENT_USER});


		if (SettingUtil.isOnTestMode) {
			showShortToast("测试�?务器\n" + HttpRequest.URL_BASE);

			HttpRequest.get(JSON.parseObject(JSON.toJSONString(TestRequestAndResponseJava.request()), zuo.biao.apijson.JSONObject.class), 0, new OnHttpResponseListener() {
				@Override
				public void onHttpResponse(int requestCode, String resultJson, Exception e) {
					TestRequestAndResponseJava.response(resultJson);
					TestRequestAndResponseJava.smartResponse(resultJson);
				}
			});


			HttpRequest.get(JSON.parseObject(JSON.toJSONString(TestRequestAndResponseKt.request()), zuo.biao.apijson.JSONObject.class), 0, new OnHttpResponseListener() {
				@Override
				public void onHttpResponse(int requestCode, String resultJson, Exception e) {
					TestRequestAndResponseKt.response(resultJson);
					TestRequestAndResponseKt.smartResponse(resultJson);
				}
			});
		}

	}



	// UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private ViewGroup llMainTabLeftContainer;
	private ViewGroup llMainTabRightContainer;

	@Override
	public void initView() {// 必须调用
		super.initView();
		exitAnim = R.anim.bottom_push_out;

		llMainTabLeftContainer = (ViewGroup) findViewById(R.id.llMainTabLeftContainer);
		llMainTabRightContainer = (ViewGroup) findViewById(R.id.llMainTabRightContainer);
	}


	@Override
	protected int[] getTabClickIds() {
		return new int[]{R.id.llMainTabTab0, R.id.llMainTabTab1, R.id.llMainTabTab2};
	}

	@Override
	protected int[][] getTabSelectIds() {
		return new int[][]{
				new int[]{R.id.ivMainTabTab0, R.id.ivMainTabTab1, R.id.ivMainTabTab2},//顶部图标
				new int[]{R.id.tvMainTabTab0, R.id.tvMainTabTab1, R.id.tvMainTabTab2}//底部文字
		};
	}

	@Override
	public int getFragmentContainerResId() {
		return R.id.flMainTabFragmentContainer;
	}

	@Override
	protected Fragment getFragment(int position) {
		switch (position) {
			case 1:
				UserListFragment fragment = UserListFragment.createInstance();
				fragment.setSearchType(EditTextInfoWindow.TYPE_NAME);
				return fragment;
			case 2:
				return MineFragment.createInstance();
			default:
				return MomentListFragment.createInstance();
		}
	}

	private static final String[] TAB_NAMES = {"圈�?", "朋�?�", "我的"};
	@Override
	protected void selectTab(int position) {
		tvBaseTitle.setText(TAB_NAMES[position]);

		View left = null, right = null;
		if (fragments[position] instanceof TopBarMenuCallback) {
			left = ((TopBarMenuCallback) fragments[position]).getLeftMenu(context);
			right = ((TopBarMenuCallback) fragments[position]).getRightMenu(context);
		}

		llMainTabLeftContainer.removeAllViews();
		if (left != null) {
			llMainTabLeftContainer.addView(left);
		}
		llMainTabRightContainer.removeAllViews();
		if (right != null) {
			llMainTabRightContainer.addView(right);
		}


		verifyLogin();
	}


	// UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	// Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




	@Override
	public void initData() {// 必须调用
		super.initData();

		if (isCurrentUserCorrect() == false) {
			reloadAll();
		}
	}


	@Override
	protected void reloadAll() {
		Log.d(TAG, "reloadAll >>> ");
		HttpRequest.getUser(APIJSONApplication.getInstance().getCurrentUserId(), 0, new OnHttpResponseListener() {

			@Override
			public void onHttpResponse(int requestCode, String resultJson, Exception e) {
				Log.d(TAG, "reloadAll >>> HttpRequest.getUser.onHttpResponse >>  saveCurrentUser >>");
				APIJSONApplication.getInstance().saveCurrentUser(new JSONResponse(resultJson).getObject(User.class));
				runUiThread(new Runnable() {

					@Override
					public void run() {
						sendBroadcast(new Intent(ActionUtil.ACTION_USER_CHANGED)
								.putExtra(INTENT_ID, APIJSONApplication.getInstance().getCurrentUserId())
								.putExtra(ActionUtil.INTENT_USER, APIJSONApplication.getInstance().getCurrentUser()));
					}
				});
			}
		});
	}

	// Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	// Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {// 必须调用
		super.initEvent();

	}

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (fragments[currentPosition] instanceof OnBottomDragListener) {
			((OnBottomDragListener) fragments[currentPosition]).onDragBottom(rightToLeft);
		}
	}


	// 系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	//�?�击手机返回键退出<<<<<<<<<<<<<<<<<<<<<
	private long firstTime = 0;//第一次返回按钮计时
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch(keyCode){
			case KeyEvent.KEYCODE_BACK:
				long secondTime = System.currentTimeMillis();
				if(secondTime - firstTime > 2000){
					showShortToast("�?按一次退出");
					firstTime = secondTime;
				} else {//完全退出
					sendBroadcast(new Intent(ACTION_EXIT_APP));
				}
				return true;
		}

		return super.onKeyUp(keyCode, event);
	}
	//�?�击手机返回键退出>>>>>>>>>>>>>>>>>>>>>

	// 类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private boolean isToExitApp = false;
	@Override
	protected void onDestroy() {
		BaseBroadcastReceiver.unregister(context, receiver);
		super.onDestroy();
		if (isToExitApp) {
			isToExitApp = false;
			moveTaskToBack(true);//应用退到�?��?�
			System.exit(0);
		}
	}

	// 类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// 系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	// Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	// 内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	private BroadcastReceiver receiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent == null ? null : intent.getAction();
			if (isAlive() == false || StringUtil.isNotEmpty(action, true) == false) {
				Log.e(TAG, "receiver.onReceive  isAlive() == false" +
						" || StringUtil.isNotEmpty(action, true) == false >> return;");
				return;
			}

			if (ACTION_EXIT_APP.equals(action)) {
				isToExitApp = true;
				finish();
				return;
			}

			if (ActionUtil.ACTION_RELOAD_CURRENT_USER.equals(action)) {
				reloadAll();// fragmentManager show remove等都会崩溃
			}
		}
	};

	// 内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
