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

import java.util.List;

import zuo.biao.apijson.JSON;
import zuo.biao.apijson.JSONRequest;
import zuo.biao.apijson.JSONResponse;
import zuo.biao.apijson.SQL;
import zuo.biao.apijson.StringUtil;
import zuo.biao.library.base.BaseView.OnDataChangedListener;
import zuo.biao.library.interfaces.AdapterCallBack;
import zuo.biao.library.interfaces.CacheCallBack;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.ui.EditTextInfoWindow;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import apijson.demo.client.R;
import apijson.demo.client.adapter.MomentAdapter;
import apijson.demo.client.application.APIJSONApplication;
import apijson.demo.client.base.BaseHttpListFragment;
import apijson.demo.client.interfaces.TopBarMenuCallback;
import apijson.demo.client.model.Moment;
import apijson.demo.client.model.MomentItem;
import apijson.demo.client.util.CommentUtil;
import apijson.demo.client.util.HttpRequest;

import com.alibaba.fastjson.JSONObject;

/**用户列表界�?�fragment
 * @author Lemon
 * @use new MomentListFragment(),详细使用�?.DemoFragmentActivity(initData方法内)
 * @must 查看 .HttpManager 中的@must和@warn
 *       查看 .SettingUtil 中的@must和@warn
 */
public class MomentListFragment extends BaseHttpListFragment<MomentItem, MomentAdapter>
implements CacheCallBack<MomentItem>, OnHttpResponseListener, TopBarMenuCallback
, OnBottomDragListener, OnDataChangedListener {
	private static final String TAG = "MomentListFragment";

	//与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String ARGUMENT_RANGE = "ARGUMENT_RANGE";
	public static final String ARGUMENT_SEARCH = "ARGUMENT_SEARCH";

	/**
	 * <br> range = RANGE_USER_CIRCLE
	 * <br> id = APIJSONApplication.getInstance().getCurrentUserId()
	 * @return
	 */
	public static MomentListFragment createInstance() {
		return createInstance(RANGE_USER_CIRCLE, APIJSONApplication.getInstance().getCurrentUserId());
	}
	/**
	 * range = RANGE_USER
	 * @param userId
	 * @return
	 */
	public static MomentListFragment createInstance(long userId) {
		return createInstance(RANGE_USER, userId);
	}
	/**
	 * @param range
	 * @param id
	 * @return
	 */
	public static MomentListFragment createInstance(int range, long id) {
		return createInstance(range, id, null);
	}
	/**
	 * range = RANGE_ALL
	 * @param search
	 * @return
	 */
	public static MomentListFragment createInstance(JSONObject search) {
		return createInstance(RANGE_ALL, 0, search);
	}
	/**
	 * @param range
	 * @param id
	 * @param search
	 * @return
	 */
	public static MomentListFragment createInstance(int range, long id, JSONObject search) {
		MomentListFragment fragment = new MomentListFragment();

		Bundle bundle = new Bundle();
		bundle.putInt(ARGUMENT_RANGE, range);
		bundle.putLong(ARGUMENT_ID, id);
		bundle.putString(ARGUMENT_SEARCH, JSON.toJSONString(search));

		fragment.setArguments(bundle);
		return fragment;
	}

	//与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	public static final int RANGE_ALL = HttpRequest.RANGE_ALL;
	public static final int RANGE_SINGLE = HttpRequest.RANGE_SINGLE;
	public static final int RANGE_USER = HttpRequest.RANGE_USER;
	public static final int RANGE_USER_FRIEND = HttpRequest.RANGE_USER_FRIEND;
	public static final int RANGE_USER_CIRCLE = HttpRequest.RANGE_USER_CIRCLE;


	private int range = RANGE_ALL;
	private long id;
	private JSONObject search;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		registerObserver(this);

		argument = getArguments();
		if (argument != null) {
			range = argument.getInt(ARGUMENT_RANGE, range);
			id = argument.getLong(ARGUMENT_ID, id);
			search = JSON.parseObject(argument.getString(ARGUMENT_SEARCH));
		}

		initCache(this);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		lvBaseList.onRefresh();

		return view;
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initView() {//必须调用
		super.initView();

	}

	@Override
	public void setList(final List<MomentItem> list) {
		runThread(TAG + "setList", new Runnable() {

			@Override
			public void run() {
				if (list != null) {
					for (MomentItem item : list) {
						if (item != null) {
							item.setCommentItemList(CommentUtil.toSingleLevelList(item.getCommentItemList()));
						}
					}
				}

				runUiThread(new Runnable() {

					@Override
					public void run() {

						setList(new AdapterCallBack<MomentAdapter>() {

							@Override
							public MomentAdapter createAdapter() {
								return new MomentAdapter(context);
							}

							@Override
							public void refreshAdapter() {
								adapter.refresh(list);
							}
						});
					}
				});
			}
		});
	}


	private TextView leftMenu;
	@SuppressLint("InflateParams")
	@Override
	public View getLeftMenu(Activity activity) {
		if (leftMenu == null) {
			leftMenu = (TextView) LayoutInflater.from(activity).inflate(R.layout.top_right_tv, null);
			leftMenu.setGravity(Gravity.CENTER);
			leftMenu.setText("全部");//"筛选");
			leftMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onDragBottom(false);
				}
			});
		}
		return leftMenu;
	}

	private ImageView rightMenu;
	@SuppressLint("InflateParams")
	@Override
	public View getRightMenu(Activity activity) {
		if (rightMenu == null) {
			rightMenu = (ImageView) LayoutInflater.from(activity).inflate(R.layout.top_right_iv, null);
			rightMenu.setImageResource(R.drawable.search);
			rightMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onDragBottom(true);
				}
			});
		}
		return rightMenu;
	}

	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须调用
		super.initData();

	}

	private boolean isAdd = false;
	public void setIsAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}


	@Override
	public void getListAsync(final int page) {
		HttpRequest.getMomentList(range, id, search, getCacheCount(), page, -page, this);
	}

	@Override
	public List<MomentItem> parseArray(String json) {
		return new JSONResponse(json).getList(getCacheClass());
	}

	@Override
	public Class<MomentItem> getCacheClass() {
		return MomentItem.class;
	}
	@Override
	public String getCacheGroup() {
		if (range == RANGE_ALL) {
			return search != null ? null : "range=" + range;
		}
		return range == RANGE_SINGLE || search != null ? null : "range=" + range + ";userId=" + id;
	}
	@Override
	public String getCacheId(MomentItem data) {
		return data == null ? null : "" + data.getId();
	}
	@Override
	public int getCacheCount() {
		return 4;
	}


	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	@Override
	public void initEvent() {//必须调用
		super.initEvent();

		lvBaseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				toActivity(MomentActivity.createIntent(context, id, false));
			}
		});
	}


	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (isAlive() == false) {
			return;
		}

		if (rightToLeft == false) {
			startActivity(MomentListActivity.createIntent(context, RANGE_ALL, 0));
			context.overridePendingTransition(R.anim.bottom_push_in, R.anim.hold);
		} else {
			if (range != RANGE_ALL && verifyLogin() == false) {
				return;
			}

			if (isAdd) {
				toActivity(EditTextInfoWindow.createIntent(context
						, EditTextInfoWindow.TYPE_NOTE, "�?�动�?", "说点什么�?�~"),
						REQUEST_TO_EDIT_TEXT_INFO, false);
			} else {
				showShortToast("输入为空则查看全部");
				toActivity(EditTextInfoWindow.createIntent(context
						, EditTextInfoWindow.TYPE_NAME, "关键�?", null),
						REQUEST_TO_EDIT_TEXT_INFO, false);
			}

		}
	}

	@Override
	public void onDataChanged() {
		if (range == RANGE_USER_CIRCLE) {
			super.onRefresh();
		}
	}

	private static final int HTTP_ADD = 1;

	@Override
	public void onHttpResponse(int requestCode, String resultJson, Exception e) {
		JSONResponse response = new JSONResponse(resultJson);
		if ((range == RANGE_USER_CIRCLE || requestCode == HTTP_ADD) && verifyHttpLogin(response.getCode()) == false) {
			return;
		}
		switch (requestCode) {
		case HTTP_ADD:
			response = response.getJSONResponse(Moment.class.getSimpleName());
			
			if (JSONResponse.isSuccess(response) == false) {
				showShortToast("�?�布失败，请检查网络�?��?试");
			} else {
				runUiThread(new Runnable() {

					@Override
					public void run() {
						showShortToast("�?�布�?功");
						lvBaseList.onRefresh();
					}
				});
			}
			break;
		default:
			super.onHttpResponse(requestCode, resultJson, e);
			break;
		}

	}



	//系统自带监�?�方法 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final int REQUEST_TO_EDIT_TEXT_INFO = 1;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_EDIT_TEXT_INFO:
			if (data != null) {
				String value = StringUtil.getString(data.getStringExtra(EditTextInfoWindow.RESULT_VALUE));

				if (isAdd) {
					HttpRequest.addMoment(value, HTTP_ADD, this);
				} else {
					String split = "";
					JSONRequest search = new JSONRequest();
					if (StringUtil.isNotEmpty(value, true)) {
						split = ":";
						search.putsSearch(HttpRequest.CONTENT, value, SQL.SEARCH_TYPE_CONTAIN_ORDER);
					}
					toActivity(MomentListActivity.createIntent(context, range, id, search, false)
							.putExtra(INTENT_TITLE, "�?�索" + split + value));
				}
			}
			break;
		default:
			break;
		}
	}


	@Override
	public void onDestroy() {
		if (leftMenu != null) {
			leftMenu.destroyDrawingCache();
			leftMenu = null;
		}
		if (rightMenu != null) {
			rightMenu.destroyDrawingCache();
			rightMenu = null;
		}

		super.onDestroy();
	}

	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}
