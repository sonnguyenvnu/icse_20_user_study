package com.vondear.rxui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * 若需�?采用Lazy方�?加载的Fragment，�?始化内容放到initData实现
 * 若�?需�?Lazy加载则initData方法内留空,�?始化内容放到initViews�?��?�
 *
 * 注�?事项 1:
 * 如果是与ViewPager一起使用，调用的是setUserVisibleHint。
 *
 * 注�?事项 2:
 * 如果是通过FragmentTransaction的show和hide的方法�?�控制显示，调用的是onHiddenChanged.
 * 针对�?始就show的Fragment 为了触�?�onHiddenChanged事件 达到lazy效果 需�?先hide�?show
 *
 * @author vondear
 * @date 2015/11/21.
 */
public abstract class FragmentLazy extends Fragment {

    /**
     * 是�?��?��?状�?
     */
    private boolean isVisible;

    /**
     * 标志�?，View已�?�?始化完�?。
     */
    private boolean isPrepared;

    /**
     * 是�?�第一次加载
     */
    private boolean isFirstLoad = true;

    public FragmentActivity mContext;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        // 若 viewpager �?设置 setOffscreenPageLimit 或设置数�?�?够
        // 销�?的Fragment onCreateView �?次都会执行(但实体类没有从内存销�?)
        // 导致initData�??�?执行,所以这里注释掉
        // isFirstLoad = true;

        // �?�消 isFirstLoad = true的注释 , 因为上述的initData本身就是应该执行的
        // onCreateView执行 �?明被移出过FragmentManager initData确实�?执行.
        // 如果这里有数�?�累加的Bug 请在initViews方法里�?始化您的数�?� 比如 list.clear();
        mContext = getActivity();

        isFirstLoad = true;
        View view = initViews(layoutInflater, viewGroup, savedInstanceState);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是�?�显示出�?�了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法�?�控制显示，调用的是onHiddenChanged.
     * 若是�?始就show的Fragment 为了触�?�该事件 需�?先hide�?show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     *               visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    /**
     * �?实现延迟加载Fragment内容,需�?在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
    }

    protected abstract View initViews(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState);

    protected abstract void initData();

}
