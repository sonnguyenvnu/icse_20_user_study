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

import zuo.biao.library.R;
import zuo.biao.library.interfaces.FragmentPresenter;
import zuo.biao.library.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**基础android.support.v4.app.Fragment，通过继承�?�获�?�或使用 里�?�创建的 组件 和 方法
 * @author Lemon
 * @see #context
 * @see #view
 * @see #onCreateView
 * @see #setContentView
 * @see #runUiThread
 * @see #runThread
 * @see #onDestroy
 * @use extends BaseFragment, 具体�?�考.DemoFragment
 */
public abstract class BaseFragment extends Fragment implements FragmentPresenter {
	private static final String TAG = "BaseFragment";

	/**
	 * 添加该Fragment的Activity
	 * @warn �?能在�?类中创建
	 */
	protected BaseActivity context = null;
	/**
	 * 该Fragment全局视图
	 * @must �?�abstract�?类的onCreateView中return view;
	 * @warn �?能在�?类中创建
	 */
	protected View view = null;
	/**
	 * 布局解释器
	 * @warn �?能在�?类中创建
	 */
	protected LayoutInflater inflater = null;
	/**
	 * 添加这个Fragment视图的布局
	 * @warn �?能在�?类中创建
	 */
	@Nullable
	protected ViewGroup container = null;

	private boolean isAlive = false;
	private boolean isRunning = false;
	/**
	 * @must 在�?�abstract�?类的onCreateView中super.onCreateView且return view;
	 */
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		context = (BaseActivity) getActivity();
		isAlive = true;

		this.inflater = inflater;
		this.container = container;

		return view;
	}

	/**设置界�?�布局
	 * @warn 最多调用一次
	 * @param layoutResID
	 * @use 在onCreateView�?�调用
	 */
	public void setContentView(int layoutResID) {
		setContentView(inflater.inflate(layoutResID, container, false));
	}
	/**设置界�?�布局
	 * @warn 最多调用一次
	 * @param view
	 * @use 在onCreateView�?�调用
	 */
	public void setContentView(View v) {
		setContentView(v, null);
	}
	/**设置界�?�布局
	 * @warn 最多调用一次
	 * @param view
	 * @param params
	 * @use 在onCreateView�?�调用
	 */
	public void setContentView(View v, ViewGroup.LayoutParams params) {
		view = v;
	}


	/**
	 * 该Fragment在Activity添加的所有Fragment中的�?置，通过ARGUMENT_POSITION设置
	 * @must �?�使用getPosition方法�?�获�?�position，�?�?position正确
	 */
	private int position = -1;
	/**获�?�该Fragment在Activity添加的所有Fragment中的�?置
	 */
	public int getPosition() {
		if (position < 0) {
			argument = getArguments();
			if (argument != null) {
				position = argument.getInt(ARGUMENT_POSITION, position);
			}
		}
		return position;
	}

	/**
	 * �?�用于 打开activity与fragment，fragment与fragment之间的通讯（传值）等
	 */
	protected Bundle argument = null;
	/**
	 * �?�用于 打开activity以�?�activity之间的通讯（传值）等；一些通讯相关基本�?作（打电�?�?�?�短信等）
	 */
	protected Intent intent = null;


	/**通过id查找并获�?�控件，使用时�?需�?强转
	 * @warn 调用�?必须调用setContentView
	 * @param id
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public <V extends View> V findViewById(int id) {
		return (V) view.findViewById(id);
	}
	/**通过id查找并获�?�控件，并setOnClickListener
	 * @param id
	 * @param l
	 * @return
	 */
	public <V extends View> V findViewById(int id, OnClickListener l) {
		V v = findViewById(id);
		v.setOnClickListener(l);
		return v;
	}

	public Intent getIntent() {
		return context.getIntent();
	}

	//�?行线程<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**在UI线程中�?行，建议用这个方法代替runOnUiThread
	 * @param action
	 */
	public final void runUiThread(Runnable action) {
		if (isAlive() == false) {
			Log.w(TAG, "runUiThread  isAlive() == false >> return;");
			return;
		}
		context.runUiThread(action);
	}
	/**�?行线程
	 * @param name
	 * @param runnable
	 * @return
	 */
	public final Handler runThread(String name, Runnable runnable) {
		if (isAlive() == false) {
			Log.w(TAG, "runThread  isAlive() == false >> return null;");
			return null;
		}
		return context.runThread(name + getPosition(), runnable);//name, runnable);�?�一Activity出现多个�?��??Fragment�?�能会出错
	}

	//�?行线程>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//进度弹窗<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**展示加载进度�?�,无标题
	 * @param stringResId
	 */
	public void showProgressDialog(int stringResId){
		if (isAlive() == false) {
			Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.showProgressDialog(context.getResources().getString(stringResId));
	}
	/**展示加载进度�?�,无标题
	 * @param dialogMessage
	 */
	public void showProgressDialog(String dialogMessage){
		if (isAlive() == false) {
			Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.showProgressDialog(dialogMessage);
	}
	/**展示加载进度�?�
	 * @param dialogTitle 标题
	 * @param dialogMessage 信�?�
	 */
	public void showProgressDialog(String dialogTitle, String dialogMessage){
		if (isAlive() == false) {
			Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.showProgressDialog(dialogTitle, dialogMessage);
	}

	/** �?�?加载进度
	 */
	public void dismissProgressDialog(){
		if (isAlive() == false) {
			Log.w(TAG, "dismissProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.dismissProgressDialog();
	}
	//进度弹窗>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//�?�动Activity<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**打开新的Activity，�?�左滑入效果
	 * @param intent
	 */
	public void toActivity(Intent intent) {
		toActivity(intent, true);
	}
	/**打开新的Activity
	 * @param intent
	 * @param showAnimation
	 */
	public void toActivity(Intent intent, boolean showAnimation) {
		toActivity(intent, -1, showAnimation);
	}
	/**打开新的Activity，�?�左滑入效果
	 * @param intent
	 * @param requestCode
	 */
	public void toActivity(Intent intent, int requestCode) {
		toActivity(intent, requestCode, true);
	}
	/**打开新的Activity
	 * @param intent
	 * @param requestCode
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
		runUiThread(new Runnable() {
			@Override
			public void run() {
				if (intent == null) {
					Log.w(TAG, "toActivity  intent == null >> return;");
					return;
				}
				//fragment中使用context.startActivity会导致在fragment中�?能正常接收onActivityResult
				if (requestCode < 0) {
					startActivity(intent);
				} else {
					startActivityForResult(intent, requestCode);
				}
				if (showAnimation) {
					context.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
				} else {
					context.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
				}
			}
		});
	}
	//�?�动Activity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//show short toast<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param stringResId
	 */
	public void showShortToast(int stringResId) {
		if (isAlive() == false) {
			Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.showShortToast(stringResId);
	}
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param string
	 */
	public void showShortToast(String string) {
		if (isAlive() == false) {
			Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.showShortToast(string);
	}
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param string
	 * @param isForceDismissProgressDialog
	 */
	public void showShortToast(String string, boolean isForceDismissProgressDialog) {
		if (isAlive() == false) {
			Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
			return;
		}
		context.showShortToast(string, isForceDismissProgressDialog);
	}
	//show short toast>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public final boolean isAlive() {
		return isAlive && context != null;// & ! isRemoving();导致finish，onDestroy内runUiThread�?�?�用
	}
	@Override
	public final boolean isRunning() {
		return isRunning & isAlive();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
		super.onResume();
		isRunning = true;
		Log.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}

	@Override
	public void onPause() {
		Log.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
		super.onPause();
		isRunning = false;
		Log.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}

	/**销�?并回收内存
	 * @warn �?类如果�?使用这个方法内用到的�?��?，应�?写onDestroy方法并在super.onDestroy();�?�?作
	 */
	@Override
	public void onDestroy() {
		Log.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
		dismissProgressDialog();
		if (view != null) {
			try {
				view.destroyDrawingCache();
			} catch (Exception e) {
				Log.w(TAG, "onDestroy  try { view.destroyDrawingCache();" +
						" >> } catch (Exception e) {\n" + e.getMessage());
			}
		}

		isAlive = false;
		isRunning = false;
		super.onDestroy();

		view = null;
		inflater = null;
		container = null;

		intent = null;
		argument = null;

		context = null;

		Log.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}
}
