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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

import apijson.demo.client.R;
import apijson.demo.client.application.APIJSONApplication;
import zuo.biao.apijson.JSON;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;

/**用户列表界�?�
 * @author Lemon
 * @use toActivity(UserListActivity.createIntent(...));
 */
public class UserListActivity extends BaseActivity implements OnBottomDragListener {
	//	private static final String TAG = "DemoFragmentActivity";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String INTENT_RANGE = "INTENT_RANGE";
	public static final String INTENT_SEARCH = "INTENT_SEARCH";
	public static final String INTENT_SHOW_SEARCH = "INTENT_SHOW_SEARCH";
	public static final String INTENT_ID_LIST = "INTENT_ID_LIST";

	/**�?�动这个Activity的Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return createIntent(context, UserListFragment.RANGE_USER_CIRCLE
				, APIJSONApplication.getInstance().getCurrentUserId());
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param userId
	 * @return
	 */
	public static Intent createIntent(Context context, long userId) {
		return createIntent(context, UserListFragment.RANGE_USER, userId);
	}
	/**�?�动这个Activity的Intent
	 * showSearch = true;
	 * @param context
	 * @param range
	 * @param id
	 * @return
	 */
	public static Intent createIntent(Context context, int range, long id) {
		return createIntent(context, range, id, null, true);
	}
	/**�?�动这个Activity的Intent
	 * showSearch = false;
	 * @param context
	 * @param search
	 * @return
	 */
	public static Intent createIntent(Context context, JSONObject search) {
		return createIntent(context, search, false);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param search
	 * @param showSearch
	 * @return
	 */
	public static Intent createIntent(Context context, JSONObject search, boolean showSearch) {
		return createIntent(context, UserListFragment.RANGE_ALL, 0, search, showSearch);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param range
	 * @param id
	 * @param search
	 * @param showSearch
	 * @return
	 */
	public static Intent createIntent(Context context, int range, long id, JSONObject search, boolean showSearch) {
		return new Intent(context, UserListActivity.class)
		.putExtra(INTENT_RANGE, range)
		.putExtra(INTENT_ID, id)
		.putExtra(INTENT_SEARCH, JSON.toJSONString(search))
		.putExtra(INTENT_SHOW_SEARCH, showSearch);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param idList
	 * @return
	 */
	public static Intent createIntent(Context context, List<Long> idList) {
		return new Intent(context, UserListActivity.class)
		.putExtra(INTENT_ID_LIST, (Serializable) idList);
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public Activity getActivity() {
		return this;
	}

	private int range = UserListFragment.RANGE_ALL;
	private long id;
	private String search;
	private boolean showSearch;
	private List<Long> idList;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_list_activity, this);

		range = getIntent().getIntExtra(INTENT_RANGE, range);
		id = getIntent().getLongExtra(INTENT_ID, id);
		search = getIntent().getStringExtra(INTENT_SEARCH);
		showSearch = getIntent().getBooleanExtra(INTENT_SHOW_SEARCH, showSearch);
		idList = (List<Long>) getIntent().getSerializableExtra(INTENT_ID_LIST);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private UserListFragment fragment;
	@Override
	public void initView() {//必须在onCreate方法内调用
		String title;
		switch (range) {
		case UserListFragment.RANGE_ALL:
			title = "全部";
			break;
		case UserListFragment.RANGE_USER:
			title = APIJSONApplication.getInstance().isCurrentUser(id) ? "我的动�?" : "TA的动�?";
			break;
		case UserListFragment.RANGE_USER_CIRCLE:
			title = "朋�?�圈";
			break;
		default:
			title = "动�?";
			break;
		}
		tvBaseTitle.setText(title);
		autoSetTitle();

		findViewById(R.id.ivUserListForward).setVisibility(showSearch ? View.VISIBLE : View.GONE);


		fragment = idList != null ? UserListFragment.createInstance(idList)
				: UserListFragment.createInstance(range, id, JSON.parseObject(search));

		fragmentManager
		.beginTransaction()
		.add(R.id.flUserListContainer, fragment)
		.show(fragment)
		.commit();
	}



	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须在onCreate方法内调用

	}

	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须在onCreate方法内调用

	}


	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			if (showSearch) {
				fragment.onDragBottom(rightToLeft);
			}
			
			return;
		}

		finish();
	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<





	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
