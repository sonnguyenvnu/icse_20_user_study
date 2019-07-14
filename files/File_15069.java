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

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.interfaces.OnReachViewBorderListener;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.SettingUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**基础Adapter
 * <br> 适用于ListView,GridView等AbsListView的�?类
 * @author Lemon
 * @warn 出于性能考虑，里�?�很多方法对�?��?(比如list)都没有判断，应在adapter外判断
 * @param <T> 数�?�模型(model/JavaBean)类
 * @use extends BaseAdapter<T>, 具体�?�考.DemoAdapter
 *      <br> 预加载使用：
 *      <br> 1.在�?类getView中最�?� return super.getView(position, convertView, parent);//�?�必须，�?�在预加载用到
 *      <br> 2.在使用�?类的类中调用�?类setOnReachViewBorderListener方法（这个方法就在这个类）//�?�必须
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	//	private static final String TAG = "BaseAdapter";


	/**
	 * 管�?�整个界�?�的Activity实例
	 */
	public Activity context;
	/**
	 * 布局解释器,用�?�实例化列表的item的界�?�
	 */
	public LayoutInflater inflater;
	/**
	 * 资�?获�?�器，用于获�?�res目录下的文件�?�文件中的内容等
	 */
	public Resources resources;
	public BaseAdapter(Activity context) {
		this.context = context;

		inflater = context.getLayoutInflater();
		resources = context.getResources();
	}

	/**
	 * 传进�?�的数�?�列表
	 */
	public List<T> list;
	public List<T> getList() {
		return list;
	}
	/**刷新列表
	 */
	public synchronized void refresh(List<T> list) {
		this.list = list == null ? null : new ArrayList<T>(list);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}
	/**获�?�item数�?�
	 */
	@Override
	public T getItem(int position) {
		return list.get(position);
	}
	/**获�?�item的id，如果�?能满足需求�?�在�?类�?写
	 * @param position
	 * @return position
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	//预加载，�?��?使用 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	protected OnReachViewBorderListener onReachViewBorderListener;
	/**设置到达parent的边界的监�?�
	 * @param onReachViewBorderListener
	 */
	public void setOnReachViewBorderListener(OnReachViewBorderListener onReachViewBorderListener) {
		this.onReachViewBorderListener = onReachViewBorderListener;
	}

	/**
	 * 预加载�??�?数。
	 * <br > = 0 - 列表滚到底部(最�?�一个Item View显示)时加载更多
	 * <br > < 0 - �?用加载更多
	 * <br > > 0 - 列表滚到倒数第preloadCount个Item View显示时加载更多
	 * @use �?�在�?类getView被调用�?(�?�以是在构造器内)赋值
	 */
	protected int preloadCount = 0;

	/**获�?�item对应View的方法，带item滑到底部等监�?�
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 * @use �?类的getView中最�?� return super.getView(position, convertView, parent);
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (SettingUtil.preload && onReachViewBorderListener != null && position >= getCount() - 1 - preloadCount) {
			onReachViewBorderListener.onReach(OnReachViewBorderListener.TYPE_BOTTOM, parent);
		}
		return convertView;
	}

	//预加载，�?��?使用 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




	//show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param stringResId
	 */
	public void showShortToast(int stringResId) {
		CommonUtil.showShortToast(context, stringResId);
	}
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param string
	 */
	public void showShortToast(String string) {
		CommonUtil.showShortToast(context, string);
	}
	//show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//�?�动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**打开新的Activity，�?�左滑入效果
	 * @param intent
	 */
	public void toActivity(final Intent intent) {
		CommonUtil.toActivity(context, intent);
	}
	/**打开新的Activity
	 * @param intent
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final boolean showAnimation) {
		CommonUtil.toActivity(context, intent, showAnimation);
	}
	/**打开新的Activity，�?�左滑入效果
	 * @param intent
	 * @param requestCode
	 */
	public void toActivity(final Intent intent, final int requestCode) {
		CommonUtil.toActivity(context, intent, requestCode);
	}
	/**打开新的Activity
	 * @param intent
	 * @param requestCode
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
		CommonUtil.toActivity(context, intent, requestCode, showAnimation);
	}
	//�?�动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
