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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apijson.demo.client.R;
import apijson.demo.client.base.BaseActivity;
import apijson.demo.client.model.Moment;
import apijson.demo.client.model.User;
import apijson.demo.client.util.ActionUtil;
import apijson.demo.client.util.HttpRequest;
import apijson.demo.client.util.MenuUtil;
import apijson.demo.client.view.UserView;
import apijson.demo.server.model.Privacy;
import zuo.biao.apijson.JSONRequest;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.library.base.BaseView.OnDataChangedListener;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.CacheManager;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.model.Entry;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.AlertDialog.OnDialogButtonClickListener;
import zuo.biao.library.ui.BottomMenuView;
import zuo.biao.library.ui.BottomMenuView.OnBottomMenuItemClickListener;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.CutPictureActivity;
import zuo.biao.library.ui.EditTextInfoActivity;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.ui.GridAdapter;
import zuo.biao.library.ui.SelectPictureActivity;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.DataKeeper;
import zuo.biao.library.util.JSON;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

/**�?�系人资料界�?�
 * @author Lemon
 */
public class UserActivity extends BaseActivity implements OnClickListener, OnBottomDragListener
, OnBottomMenuItemClickListener, OnHttpResponseListener, OnDialogButtonClickListener, OnDataChangedListener {
	public static final String TAG = "UserActivity";

	public static final String INTENT_IS_ON_EDIT_MODE = "INTENT_IS_ON_EDIT_MODE";

	/**获�?��?�动UserActivity的intent
	 * @param context
	 * @param id
	 * @return
	 */
	public static Intent createIntent(Context context, long id) {
		return new Intent(context, UserActivity.class).putExtra(INTENT_ID, id);
	}


	@Override
	public Activity getActivity() {
		return this;
	}

	private long id = 0;
	private boolean isOnEditMode = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_activity, this);

		registerObserver(this);

		intent = getIntent();
		id = intent.getLongExtra(INTENT_ID, id);
		isOnEditMode = intent.getBooleanExtra(INTENT_IS_ON_EDIT_MODE, isOnEditMode);

		id = getIntent().getLongExtra(INTENT_ID, id);
		if (id <= 0) {
			finishWithError("用户�?存在�?");
			return;
		}

		if (isOnEditMode && isCurrentUser(id) == false) {
			Log.e(TAG, "�?�能修改自己的个人资料");
			isOnEditMode = false;
		}

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	//	private BaseViewLayout<User> bvlUser;//方�?一
	//	private UserViewLayout uvlUser;//方�?二

	private ViewGroup llUserBusinessCardContainer;//方�?三
	private UserView userView;

	private View llUserMoment;
	private GridView gvUserMoment;

	private TextView tvUserRemark;
	private TextView tvUserPhone;

	private ViewGroup llUserBottomMenuContainer;
	private BottomMenuView bottomMenuView;
	@Override
	public void initView() {//必须调用

		//添加用户�??片，这些方�?都�?�<<<<<<<<<<<<<<<<<<<<<<
		//		//方�?一
		//		bvlUser = (BaseViewLayout<User>) findViewById(R.id.bvlUser);
		//		bvlUser.createView(new UserView(context, getResources()));
		//
		//		//方�?二
		//		uvlUser = (UserViewLayout) findViewById(R.id.uvlUser);

		//方�?三
		llUserBusinessCardContainer = (ViewGroup) findViewById(R.id.llUserBusinessCardContainer);
		llUserBusinessCardContainer.removeAllViews();

		userView = new UserView(context, getResources());
		llUserBusinessCardContainer.addView(userView.createView(getLayoutInflater()));
		//添加用户�??片，这些方�?都�?�>>>>>>>>>>>>>>>>>>>>>>>


		llUserMoment = findViewById(R.id.llUserMoment);
		gvUserMoment = (GridView) findViewById(R.id.gvUserMoment);

		tvUserRemark = (TextView) findViewById(R.id.tvUserRemark);
		tvUserPhone = (TextView) findViewById(R.id.tvUserPhone);

		llUserMoment.setVisibility(isOnEditMode ? View.GONE : View.VISIBLE);
		if (isOnEditMode == false) {
			//添加底部�?��?�<<<<<<<<<<<<<<<<<<<<<<
			llUserBottomMenuContainer = (ViewGroup) findViewById(R.id.llUserBottomMenuContainer);
			llUserBottomMenuContainer.removeAllViews();

			bottomMenuView = new BottomMenuView(context, getResources(), REQUEST_TO_BOTTOM_MENU);
			llUserBottomMenuContainer.addView(bottomMenuView.createView(getLayoutInflater()));
			//添加底部�?��?�>>>>>>>>>>>>>>>>>>>>>>>
		}

	}

	private User user;
	private List<Moment> momentList;
	private GridAdapter adapter;
	/**显示用户
	 * @param user_
	 */
	private void setUser(User user_) {
		setUser(user_, momentList);
	}
	/**显示用户
	 * @param user_
	 * @param momentList_
	 */
	private void setUser(User user_, List<Moment> momentList_) {
		if (user_ == null) {
			Log.w(TAG, "setUser  user_ == null >> user = new User();");
			user_ = new User();
		}
		this.user = user_;
		this.momentList = momentList_;
		if (momentList == null) {
			momentList = new ArrayList<>();
		}
		final List<Entry<String, String>> list = new ArrayList<>();
		for (Moment moment : momentList) {
			list.add(new Entry<String, String>(moment.getFirstPicture()));
		}

		runUiThread(new Runnable() {

			@Override
			public void run() {
				//				bvlUser.bindView(user);//方�?一
				//				uvlUser.bindView(user);//方�?二
				userView.bindView(user);//方�?三

				tvUserRemark.setText(StringUtil.getTrimedString(user.getHead()));

				if (adapter == null) {
					adapter = new GridAdapter(context);
					adapter.setHasName(false);
					gvUserMoment.setAdapter(adapter);
				}
				adapter.refresh(list);
			}
		});
	}


	private Privacy privacy;
	/**显示用户�?�?信�?�
	 * @param privacy
	 */
	private void setPrivacy(Privacy privacy_) {
		if (privacy_ == null) {
			Log.w(TAG, "setUser  user_ == null >> user = new User();");
			privacy_ = new Privacy();
		}
		this.privacy = privacy_;
		runUiThread(new Runnable() {

			@Override
			public void run() {
				tvUserPhone.setText(StringUtil.getTrimedString(privacy.getPhone()));
			}
		});
	}

	/**�?剪图片
	 * @param path
	 */
	private void cutPicture(String path) {
		if (StringUtil.isFilePath(path) == false) {
			Log.e(TAG, "cutPicture  StringUtil.isFilePath(path) == false >> showShortToast(找�?到图片);return;");
			showShortToast("找�?到图片");
			return;
		}

		toActivity(CutPictureActivity.createIntent(context, path
				, DataKeeper.imagePath, "photo" + System.currentTimeMillis(), 200)
				, REQUEST_TO_CUT_PICTURE);
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void initData() {//必须调用
		super.initData();

		if (isCurrentUserCorrect() == false) {//解决�?能在未登录情况下查看
			onDataChanged();
		}
	}

	@Override
	public void onDataChanged() {
		tvBaseTitle.setText(isOnEditMode ? "编辑资料" : (isCurrentUser(id) ? "我的资料" : "详细资料"));

		if (bottomMenuView != null) {
			bottomMenuView.bindView(MenuUtil.getMenuList(MenuUtil.USER, id, ! User.isFriend(currentUser, id)));
		}

		runThread(TAG + "onDataChanged", new Runnable() {

			@Override
			public void run() {
				//先加载缓存数�?�，比网络请求快很多
				user = CacheManager.getInstance().get(User.class, "" + id);
				if (isOnEditMode == false) {
					momentList = CacheManager.getInstance().getList(Moment.class, "userId=" + id, 0, 3);
				}
				runUiThread(new Runnable() {

					@Override
					public void run() {
						setUser(user, momentList);
						setPrivacy(null);
					}
				});

				HttpRequest.getUser(id, ! isOnEditMode, HTTP_GET, UserActivity.this);
				HttpRequest.getPrivacy(id, HTTP_GET_PRIVACY, UserActivity.this);
			}
		});

	}


	private User getUser() {
		if (user == null) {
			user = new User(id);
		}
		return user;
	}

	private void putUser() {
		showProgressDialog("正在上传...");
		user = getUser();
		apijson.demo.server.model.User userRequest = new apijson.demo.server.model.User(user.getId());
		userRequest.setName(user.getName());
		userRequest.setSex(user.getSex());
		userRequest.setTag(user.getTag());
		userRequest.setHead(user.getHead());
		//		userRequest.setStarred(user.getStarred());

		HttpRequest.put(new JSONRequest(userRequest).setTag(User.class.getSimpleName())
				, HTTP_PUT, this);
	}

	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用

		llUserMoment.setOnClickListener(this);
		gvUserMoment.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				llUserMoment.onTouchEvent(event);
				return false;
			}
		});


		if (isOnEditMode) {
			findViewById(R.id.llUserRemark).setOnClickListener(this);
			findViewById(R.id.llUserPhone).setOnClickListener(this);

			userView.setOnDataChangedListener(new OnDataChangedListener() {

				@Override
				public void onDataChanged() {
					user = userView.getData();
					isDataChanged = true;
				}
			});
		}
		userView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (user == null) {
					return;
				}
				switch (v.getId()) {
				case R.id.ivUserViewHead:
					if (isOnEditMode) {
						toActivity(SelectPictureActivity.createIntent(context), REQUEST_TO_SELECT_PICTURE, false);
					} else {
						toActivity(WebViewActivity.createIntent(context, user.getHead(), user.getHead()));
					}
					break;
				case R.id.tvUserViewName:
					if (isOnEditMode) {
						toActivity(EditTextInfoWindow.createIntent(context, "�??字", user.getName())
								, REQUEST_TO_EDIT_TEXT_INFO_NAME, false);
					} else {
						CommonUtil.copyText(context, user.getName());
					}
					break;
				default:
					switch (v.getId()) {
					case R.id.tvUserViewSex:
						if (isOnEditMode) {
							user.setSex(user.getSex() == User.SEX_FEMALE ? User.SEX_MAIL : User.SEX_FEMALE);
						}
						break;
					case R.id.tvUserViewTag:
						if (isOnEditMode) {
							toActivity(EditTextInfoWindow.createIntent(context
									, "标签", user.getTag()), REQUEST_TO_EDIT_TEXT_INFO_TAG, false);
						} else {
							CommonUtil.copyText(context, user.getTag());
						}
						break;
					default:
						return;
					}
					if (isOnEditMode) {
						userView.bindView(user);
						isDataChanged = true;
					}
					break;
				}
			}
		});

		if (bottomMenuView != null) {
			bottomMenuView.setOnMenuItemClickListener(this);//底部�?��?�点击监�?�
		}
	}

	@Override
	public void onBottomMenuItemClick(int intentCode) {
		if (user == null) {
			Log.e(TAG, "onBottomMenuItemClick  user == null >> return;");
			return;
		}
		if (verifyLogin() == false) {
			return;
		}
		switch (intentCode) {
		case MenuUtil.INTENT_CODE_EDIT:
			toActivity(UserActivity.createIntent(context, id).putExtra(UserActivity.INTENT_IS_ON_EDIT_MODE, true));
			break;
		case MenuUtil.INTENT_CODE_ADD:
			HttpRequest.setIsFriend(id, true, HTTP_ADD, this);
			break;
		case MenuUtil.INTENT_CODE_DELETE:
			HttpRequest.setIsFriend(id, false, HTTP_DELETE, this);
			break;
		case MenuUtil.INTENT_CODE_QRCODE:
			toActivity(QRCodeActivity.createIntent(context, id));
			break;
		case MenuUtil.INTENT_CODE_SEND:
			CommonUtil.shareInfo(context, JSON.toJSONString(user));
			break;
		default:
			break;
		}
	}

	//对应HttpRequest.getUser(id, 0, UserActivity.this); <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	protected static final int HTTP_GET = 1;
	protected static final int HTTP_GET_PRIVACY = 5;
	protected static final int HTTP_ADD = 2;
	protected static final int HTTP_DELETE = 3;
	protected static final int HTTP_PUT = 4;

	@Override
	public void onHttpResponse(int requestCode, String resultJson, Exception e) {
		JSONResponse response = new JSONResponse(resultJson);
		JSONResponse response2 = response.getJSONResponse(User.class.getSimpleName());
		boolean isSucceed = JSONResponse.isSuccess(response2);

		dismissProgressDialog();
		switch (requestCode) {
		case HTTP_GET:
			User user = response.getObject(User.class);
			if (user == null || user.getId() <= 0) {
				if (JSONResponse.isSuccess(response)) {
					showShortToast("用户已注销");
					super.finish();//需�?动画，且�?需�?�?存缓存
					return;
				}
				showShortToast(R.string.get_failed);
			}
			setUser(user, response.getList(Moment.class.getSimpleName() + "[]", Moment.class));
			break;
		case HTTP_GET_PRIVACY:
			setPrivacy(response.getObject(Privacy.class));
			break;
		case HTTP_ADD:
		case HTTP_DELETE:
			if (verifyHttpLogin(response.getCode()) == false) {
				return;
			}
			if (isSucceed) {
				showShortToast(requestCode == HTTP_ADD ? R.string.add_succeed : R.string.delete_succeed);
				sendBroadcast(new Intent(ActionUtil.ACTION_RELOAD_CURRENT_USER));
			} else {
				showShortToast(requestCode == HTTP_ADD ? R.string.add_failed : R.string.delete_failed);
			}
			break;
		case HTTP_PUT:
			if (verifyHttpLogin(response.getCode()) == false) {
				return;
			}
			if (isSucceed) {
				isDataChanged = false;
				sendBroadcast(new Intent(ActionUtil.ACTION_RELOAD_CURRENT_USER));
			}
			showShortToast(isSucceed ? "�??交�?功" : "�??交失败，请检查网络�?��?试");
			break;
		default:
			break;
		}
	}
	//对应HttpRequest.getUser(id, 0, UserActivity.this); >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
		case R.id.llUserMoment:
			toActivity(MomentListActivity.createIntent(context, id));
			break;
		case R.id.llUserRemark:
			toActivity(EditTextInfoActivity.createIntent(context, EditTextInfoActivity.TYPE_WEBSITE, "备注"
					, StringUtil.getTrimedString(tvUserRemark)), REQUEST_TO_EDIT_TEXT_INFO_REMARK);
			break;
		case R.id.llUserPhone:
			toActivity(EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_PHONE
					, "手机", privacy.getPhone()), REQUEST_TO_EDIT_TEXT_INFO_PHONE, false);
			break;
		default:
			break;
		}
	}

	public static final int DIALOG_PUT_USER = 1;
	@Override
	public void onDialogButtonClick(int requestCode, boolean isPositive) {
		if (isPositive == false) {
			if (requestCode == DIALOG_PUT_USER) {
				isDataChanged = false;
				finish();
			}
			return;
		}

		switch (requestCode) {
		case DIALOG_PUT_USER:
			putUser();
			break;
		default:
			break;
		}
	}


	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final int REQUEST_TO_BOTTOM_MENU = 1;
	private static final int REQUEST_TO_SELECT_PICTURE = 2;
	private static final int REQUEST_TO_CUT_PICTURE = 3;
	private static final int REQUEST_TO_EDIT_TEXT_INFO_NAME = 5;
	private static final int REQUEST_TO_EDIT_TEXT_INFO_PHONE = 6;
	private static final int REQUEST_TO_EDIT_TEXT_INFO_REMARK = 7;
	private static final int REQUEST_TO_EDIT_TEXT_INFO_TAG = 8;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_BOTTOM_MENU:
			if (data != null) {
				onBottomMenuItemClick(data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1));
			}
			break;
		case REQUEST_TO_EDIT_TEXT_INFO_NAME:
		case REQUEST_TO_EDIT_TEXT_INFO_PHONE:
		case REQUEST_TO_EDIT_TEXT_INFO_REMARK:
		case REQUEST_TO_EDIT_TEXT_INFO_TAG:
			if (data == null) {
				break;
			}
			user = getUser();
			String value = data.getStringExtra(EditTextInfoActivity.RESULT_VALUE);
			switch (requestCode) {
			case REQUEST_TO_EDIT_TEXT_INFO_NAME:
				user.setName(value);
				break;
			case REQUEST_TO_EDIT_TEXT_INFO_PHONE:
				privacy.setPhone(value);
				break;
			case REQUEST_TO_EDIT_TEXT_INFO_REMARK:
				user.setHead(value);
				break;
			case REQUEST_TO_EDIT_TEXT_INFO_TAG:
				user.setTag(value);
				break;
			default:
				return;
			}
			isDataChanged = true;
			setUser(user);
			break;
		case REQUEST_TO_SELECT_PICTURE:
			if (data != null) {
				cutPicture(data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH));
			}
			break;
		case REQUEST_TO_CUT_PICTURE:
			if (data != null) {
				isDataChanged = true;

				user = getUser();
				user.setHead(data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
				setUser(user);
			}
			break;
		}
	}


	private boolean isDataChanged = false;
	@Override
	public void finish() {
		user = getUser();
		user.setHead(StringUtil.getTrimedString(tvUserRemark));
		if (isOnEditMode && isDataChanged) {
			new AlertDialog(context, "", "资料已改�?�，需�?�?存�?�？", true, DIALOG_PUT_USER, this).show();
			return;
		}
		if (user != null) {
			CacheManager.getInstance().save(User.class, user, "" + user.getId(), "range=" + 0);//更新缓存
		}

		super.finish();
	}


	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
