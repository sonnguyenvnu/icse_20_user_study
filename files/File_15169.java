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

import java.util.ArrayList;
import java.util.Arrays;

import zuo.biao.library.R;
import zuo.biao.library.base.BaseBottomWindow;
import zuo.biao.library.util.StringUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**通用底部弹出�?��?�
 * @author Lemon
 * @use
 * <br> toActivity或startActivityForResult (BottomMenuWindow.createIntent(...), requestCode);
 * <br> 然�?�在onActivityResult方法内
 * <br> data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID); �?�得到点击的 position
 * <br> 或
 * <br> data.getIntExtra(BottomMenuWindow.RESULT_INTENT_CODE); �?�得到点击的 intentCode
 */
public class BottomMenuWindow extends BaseBottomWindow implements OnItemClickListener {
	private static final String TAG = "BottomMenuWindow";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**�?�动BottomMenuWindow的Intent
	 * @param context
	 * @param names
	 * @return
	 */
	public static Intent createIntent(Context context, String[] names) {
		return createIntent(context, names, new ArrayList<Integer>());
	}

	/**�?�动BottomMenuWindow的Intent
	 * @param context
	 * @param nameList
	 * @return
	 */
	public static Intent createIntent(Context context, ArrayList<String> nameList) {
		return createIntent(context, nameList, null);
	}

	/**�?�动BottomMenuWindow的Intent
	 * @param context
	 * @param names
	 * @param ids
	 * @return
	 */
	public static Intent createIntent(Context context, String[] names, int[] ids) {
		return new Intent(context, BottomMenuWindow.class).
				putExtra(INTENT_ITEMS, names).
				putExtra(INTENT_ITEM_IDS, ids);
	}

	/**�?�动BottomMenuWindow的Intent
	 * @param context
	 * @param names
	 * @param idList
	 * @return
	 */
	public static Intent createIntent(Context context, String[] names, ArrayList<Integer> idList) {
		return new Intent(context, BottomMenuWindow.class).
				putExtra(INTENT_ITEMS, names).
				putExtra(INTENT_ITEM_IDS, idList);
	}

	/**�?�动BottomMenuWindow的Intent
	 * @param context
	 * @param nameList
	 * @param idList
	 * @return
	 */
	public static Intent createIntent(Context context, 
			ArrayList<String> nameList, ArrayList<Integer> idList) {
		return new Intent(context, BottomMenuWindow.class).
				putStringArrayListExtra(INTENT_ITEMS, nameList).
				putIntegerArrayListExtra(INTENT_ITEM_IDS, idList);
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bottom_menu_window);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private ListView lvBottomMenu;
	@Override
	public void initView() {//必须调用
		super.initView();

		lvBottomMenu = (ListView) findViewById(R.id.lvBottomMenu);
	}



	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private String title;
	private ArrayList<String> nameList = null;
	private ArrayList<Integer> idList = null;

	private ArrayAdapter<String> adapter;
	@Override
	public void initData() {//必须调用
		super.initData();

		intent = getIntent();

		title = intent.getStringExtra(INTENT_TITLE);
		if (StringUtil.isNotEmpty(title, true)) {
			tvBaseTitle.setVisibility(View.VISIBLE);
			tvBaseTitle.setText(StringUtil.getCurrentString());
		} else {
			tvBaseTitle.setVisibility(View.GONE);
		}


		int[] ids = intent.getIntArrayExtra(INTENT_ITEM_IDS);
		if (ids == null || ids.length <= 0) {
			idList = intent.getIntegerArrayListExtra(INTENT_ITEM_IDS);
		} else {
			idList = new ArrayList<Integer>();
			for (int id : ids) {
				idList.add(id);
			}
		}

		String[] menuItems = intent.getStringArrayExtra(INTENT_ITEMS);
		if (menuItems == null || menuItems.length <= 0) {
			nameList = intent.getStringArrayListExtra(INTENT_ITEMS);
		} else {
			nameList = new ArrayList<String>(Arrays.asList(menuItems));
		}
		if (nameList == null || nameList.size() <= 0) {
			Log.e(TAG, "init   nameList == null || nameList.size() <= 0 >> finish();return;");
			finish();
			return;
		}

		adapter = new ArrayAdapter<String>(this, R.layout.bottom_menu_item, R.id.tvBottomMenuItem, nameList);
		lvBottomMenu.setAdapter(adapter);

	}


	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用
		super.initEvent();

		lvBottomMenu.setOnItemClickListener(this);
		
		vBaseBottomWindowRoot.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				finish();
				return true;
			}
		});
	}

	//系统自带监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		intent = new Intent()
		.putExtra(RESULT_TITLE, StringUtil.getTrimedString(tvBaseTitle))
		.putExtra(RESULT_ITEM_ID, position);
		if (idList != null && idList.size() > position) {
			intent.putExtra(RESULT_ITEM_ID, idList.get(position));
		}

		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	protected void setResult() {
		
	}


	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
