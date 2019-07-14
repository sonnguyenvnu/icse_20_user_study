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

import zuo.biao.library.R;
import zuo.biao.library.interfaces.ActivityPresenter;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.SystemBarTintManager;
import zuo.biao.library.manager.ThreadManager;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.ScreenUtil;
import zuo.biao.library.util.StringUtil;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**基础android.support.v4.app.FragmentActivity，通过继承�?�获�?�或使用 里�?�创建的 组件 和 方法
 * *onFling内控制左�?�滑动手势�?作范围，�?�自定义
 * @author Lemon
 * @see ActivityPresenter#getActivity
 * @see #context
 * @see #view
 * @see #fragmentManager
 * @see #setContentView
 * @see #runUiThread
 * @see #runThread
 * @see #onDestroy
 * @use extends BaseActivity, 具体�?�考 .DemoActivity 和 .DemoFragmentActivity
 */
public abstract class BaseActivity extends FragmentActivity implements ActivityPresenter, OnGestureListener {
	private static final String TAG = "BaseActivity";

	/**
	 * 该Activity实例，命�??为context是因为大部分方法都�?�需�?context，写�?context使用更方便
	 * @warn �?能在�?类中创建
	 */
	protected BaseActivity context = null;
	/**
	 * 该Activity的界�?�，�?�contentView
	 * @warn �?能在�?类中创建
	 */
	protected View view = null;
	/**
	 * 布局解释器
	 * @warn �?能在�?类中创建
	 */
	protected LayoutInflater inflater = null;
	/**
	 * Fragment管�?�器
	 * @warn �?能在�?类中创建
	 */
	protected FragmentManager fragmentManager = null;

	private boolean isAlive = false;
	private boolean isRunning = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		context = (BaseActivity) getActivity();
		isAlive = true;
		fragmentManager = getSupportFragmentManager();

		inflater = getLayoutInflater();

		threadNameList = new ArrayList<String>();
		
		BaseBroadcastReceiver.register(context, receiver, ACTION_EXIT_APP);
	}

	/**
	 * 默认标题�?边的进度环，layout.xml中用@id/pbBaseTitle绑定。
	 * @warn 如果�?Activity的layout中没有android:id="@id/pbBaseTitle"的pbBaseTitle，使用�?必须在�?Activity中赋值
	 */
	@Nullable
	protected ProgressBar pbBaseTitle;
	/**
	 * 默认标题TextView，layout.xml中用@id/tvBaseTitle绑定。�?Activity内调用autoSetTitle方法 会优先使用INTENT_TITLE
	 * @see #autoSetTitle
	 * @warn 如果�?Activity的layout中没有android:id="@id/tvBaseTitle"的TextView，使用�?必须在�?Activity中赋值
	 */
	@Nullable
	protected TextView tvBaseTitle;

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);

		// 状�?�?沉浸，4.4+生效 <<<<<<<<<<<<<<<<<
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.topbar_bg);//状�?背景色，�?�传drawable资�?
		// 状�?�?沉浸，4.4+生效 >>>>>>>>>>>>>>>>>

		pbBaseTitle = (ProgressBar) findViewById(R.id.pbBaseTitle);//绑定默认标题�?ProgressBar
		tvBaseTitle = (TextView) findViewById(R.id.tvBaseTitle);//绑定默认标题TextView
	}

	//底部滑动实现�?�点击标题�?左�?�按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private OnBottomDragListener onBottomDragListener;
	private GestureDetector gestureDetector;
	/**设置该Activity界�?�布局，并设置底部左�?�滑动手势监�?�
	 * @param layoutResID
	 * @param listener
	 * @use 在�?类中
	 * *1.onCreate中super.onCreate�?�setContentView(layoutResID, this);
	 * *2.�?写onDragBottom方法并实现滑动事件处�?�
	 * *3.在导航�?左�?�按钮的onClick事件中调用onDragBottom方法
	 */
	public void setContentView(int layoutResID, OnBottomDragListener listener) {
		setContentView(layoutResID);

		onBottomDragListener = listener;
		gestureDetector = new GestureDetector(this, this);//�?始化手势监�?�类

		view = inflater.inflate(layoutResID, null);
		view.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		});
	}

	//底部滑动实现�?�点击标题�?左�?�按钮效果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	/**
	 * 用于 打开activity以�?�activity之间的通讯（传值）等；一些通讯相关基本�?作（打电�?�?�?�短信等）
	 */
	protected Intent intent = null;

	/**
	 * 退出时之�?的界�?�进入动画,�?�在finish();�?通过改�?�它的值�?�改�?�动画效果
	 */
	protected int enterAnim = R.anim.fade;
	/**
	 * 退出时该界�?�动画,�?�在finish();�?通过改�?�它的值�?�改�?�动画效果
	 */
	protected int exitAnim = R.anim.right_push_out;

	//	/**通过id查找并获�?�控件，使用时�?需�?强转
	//	 * @param id
	//	 * @return 
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public <V extends View> V findViewById(int id) {
	//		return (V) view.findViewById(id);
	//	}
	/**通过id查找并获�?�控件，并setOnClickListener
	 * @param id
	 * @param l
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <V extends View> V findViewById(int id, OnClickListener l) {
		V v = (V) findViewById(id);
		v.setOnClickListener(l);
		return v;
	}

	//自动设置标题方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**自动把标题设置为上个Activity传入的INTENT_TITLE，建议在�?类initView中使用
	 * *这个方法没有return，tvTitle = tvBaseTitle，直接用tvBaseTitle
	 * @must 在UI线程中调用
	 */
	protected void autoSetTitle() {
		tvBaseTitle = autoSetTitle(tvBaseTitle);
	}
	/**自动把标题设置为上个Activity传入的INTENT_TITLE，建议在�?类initView中使用
	 * @param tvTitle
	 * @return tvTitle 返回tvTitle是为了�?�以写�?一行，如 tvTitle = autoSetTitle((TextView) findViewById(titleResId));
	 * @must 在UI线程中调用
	 */
	protected TextView autoSetTitle(TextView tvTitle) {
		if (tvTitle != null) {
			String title = getIntent().getStringExtra(INTENT_TITLE);
			if (StringUtil.isNotEmpty(title, false)) {
				tvTitle.setText(StringUtil.getString(title));
			}
			if (pbBaseTitle != null) {
				tvTitle.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (isShowingProgress() == false) {
							initData();
						}
					}
				});
			}
		}
		return tvTitle;
	}

	//自动设置标题方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//显示与关闭进度弹窗方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**正在显示进度
	 * @return
	 */
	public boolean isShowingProgress() {
		if (pbBaseTitle != null) {
			return pbBaseTitle.getVisibility() == View.VISIBLE;
		}
		if (progressDialog != null) {
			return progressDialog.isShowing();
		} 
		return false;
	}

	/**
	 * 进度弹窗
	 */
	protected ProgressDialog progressDialog = null;

	/**展示加载进度�?�,无标题
	 * stringResId = R.string.loading
	 */
	public void showProgressDialog() {
		showProgressDialog(R.string.loading);
	}
	/**展示加载进度�?�,无标题
	 * @param stringResId
	 */
	public void showProgressDialog(int stringResId) {
		try {
			showProgressDialog(null, context.getResources().getString(stringResId));
		} catch (Exception e) {
			Log.e(TAG, "showProgressDialog  showProgressDialog(null, context.getResources().getString(stringResId));");
		}
	}
	/**展示加载进度�?�,无标题
	 * @param message
	 */
	public void showProgressDialog(String message) {
		showProgressDialog(null, message);
	}
	/**展示加载进度�?�
	 * @param title 标题
	 * @param message 信�?�
	 */
	public void showProgressDialog(final String title, final String message) {
		runUiThread(new Runnable() {
			@Override
			public void run() {
				if (pbBaseTitle != null) {
					pbBaseTitle.setVisibility(View.VISIBLE);
					String s = tvBaseTitle == null ? null : StringUtil.isNotEmpty(message, false) ? message : title;
					if (StringUtil.isNotEmpty(s, true)) {
						tvBaseTitle.setText(s);
					}
					return;
				}

				if (progressDialog == null) {
					progressDialog = new ProgressDialog(context);
				}
				if(progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				if (StringUtil.isNotEmpty(title, false)) {
					progressDialog.setTitle(title);
				}
				if (StringUtil.isNotEmpty(message, false)) {
					progressDialog.setMessage(message);
				}
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.show();
			}
		});
	}


	/**�?�?加载进度
	 */
	public void dismissProgressDialog() {
		runUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}

				if (pbBaseTitle != null) {
					pbBaseTitle.setVisibility(View.GONE);
				}
			}
		});
	}
	//显示与关闭进度弹窗方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//�?�动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
					overridePendingTransition(R.anim.right_push_in, R.anim.hold);
				} else {
					overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
				}
			}
		});
	}
	//�?�动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param stringResId
	 */
	public void showShortToast(int stringResId) {
		try {
			showShortToast(context.getResources().getString(stringResId));
		} catch (Exception e) {
			Log.e(TAG, "showShortToast  context.getResources().getString(resId)" +
					" >>  catch (Exception e) {" + e.getMessage());
		}
	}
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param string
	 */
	public void showShortToast(String string) {
		showShortToast(string, false);
	}
	/**快�?�显示short toast方法，需�?long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---�?常用所以这个类里�?写
	 * @param string
	 * @param isForceDismissProgressDialog
	 */
	public void showShortToast(final String string, final boolean isForceDismissProgressDialog) {
		runUiThread(new Runnable() {
			@Override
			public void run() {
				if (isForceDismissProgressDialog) {
					dismissProgressDialog();
				}
				Toast.makeText(context, "" + string, Toast.LENGTH_SHORT).show();
			}
		});
	}
	//show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//�?行线程 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**在UI线程中�?行，建议用这个方法代替runOnUiThread
	 * @param action
	 */
	public final void runUiThread(Runnable action) {
		if (isAlive() == false) {
			Log.w(TAG, "runUiThread  isAlive() == false >> return;");
			return;
		}
		runOnUiThread(action);
	}
	/**
	 * 线程�??列表
	 */
	protected List<String> threadNameList;
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
		name = StringUtil.getTrimedString(name);
		Handler handler = ThreadManager.getInstance().runThread(name, runnable);
		if (handler == null) {
			Log.e(TAG, "runThread handler == null >> return null;");
			return null;
		}

		if (threadNameList.contains(name) == false) {
			threadNameList.add(name);
		}
		return handler;
	}

	//�?行线程 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//Activity的返回按钮和底部弹窗的�?�消按钮几乎是必备，正好原生支�?�??射；而其它比如Fragment�?少用到，也�?支�?�??射<<<<<<<<<
	/**返回按钮被点击，默认处�?�是onBottomDragListener.onDragBottom(false)，�?写�?�自定义事件处�?�
	 * @param v
	 * @use layout.xml中的组件添加android:onClick="onReturnClick"�?��?�
	 * @warn �?�能在Activity对应的contentView layout中使用；
	 * *给对应View setOnClickListener会导致android:onClick="onReturnClick"失效
	 */
	@Override
	public void onReturnClick(View v) {
		Log.d(TAG, "onReturnClick >>>");
		if (onBottomDragListener != null) {
			onBottomDragListener.onDragBottom(false);
		} else {
			onBackPressed();//会从最外层�?类调finish();BaseBottomWindow就是示例
		}
	}
	/**�?进按钮被点击，默认处�?�是onBottomDragListener.onDragBottom(true)，�?写�?�自定义事件处�?�
	 * @param v
	 * @use layout.xml中的组件添加android:onClick="onForwardClick"�?��?�
	 * @warn �?�能在Activity对应的contentView layout中使用；
	 * *给对应View setOnClickListener会导致android:onClick="onForwardClick"失效
	 */
	@Override
	public void onForwardClick(View v) {
		Log.d(TAG, "onForwardClick >>>");
		if (onBottomDragListener != null) {
			onBottomDragListener.onDragBottom(true);
		}
	}
	//Activity常用导航�?�?�边按钮，而且底部弹窗BottomWindow的确定按钮是必备；而其它比如Fragment�?少用到，也�?支�?�??射>>>>>


	@Override
	public final boolean isAlive() {
		return isAlive && context != null;// & ! isFinishing();导致finish，onDestroy内runUiThread�?�?�用
	}
	@Override
	public final boolean isRunning() {
		return isRunning & isAlive();
	}

	/**一般用于对�?支�?的数�?�的处�?�，比如onCreate中获�?�到�?能接�?�的id(id<=0)�?�以这样处�?�
	 */
	public void finishWithError(String error) {
		showShortToast(error);
		enterAnim = exitAnim = R.anim.null_anim;
		finish();
	}

	@Override
	public void finish() {
		super.finish();//必须写在最�?�?能显示自定义动画
		runUiThread(new Runnable() {
			@Override
			public void run() {
				if (enterAnim > 0 && exitAnim > 0) {
					try {
						overridePendingTransition(enterAnim, exitAnim);
					} catch (Exception e) {
						Log.e(TAG, "finish overridePendingTransition(enterAnim, exitAnim);" +
								" >> catch (Exception e) {  " + e.getMessage());
					}
				}
			}
		});
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
		super.onResume();
		isRunning = true;
		Log.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
		super.onPause();
		isRunning = false;
		Log.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}

	/**销�?并回收内存
	 * @warn �?类如果�?使用这个方法内用到的�?��?，应�?写onDestroy方法并在super.onDestroy();�?�?作
	 */
	@Override
	protected void onDestroy() {
		Log.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
		dismissProgressDialog();
		BaseBroadcastReceiver.unregister(context, receiver);
		ThreadManager.getInstance().destroyThread(threadNameList);
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

		inflater = null;
		view = null;
		pbBaseTitle = null;
		tvBaseTitle = null;

		fragmentManager = null;
		progressDialog = null;
		threadNameList = null;

		intent = null;

		context = null;

		Log.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}

	
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent == null ? null : intent.getAction();
			if (isAlive() == false || StringUtil.isNotEmpty(action, true) == false) {
				Log.e(TAG, "receiver.onReceive  isAlive() == false" +
						" || StringUtil.isNotEmpty(action, true) == false >> return;");
				return;
			}

			if (ACTION_EXIT_APP.equals(action)) {
				finish();
			} 
		}
	};
	


	//手机返回键和�?��?�键实现�?�点击标题�?左�?�按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private boolean isOnKeyLongPress = false;
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		isOnKeyLongPress = true;
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (isOnKeyLongPress) {
			isOnKeyLongPress = false;
			return true;
		}

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (onBottomDragListener != null) {
				onBottomDragListener.onDragBottom(false);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_MENU:
			if (onBottomDragListener != null) {
				onBottomDragListener.onDragBottom(true);
				return true;
			}
			break;
		default:
			break;
		}

		return super.onKeyUp(keyCode, event);
	}

	//手机返回键和�?��?�键实现�?�点击标题�?左�?�按钮效果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//底部滑动实现�?�点击标题�?左�?�按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

		//		/*原�?�实现全局滑动返回的代�?，OnFinishListener已删除，�?�以自己写一个或者
		//		 * 用onBottomDragListener.onDragBottom(false);代替onFinishListener.finish();**/
		//		if (onFinishListener != null) {
		//
		//			float maxDragHeight = getResources().getDimension(R.dimen.page_drag_max_height);
		//			float distanceY = e2.getRawY() - e1.getRawY();
		//			if (distanceY < maxDragHeight && distanceY > - maxDragHeight) {
		//
		//				float minDragWidth = getResources().getDimension(R.dimen.page_drag_min_width);
		//				float distanceX = e2.getRawX() - e1.getRawX();
		//				if (distanceX > minDragWidth) {
		//					onFinishListener.finish();
		//					return true;
		//				}
		//			}
		//		}


		//底部滑动实现�?�点击标题�?左�?�按钮效果
		if (onBottomDragListener != null && e1.getRawY() > ScreenUtil.getScreenSize(this)[1]
				- ((int) getResources().getDimension(R.dimen.bottom_drag_height))) {

			float maxDragHeight = getResources().getDimension(R.dimen.bottom_drag_max_height);
			float distanceY = e2.getRawY() - e1.getRawY();
			if (distanceY < maxDragHeight && distanceY > - maxDragHeight) {

				float minDragWidth = getResources().getDimension(R.dimen.bottom_drag_min_width);
				float distanceX = e2.getRawX() - e1.getRawX();
				if (distanceX > minDragWidth) {
					onBottomDragListener.onDragBottom(false);
					return true;
				} else if (distanceX < - minDragWidth) {
					onBottomDragListener.onDragBottom(true);
					return true;
				}
			} 
		}

		return false;
	}
	@Override  
	public boolean dispatchTouchEvent(MotionEvent ev) {  
		if (gestureDetector != null) {
			gestureDetector.onTouchEvent(ev);  
		}
		return super.dispatchTouchEvent(ev);
	}

	//底部滑动实现�?�点击标题�?左�?�按钮效果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}
