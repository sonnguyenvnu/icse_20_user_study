package com.xuexiang.xui.widget.statelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xuexiang.xui.R;
import com.xuexiang.xui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个方便在多�?状�?切�?�的view
 *
 * @author xuexiang
 * @since 2019/1/14 下�?�10:04
 */
public class MultipleStatusView extends FrameLayout {
    private static final LayoutParams DEFAULT_LAYOUT_PARAMS = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

    public static final int STATUS_CONTENT = 0x00;
    public static final int STATUS_LOADING = 0x01;
    public static final int STATUS_EMPTY = 0x02;
    public static final int STATUS_ERROR = 0x03;
    public static final int STATUS_NO_NETWORK = 0x04;

    private static final int NULL_RESOURCE_ID = -1;

    /**
     * 空白页�?�
     */
    private View mEmptyView;
    /**
     * 出错页�?�
     */
    private View mErrorView;
    /**
     * loading加载页�?�
     */
    private View mLoadingView;
    /**
     * 无网页�?�
     */
    private View mNoNetworkView;
    /**
     * 主内容页�?�
     */
    private View mContentView;
    /**
     * 空白页�?�资�?id
     */
    private int mEmptyViewResId;
    /**
     * 出错页�?�资�?id
     */
    private int mErrorViewResId;
    /**
     * loading加载页�?�资�?id
     */
    private int mLoadingViewResId;
    /**
     * 无网页�?�资�?id
     */
    private int mNoNetworkViewResId;
    /**
     * 主内容页�?�资�?id
     */
    private int mContentViewResId;

    /**
     * 页�?�当�?的状�?
     */
    private int mViewStatus;
    private LayoutInflater mInflater;
    /**
     * �?试按钮的点击监�?�事件
     */
    private OnClickListener mOnRetryClickListener;

    private final List<Integer> mOtherIds = new ArrayList<>();

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.MultipleStatusViewStyle);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
        mEmptyViewResId = a.getResourceId(R.styleable.MultipleStatusView_msv_emptyView, R.layout.msv_empty_view);
        mErrorViewResId = a.getResourceId(R.styleable.MultipleStatusView_msv_errorView, R.layout.msv_error_view);
        mLoadingViewResId = a.getResourceId(R.styleable.MultipleStatusView_msv_loadingView, R.layout.msv_loading_view);
        mNoNetworkViewResId = a.getResourceId(R.styleable.MultipleStatusView_msv_noNetworkView, R.layout.msv_no_network_view);
        mContentViewResId = a.getResourceId(R.styleable.MultipleStatusView_msv_contentView, NULL_RESOURCE_ID);
        a.recycle();
        mInflater = LayoutInflater.from(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showContent();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear(mEmptyView, mLoadingView, mErrorView, mNoNetworkView);
        if (null != mOtherIds) {
            mOtherIds.clear();
        }
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null;
        }
        mInflater = null;
    }

    /**
     * 获�?�当�?状�?
     */
    public int getViewStatus() {
        return mViewStatus;
    }

    /**
     * 设置�?试点击事件
     *
     * @param onRetryClickListener �?试点击事件
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        mOnRetryClickListener = onRetryClickListener;
    }

    /**
     * 显示空视图
     */
    public final void showEmpty() {
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示空视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局�?�数
     */
    public final void showEmpty(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showEmpty(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示空视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局�?�数
     */
    public final void showEmpty(View view, ViewGroup.LayoutParams layoutParams) {
        Utils.checkNull(view, "Empty view is null!");
        mViewStatus = STATUS_EMPTY;
        if (null == mEmptyView) {
            mEmptyView = view;
            if (null != mOnRetryClickListener) {
                mEmptyView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mEmptyView.getId());
            addView(mEmptyView, 0, layoutParams);
        }
        showViewById(mEmptyView.getId());
    }

    /**
     * 显示错误视图
     */
    public final void showError() {
        showError(mErrorViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示错误视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局�?�数
     */
    public final void showError(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showError(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示错误视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局�?�数
     */
    public final void showError(View view, ViewGroup.LayoutParams layoutParams) {
        Utils.checkNull(view, "Error view is null!");
        mViewStatus = STATUS_ERROR;
        if (null == mErrorView) {
            mErrorView = view;
            if (null != mOnRetryClickListener) {
                mErrorView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mErrorView.getId());
            addView(mErrorView, 0, layoutParams);
        }
        showViewById(mErrorView.getId());
    }

    /**
     * 显示加载中视图
     */
    public final void showLoading() {
        showLoading(mLoadingViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示加载中视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局�?�数
     */
    public final void showLoading(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showLoading(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示加载中视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局�?�数
     */
    public final void showLoading(View view, ViewGroup.LayoutParams layoutParams) {
        Utils.checkNull(view, "Loading view is null!");
        mViewStatus = STATUS_LOADING;
        if (null == mLoadingView) {
            mLoadingView = view;
            mOtherIds.add(mLoadingView.getId());
            addView(mLoadingView, 0, layoutParams);
        }
        showViewById(mLoadingView.getId());
    }

    /**
     * 显示无网络视图
     */
    public final void showNoNetwork() {
        showNoNetwork(mNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示无网络视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局�?�数
     */
    public final void showNoNetwork(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showNoNetwork(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示无网络视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局�?�数
     */
    public final void showNoNetwork(View view, ViewGroup.LayoutParams layoutParams) {
        Utils.checkNull(view, "No network view is null!");
        mViewStatus = STATUS_NO_NETWORK;
        if (null == mNoNetworkView) {
            mNoNetworkView = view;
            if (null != mOnRetryClickListener) {
                mNoNetworkView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mNoNetworkView.getId());
            addView(mNoNetworkView, 0, layoutParams);
        }
        showViewById(mNoNetworkView.getId());
    }

    /**
     * 显示内容视图
     */
    public final void showContent() {
        mViewStatus = STATUS_CONTENT;
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater.inflate(mContentViewResId, null);
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS);
        }
        showContentView();
    }

    public View getErrorView() {
        return mErrorView;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public View getNoNetworkView() {
        return mNoNetworkView;
    }

    public View getContentView() {
        return mContentView;
    }

    private void showContentView() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(mOtherIds.contains(view.getId()) ? View.GONE : View.VISIBLE);
        }
    }

    private View inflateView(int layoutId) {
        return mInflater.inflate(layoutId, null);
    }

    private void showViewById(int viewId) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(view.getId() == viewId ? View.VISIBLE : View.GONE);
        }
    }

    private void clear(View... views) {
        if (null == views) {
            return;
        }
        try {
            for (View view : views) {
                if (null != view) {
                    removeView(view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
