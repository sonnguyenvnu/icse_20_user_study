package com.starrtc.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by zengyazhi on 2017/8/17.
 */

public class StatusBarUtils {
    private Activity mActivity;
    //状�?�?颜色
    private int mColor = -1;
    //状�?�?drawble
    private Drawable mDrawable;
    //是�?�是最外层布局是 DrawerLayout 的侧滑�?��?�
    private boolean mIsDrawerLayout;
    //是�?�包�?� ActionBar
    private boolean mIsActionBar;
    //侧滑�?��?�页�?�的内容视图
    private int mContentResourseIdInDrawer;

    public StatusBarUtils(Activity activity) {
        mActivity = activity;
    }

    public static StatusBarUtils with(Activity activity) {
        return new StatusBarUtils(activity);
    }

    public int getColor() {
        return mColor;
    }

    public StatusBarUtils setColor(int color) {
        mColor = color;
        return this;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public StatusBarUtils setDrawable(Drawable drawable) {
        mDrawable = drawable;
        return this;
    }

    public boolean isDrawerLayout() {
        return mIsDrawerLayout;
    }

    public boolean isActionBar() {
        return mIsActionBar;
    }

    public StatusBarUtils setIsActionBar(boolean actionBar) {
        mIsActionBar = actionBar;
        return this;
    }

    /**
     * 是�?�是最外层布局为 DrawerLayout 的侧滑�?��?�
     *
     * @param drawerLayout 是�?�最外层布局为 DrawerLayout
     * @param contentId    内容视图的 id
     * @return
     */
    public StatusBarUtils setDrawerLayoutContentId(boolean drawerLayout, int contentId) {
        mIsDrawerLayout = drawerLayout;
        mContentResourseIdInDrawer = contentId;
        return this;
    }

    public void init() {
        fullScreen(mActivity);
        if (mColor != -1) {
            //设置了状�?�?颜色
            addStatusViewWithColor(mActivity, mColor);
        }
        if (mDrawable != null) {
            //设置了状�?�? drawble，例如�?�?�色
            addStatusViewWithDrawble(mActivity, mDrawable);
        }
        if (isDrawerLayout()) {
            //未设置 fitsSystemWindows 且是侧滑�?��?�，需�?设置 fitsSystemWindows 以解决 4.4 上侧滑�?��?�上方白�?�问题
            fitsSystemWindows(mActivity);
        }
        if (isActionBar()) {
            //�?增加内容视图的 paddingTop,�?�则内容被 ActionBar �?�盖
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(mActivity) + getActionBarHeight(mActivity), 0, 0);
            }
        }
    }

    /**
     * 去除 ActionBar 阴影
     */
    public StatusBarUtils clearActionBarShadow() {
        if (Build.VERSION.SDK_INT >= 21) {
            ActionBar supportActionBar = ((AppCompatActivity) mActivity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setElevation(0);
            }
        }
        return this;
    }

    /**
     * 设置页�?�最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     */
    private void fitsSystemWindows(Activity activity) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
            //布局预留状�?�?高度的 padding
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页�?�顶部延伸至status bar;虽默认为false,但�?测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }

    /**
     * 利用�??射获�?�状�?�?高度
     *
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        //获�?�状�?�?高度的资�?id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("getStatusBarHeight", result + "");
        return result;
    }

    /**
     * 获得 ActionBar 的高度
     *
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return result;
    }

    /**
     * 添加状�?�?�?��?视图
     *
     * @param activity
     */
    private void addStatusViewWithColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isDrawerLayout()) {
                //�?在内容布局增加状�?�?，�?�则会盖在侧滑�?��?�上
                ViewGroup rootView = (ViewGroup) activity.findViewById(android.R.id.content);
                //DrawerLayout 则需�?在第一个�?视图�?�内容试图中添加padding
                View parentView = rootView.getChildAt(0);
                LinearLayout linearLayout = new LinearLayout(activity);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                View statusBarView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(activity));
                statusBarView.setBackgroundColor(color);
                //添加�?��?状�?�?到线性布局中
                linearLayout.addView(statusBarView, lp);
                //侧滑�?��?�
                DrawerLayout drawer = (DrawerLayout) parentView;
                //内容视图
                View content = activity.findViewById(mContentResourseIdInDrawer);
                //将内容视图从 DrawerLayout 中移除
                drawer.removeView(content);
                //添加内容视图
                linearLayout.addView(content, content.getLayoutParams());
                //将带有�?��?状�?�?的新的内容视图设置给 DrawerLayout
                drawer.addView(linearLayout, 0);
            } else {
                //设置 paddingTop
                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(mActivity), 0, 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //直接设置状�?�?颜色
                    activity.getWindow().setStatusBarColor(color);
                } else {
                    //增加�?��?状�?�?
                    ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                    View statusBarView = new View(activity);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            getStatusBarHeight(activity));
                    statusBarView.setBackgroundColor(color);
                    decorView.addView(statusBarView, lp);
                }
            }
        }
    }

    /**
     * 添加状�?�?�?��?视图
     *
     * @param activity
     */
    private void addStatusViewWithDrawble(Activity activity, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //�?��?状�?�?
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                statusBarView.setBackground(drawable);
            } else {
                statusBarView.setBackgroundDrawable(drawable);
            }
            if (isDrawerLayout()) {
                //�?在内容布局增加状�?�?，�?�则会盖在侧滑�?��?�上
                ViewGroup rootView = (ViewGroup) activity.findViewById(android.R.id.content);
                //DrawerLayout 则需�?在第一个�?视图�?�内容试图中添加padding
                View parentView = rootView.getChildAt(0);
                LinearLayout linearLayout = new LinearLayout(activity);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                //添加�?��?状�?�?到线性布局中
                linearLayout.addView(statusBarView, lp);
                //侧滑�?��?�
                DrawerLayout drawer = (DrawerLayout) parentView;
                //内容视图
                View content = activity.findViewById(mContentResourseIdInDrawer);
                //将内容视图从 DrawerLayout 中移除
                drawer.removeView(content);
                //添加内容视图
                linearLayout.addView(content, content.getLayoutParams());
                //将带有�?��?状�?�?的新的内容视图设置给 DrawerLayout
                drawer.addView(linearLayout, 0);
            } else {
                //增加�?��?状�?�?，并增加状�?�?高度的 paddingTop
                ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                decorView.addView(statusBarView, lp);
                //设置 paddingTop
                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(mActivity), 0, 0);
            }
        }
    }

    /**
     * 通过设置全�?，设置状�?�?�?明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需�?把颜色设置�?明，�?�则导航�?会呈现系统默认的浅�?�色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag �?结�?�使用，表示让应用的主体内容�?�用系统状�?�?的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航�?颜色也�?�以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 通过设置全�?，设置状�?�?�?明 导航�?黑色
     *
     * @param activity
     */
    public static void setStatusTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();

                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//                attributes.flags |= flagTranslucentStatus;
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);

                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }
}
