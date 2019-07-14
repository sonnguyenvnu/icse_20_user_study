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

package zuo.biao.library.base;

import android.view.View;
import android.widget.BaseAdapter;

import java.util.List;

import zuo.biao.library.interfaces.AdapterCallBack;
import zuo.biao.library.interfaces.OnReachViewBorderListener;
import zuo.biao.library.interfaces.OnStopLoadListener;
import zuo.biao.library.manager.HttpManager;
import zuo.biao.library.ui.xlistview.XListView;
import zuo.biao.library.ui.xlistview.XListView.IXListViewListener;
import zuo.biao.library.util.Log;

/**基础http获�?�列表的Activity
 * @author Lemon
 * @param <T> 数�?�模型(model/JavaBean)类
 * @param <BA> 管�?�XListView的Adapter
 * @see #getListAsync(int)
 * @see #onHttpResponse(int, String, Exception)
 * @use extends BaseHttpListActivity 并在�?类onCreate中lvBaseList.onRefresh();, 具体�?�考 .UserListFragment
 */
public abstract class BaseHttpListActivity<T, BA extends BaseAdapter> extends BaseListActivity<T, XListView, BA>
implements HttpManager.OnHttpResponseListener, IXListViewListener, OnStopLoadListener {
	private static final String TAG = "BaseHttpListActivity";




	// UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initView() {
		super.initView();

		setList((List<T>) null);//ListView需�?设置adapter�?能显示header和footer; setAdapter调�?到�?类方法
	}

	/**设置列表适�?器
	 * @param callBack
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setList(AdapterCallBack<BA> callBack) {
		super.setList(callBack);
		boolean empty = adapter == null || adapter.isEmpty();
		Log.d(TAG, "setList  adapter empty = " + empty);
		lvBaseList.showFooter(! empty);//放setAdapter中�?行，adapter!=null时没有调用setAdapter

		if (adapter != null && adapter instanceof zuo.biao.library.base.BaseAdapter) {
			((zuo.biao.library.base.BaseAdapter<T>) adapter).setOnReachViewBorderListener(
					empty || lvBaseList.isFooterShowing() == false ? null : new OnReachViewBorderListener(){

						@Override
						public void onReach(int type, View v) {
							if (type == TYPE_BOTTOM) {
								lvBaseList.onLoadMore();
							}
						}
					});
		}
	}

	// UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	// Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {
		super.initData();

	}

	/**
	 * 将JSON串转为List（已在�?�UI线程中）
	 * *直接JSON.parseArray(json, getCacheClass());�?�以�?去这个方法，但由于�?�能json�?完全符�?�parseArray�?�件，所以还是�?�?留。
	 * *比如json�?�有其中一部分能作为parseArray的字符串时，必须先�??�?�出这段字符串�?parseArray
	 */
	public abstract List<T> parseArray(String json);


	// Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	// Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {// 必须调用
		super.initEvent();
		setOnStopLoadListener(this);

		lvBaseList.setXListViewListener(this);
	}

	/*
	 * @param page 用-page作为requestCode
	 */
	@Override
	public abstract void getListAsync(int page);

	@Override
	public void onStopRefresh() {
		runUiThread(new Runnable() {

			@Override
			public void run() {
				lvBaseList.stopRefresh();
			}
		});
	}
	@Override
	public void onStopLoadMore(final boolean isHaveMore) {
		runUiThread(new Runnable() {

			@Override
			public void run() {
				lvBaseList.stopLoadMore(isHaveMore);
			}
		});
	}

	/**
	 * @param requestCode  = -page {@link #getListAsync(int)}
	 * @param resultJson
	 * @param e
	 */
	@Override
	public void onHttpResponse(final int requestCode, final String resultJson, final Exception e) {
		runThread(TAG + "onHttpResponse", new Runnable() {

			@Override
			public void run() {
				int page = 0;
				if (requestCode > 0) {
					Log.w(TAG, "requestCode > 0, 应该用BaseListFragment#getListAsync(int page)中的page的负数作为requestCode!");
				} else {
					page = - requestCode;
				}
				List<T> array = parseArray(resultJson);

				if ((array == null || array.isEmpty()) && e != null) {
					onLoadFailed(page, e);
				} else {
					onLoadSucceed(page, array);
				}
			}
		});
	}


	// 系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	// 类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	// 类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// 系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	// Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	// 内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	// 内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
