package com.example.jingbin.cloudreader.ui.gank.child;

import android.animation.ValueAnimator;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.adapter.EverydayAdapter;
import com.example.jingbin.cloudreader.base.BaseFragment;
import com.example.jingbin.cloudreader.bean.AndroidBean;
import com.example.jingbin.cloudreader.bean.BannerItemBean;
import com.example.jingbin.cloudreader.databinding.FooterItemEverydayBinding;
import com.example.jingbin.cloudreader.databinding.FragmentEverydayBinding;
import com.example.jingbin.cloudreader.databinding.HeaderItemEverydayBinding;
import com.example.jingbin.cloudreader.http.rx.RxBus;
import com.example.jingbin.cloudreader.http.rx.RxBusBaseMessage;
import com.example.jingbin.cloudreader.http.rx.RxCodeConstants;
import com.example.jingbin.cloudreader.utils.DensityUtil;
import com.example.jingbin.cloudreader.utils.GlideImageLoader;
import com.example.jingbin.cloudreader.utils.PerfectClickListener;
import com.example.jingbin.cloudreader.view.webview.WebViewActivity;
import com.example.jingbin.cloudreader.viewmodel.gank.EverydayViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.jingbin.cloudreader.viewmodel.gank.EverydayViewModel.getTodayTime;

/**
 * �?日推�??
 * 更新逻辑：判断是�?�是第二天(相对于2016-11-26)
 * 是：判断是�?�是大于12：30
 * *****     |是：刷新当天数�?�
 * *****     |�?�：使用缓存：|无：请求�?一天数�?�,直到请求到数�?�为止
 * **********             |有：使用缓存
 * �?�：使用缓存 ： |无：请求今天数�?�
 * **********    |有：使用缓存
 */
public class EverydayFragment extends BaseFragment<EverydayViewModel, FragmentEverydayBinding> {

    private HeaderItemEverydayBinding mHeaderBinding;
    private EverydayAdapter mEverydayAdapter;
    private boolean mIsPrepared = false;
    private RotateAnimation animation;
    private boolean isLoadBanner;

    @Override
    public int setContent() {
        return R.layout.fragment_everyday;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showContentView();
        initAnimation();
        initRecyclerView();

        mIsPrepared = true;
        /**
         * 因为�?�动时先走loadData()�?走onActivityCreated，
         * 所以此处�?�?外调用load(),�?然最�?�?会加载内容
         */
        loadData();
    }

    @Override
    protected void loadData() {
        if (mIsPrepared && isLoadBanner) {
            onResume();
        }
        if (!mIsVisible || !mIsPrepared) {
            return;
        }
        bindingView.recyclerView.postDelayed(() -> viewModel.loadData(), 150);
    }

    private void initRecyclerView() {
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_everyday, null, false);
        FooterItemEverydayBinding mFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.footer_item_everyday, null, false);
        bindingView.recyclerView.setPullRefreshEnabled(false);
        bindingView.recyclerView.setLoadingMoreEnabled(false);
        bindingView.recyclerView.addHeaderView(mHeaderBinding.getRoot());
        bindingView.recyclerView.addFootView(mFooterBinding.getRoot(), true);
        bindingView.recyclerView.noMoreLoading();
        bindingView.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，�?然滑动�?�?畅
        bindingView.recyclerView.setNestedScrollingEnabled(false);
        bindingView.recyclerView.setHasFixedSize(false);
        bindingView.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mEverydayAdapter = new EverydayAdapter();
        bindingView.recyclerView.setAdapter(mEverydayAdapter);

        // 显示日期,去掉第一�?的"0"
        String day = getTodayTime().get(2);
        mHeaderBinding.includeEveryday.tvDailyText.setText(day.indexOf("0") == 0 ? day.replace("0", "") : day);
        mHeaderBinding.includeEveryday.ibXiandu.setOnClickListener(listener);
        mHeaderBinding.includeEveryday.ibWanAndroid.setOnClickListener(listener);
        mHeaderBinding.includeEveryday.ibMovieHot.setOnClickListener(listener);
        mHeaderBinding.includeEveryday.flEveryday.setOnClickListener(listener);
        DensityUtil.formatHeight(mHeaderBinding.banner, DensityUtil.getDisplayWidth(), 2.5f, 1);

        onObserveViewModel();
    }

    private void onObserveViewModel() {
        viewModel.getShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                showRotaLoading(aBoolean);
            }
        });
        viewModel.getBannerData().observe(this, new Observer<EverydayViewModel.BannerDataBean>() {
            @Override
            public void onChanged(@Nullable EverydayViewModel.BannerDataBean bean) {
                if (bean != null && bean.getImageUrls() != null && bean.getImageUrls().size() > 0) {
                    mHeaderBinding.banner.setImages(bean.getImageUrls()).setImageLoader(new GlideImageLoader()).start();
                    ArrayList<BannerItemBean> list = bean.getList();
                    if (list != null && list.size() > 0) {
                        mHeaderBinding.banner.setOnBannerListener(position -> {
                            if (!TextUtils.isEmpty(list.get(position).getCode()) && list.get(position).getCode().startsWith("http")) {
                                WebViewActivity.loadUrl(getContext(), list.get(position).getCode(), "加载中...");
                            }
                        });
                    }
                    isLoadBanner = true;
                }
            }
        });
        viewModel.getContentData().observe(this, new Observer<ArrayList<List<AndroidBean>>>() {
            @Override
            public void onChanged(@Nullable ArrayList<List<AndroidBean>> lists) {
                if (lists != null && lists.size() > 0) {
                    mEverydayAdapter.clear();
                    mEverydayAdapter.addAll(lists);
                    mEverydayAdapter.notifyDataSetChanged();
                    bindingView.recyclerView.refreshComplete();
                } else {
                    showError();
                }
            }
        });
    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.ib_xiandu:
                    WebViewActivity.loadUrl(v.getContext(), getString(R.string.string_url_xiandu), "闲读");
                    break;
                case R.id.ib_wan_android:
                    WebViewActivity.loadUrl(v.getContext(), getString(R.string.string_url_wanandroid), "玩Android");
                    break;
                case R.id.ib_movie_hot:
                    RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE_TO_ONE, new RxBusBaseMessage());
                    break;
                case R.id.fl_everyday:
                    WebViewActivity.loadUrl(v.getContext(), getString(R.string.string_url_trending), "Trending");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // 失去焦点，�?�则RecyclerView第一个item会回到顶部
        bindingView.recyclerView.setFocusable(false);
        if (isLoadBanner) {
            mHeaderBinding.banner.startAutoPlay();
        }
    }

    @Override
    protected void onInvisible() {
        if (mIsPrepared && isLoadBanner) {
            onPause();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // �?�?��?时轮播图�?�止滚动
        if (isLoadBanner) {
            mHeaderBinding.banner.stopAutoPlay();
        }
    }

    private void initAnimation() {
        bindingView.llLoading.setVisibility(View.VISIBLE);
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);//设置动画�?续时间
        animation.setInterpolator(new LinearInterpolator());//�?�?�顿
        animation.setRepeatMode(ValueAnimator.RESTART);//�?新从头执行
        animation.setRepeatCount(ValueAnimator.INFINITE);//设置�?�?次数
        bindingView.ivLoading.setAnimation(animation);
        animation.startNow();
    }

    /**
     * 显示旋转动画
     */
    private void showRotaLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.recyclerView.setVisibility(View.GONE);
            animation.startNow();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.recyclerView.setVisibility(View.VISIBLE);
            animation.cancel();
        }
    }

    @Override
    protected void onRefresh() {
        showContentView();
        showRotaLoading(true);
        viewModel.loadData();
    }

}
