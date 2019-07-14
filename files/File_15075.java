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

import zuo.biao.library.util.Log;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

/**基础底部标签Activity
 * @author Lemon
 * @use extends BaseBottomTabActivity
 */
public abstract class BaseBottomTabActivity extends BaseActivity {
	private static final String TAG = "BaseBottomTabActivity";



	// UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	protected static int[] tabClickIds;

	protected View[] vTabClickViews;
	protected View[][] vTabSelectViews;
	@Override
	public void initView() {// 必须调用

		tabClickIds = getTabClickIds();

		vTabClickViews = new View[getCount()];
		for (int i = 0; i < getCount(); i++) {
			vTabClickViews[i] = findViewById(tabClickIds[i]);
		}

		int[][] tabSelectIds = getTabSelectIds();
		if (tabSelectIds != null && tabSelectIds.length > 0) {
			vTabSelectViews = new View[tabSelectIds.length][getCount()];
			for (int i = 0; i < tabSelectIds.length; i++) {
				if (tabSelectIds[i] != null) {
					for (int j = 0; j < tabSelectIds[i].length; j++) {
						vTabSelectViews[i][j] = findViewById(tabSelectIds[i][j]);
					}
				}
			}
		}
	}


	/**选择tab，在selectFragment里被调用
	 * @param position
	 */
	protected abstract void selectTab(int position);

	/**设置选中状�?
	 * @param position 
	 */
	protected void setTabSelection(int position) {
		if (vTabSelectViews == null) {
			Log.e(TAG, "setTabSelection  vTabSelectViews == null >> return;");
			return;
		}
		for (int i = 0; i < vTabSelectViews.length; i++) {
			if (vTabSelectViews[i] == null) {
				Log.w(TAG, "setTabSelection  vTabSelectViews[" + i + "] == null >> continue;");
				continue;
			}
			for (int j = 0; j < vTabSelectViews[i].length; j++) {
				vTabSelectViews[i][j].setSelected(j == position);
			}
		}
	}

	protected int currentPosition = 0;
	/**选择并显示fragment
	 * @param position
	 */
	public void selectFragment(int position) {
		if (fragments == null || fragments.length != getCount()) {
			removeAll();
			fragments = new Fragment[getCount()];
		}

		if (currentPosition == position) {
			if (fragments[position] != null && fragments[position].isVisible()) {
				Log.e(TAG, "selectFragment currentPosition == position" +
						" >> fragments[position] != null && fragments[position].isVisible()" +
						" >> return;	");
				return;
			}
		}

		if (fragments[position] == null) {
			fragments[position] = getFragment(position);
		}

		// 用全局的fragmentTransaction因为already committed 崩溃
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.hide(fragments[currentPosition]);
		if (fragments[position].isAdded() == false) {
			fragmentTransaction.add(getFragmentContainerResId(), fragments[position]);
		}
		fragmentTransaction.show(fragments[position]).commit();

		//消耗资�?很少，�?�?Fragment<<<<<<
		setTabSelection(position);
		selectTab(position);
		//消耗资�?很少，�?�?Fragment>>>>>>

		this.currentPosition = position;
	};


	protected void reload(int position) {
		remove(position);
		if (position == currentPosition) {
			selectFragment(position);
		}
	}
	protected void reloadAll() {
		runUiThread(new Runnable() {
			
			@Override
			public void run() {
				removeAll(true);
				selectFragment(currentPosition);				
			}
		});
	}
	protected void remove(int position) {
		remove(position, false);
	}
	protected void remove(int position, boolean destroy) {
		if (fragments != null && position >= 0 && position < fragments.length && fragments[position] != null) {
			try {
				fragmentManager.beginTransaction().remove(fragments[position]).commit();
			} catch (Exception e) {
				Log.e(TAG, "remove  try { fragmentManager.beginTransaction().remove(fragments[position]).commit();" +
						" } catch (Exception e) {\n" + e.getMessage());
				destroy = true;
			}
			if (destroy) {
				fragments[position].onDestroy();
				fragments[position] = null;
			}
		}
	}
	protected void removeAll() {
		removeAll(false);
	}
	protected void removeAll(boolean destroy) {
		if (fragments != null) {
			for (int i = 0; i < fragments.length; i++) {
				remove(i, destroy);
			}
		}
	}

	// UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	// Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	protected Fragment[] fragments;
	@Override
	public void initData() {// 必须调用

		// fragmentActivity�?界�?��?始化<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

		selectFragment(currentPosition);

		// fragmentActivity�?界�?��?始化>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	}


	/**获�?�tab内设置点击事件的View的id
	 * @param position
	 * @return
	 */
	protected abstract int[] getTabClickIds();

	/**获�?�tab内设置选择事件的View的id，setSelected(position == currentPositon)
	 * @return
	 * @warn 返回int[leghth0][leghth1]必须满足leghth0 >= 1 && leghth1 = getCount() = getTabClickIds().length
	 */
	protected abstract int[][] getTabSelectIds();

	/**获�?�Fragment容器的id
	 * @return
	 */
	public abstract int getFragmentContainerResId();

	/**获�?�新的Fragment
	 * @param position
	 * @return
	 */
	protected abstract Fragment getFragment(int position);

	/**获�?�Tab(或Fragment)的数�?
	 * @return
	 */
	public int getCount() {
		return tabClickIds == null ? 0 :tabClickIds.length;
	}

	// Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	// Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {// 必须调用

		for (int i = 0; i < vTabClickViews.length; i++) {
			final int which = i;
			vTabClickViews[which].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					selectFragment(which);
				}
			});
		}
	}

	// 系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	// 类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	// 类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// 系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	// Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	// 内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	// 内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
