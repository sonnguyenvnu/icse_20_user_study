package com.bigkoo.convenientbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.helper.CBLoopScaleHelper;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.CBPageChangeListener;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 页�?�翻转控件，�?方便的广告�?
 * 支�?无�?循环，自动翻页，翻页特效
 *
 * @author Sai 支�?自动翻页
 */
public class ConvenientBanner<T> extends RelativeLayout {
    private List<T> mDatas;
    private int[] page_indicatorId;
    private ArrayList<ImageView> mPointViews = new ArrayList<ImageView>();
    private CBPageAdapter pageAdapter;
    private CBLoopViewPager viewPager;
    private ViewGroup loPageTurningPoint;
    private long autoTurningTime = -1;
    private boolean turning;
    private boolean canTurn = false;
    private boolean canLoop = true;
    private CBLoopScaleHelper cbLoopScaleHelper;
    private CBPageChangeListener pageChangeListener;
    private OnPageChangeListener onPageChangeListener;
    private AdSwitchTask adSwitchTask;
    private boolean isVertical = false;

    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT, ALIGN_PARENT_RIGHT, CENTER_HORIZONTAL
    }

    public ConvenientBanner(Context context) {
        super(context);
        init(context);
    }

    public ConvenientBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ConvenientBanner);
        canLoop = a.getBoolean(R.styleable.ConvenientBanner_canLoop, true);
        autoTurningTime = a.getInteger(R.styleable.ConvenientBanner_autoTurningTime, -1);
        a.recycle();
        init(context);
    }

    private void init(Context context) {
        View hView = LayoutInflater.from(context).inflate(
                R.layout.include_viewpager, this, true);
        viewPager = (CBLoopViewPager)hView.findViewById(R.id.cbLoopViewPager);
        loPageTurningPoint = (ViewGroup)hView
                .findViewById(R.id.loPageTurningPoint);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        viewPager.setLayoutManager(linearLayoutManager);

        cbLoopScaleHelper = new CBLoopScaleHelper();

        adSwitchTask = new AdSwitchTask(this);
    }

    public ConvenientBanner setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        viewPager.setLayoutManager(layoutManager);
        return this;
    }
    public ConvenientBanner setPages(CBViewHolderCreator holderCreator, List<T> datas) {
        this.mDatas = datas;
        pageAdapter = new CBPageAdapter(holderCreator, mDatas, canLoop);
        viewPager.setAdapter(pageAdapter);

        if (page_indicatorId != null)
            setPageIndicator(page_indicatorId);

        cbLoopScaleHelper.setFirstItemPos(canLoop ? mDatas.size() : 0);
        cbLoopScaleHelper.attachToRecyclerView(viewPager);

        return this;
    }

    public ConvenientBanner setCanLoop(boolean canLoop){
        this.canLoop = canLoop;
        pageAdapter.setCanLoop(canLoop);
        notifyDataSetChanged();
        return this;
    }

    public boolean isCanLoop(){
        return canLoop;
    }




    /**
     * 通知数�?��?�化
     */
    public void notifyDataSetChanged() {
        viewPager.getAdapter().notifyDataSetChanged();
        if (page_indicatorId != null)
            setPageIndicator(page_indicatorId);
        cbLoopScaleHelper.setCurrentItem(canLoop ? mDatas.size() : 0);
    }

    /**
     * 设置底部指示器是�?��?��?
     *
     * @param visible
     */
    public ConvenientBanner setPointViewVisible(boolean visible) {
        loPageTurningPoint.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 底部指示器资�?图片
     *
     * @param page_indicatorId
     */
    public ConvenientBanner setPageIndicator(int[] page_indicatorId) {
        loPageTurningPoint.removeAllViews();
        mPointViews.clear();
        this.page_indicatorId = page_indicatorId;
        if (mDatas == null) return this;
        for (int count = 0; count < mDatas.size(); count++) {
            // 翻页指示的点
            ImageView pointView = new ImageView(getContext());
            pointView.setPadding(5, 0, 5, 0);
            if (cbLoopScaleHelper.getFirstItemPos()%mDatas.size()==count)
                pointView.setImageResource(page_indicatorId[1]);
            else
                pointView.setImageResource(page_indicatorId[0]);
            mPointViews.add(pointView);
            loPageTurningPoint.addView(pointView);
        }
        pageChangeListener = new CBPageChangeListener(mPointViews,
                page_indicatorId);
        cbLoopScaleHelper.setOnPageChangeListener(pageChangeListener);
        if (onPageChangeListener != null)
            pageChangeListener.setOnPageChangeListener(onPageChangeListener);

        return this;
    }

    public OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    /**
     * 设置翻页监�?�器
     *
     * @param onPageChangeListener
     * @return
     */
    public ConvenientBanner setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        //如果有默认的监�?�器（�?�是使用了默认的翻页指示器）则把用户设置的�?附到默认的上�?�，�?�则就直接设置
        if (pageChangeListener != null)
            pageChangeListener.setOnPageChangeListener(onPageChangeListener);
        else cbLoopScaleHelper.setOnPageChangeListener(onPageChangeListener);
        return this;
    }

    /**
     * 监�?�item点击
     *
     * @param onItemClickListener
     */
    public ConvenientBanner setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            pageAdapter.setOnItemClickListener(null);
            return this;
        }
        pageAdapter.setOnItemClickListener(onItemClickListener);
        return this;
    }

    /**
     * 获�?�当�?页对应的position
     * @return
     */
    public int getCurrentItem() {
        return cbLoopScaleHelper.getRealCurrentItem();
    }
    /**
     * 设置当�?页对应的position
     * @return
     */
    public ConvenientBanner setCurrentItem(int position, boolean smoothScroll) {
        cbLoopScaleHelper.setCurrentItem(canLoop ? mDatas.size()+position : position, smoothScroll);
        return this;
    }

    /**
     * 设置第一次加载当�?页对应的position
     * setPageIndicator之�?使用
     * @return
     */
    public ConvenientBanner setFirstItemPos(int position) {
        cbLoopScaleHelper.setFirstItemPos(canLoop ? mDatas.size()+position : position);
        return this;
    }
    /**
     * 指示器的方�?�
     *
     * @param align 三个方�?�：居左 （RelativeLayout.ALIGN_PARENT_LEFT），居中 （RelativeLayout.CENTER_HORIZONTAL），居�?� （RelativeLayout.ALIGN_PARENT_RIGHT）
     * @return
     */
    public ConvenientBanner setPageIndicatorAlign(PageIndicatorAlign align) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) loPageTurningPoint.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, align == PageIndicatorAlign.ALIGN_PARENT_LEFT ? RelativeLayout.TRUE : 0);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, align == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? RelativeLayout.TRUE : 0);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, align == PageIndicatorAlign.CENTER_HORIZONTAL ? RelativeLayout.TRUE : 0);
        loPageTurningPoint.setLayoutParams(layoutParams);
        return this;
    }

    /***
     * 是�?�开�?�了翻页
     * @return
     */
    public boolean isTurning() {
        return turning;
    }

    /***
     * 开始翻页
     * @param autoTurningTime 自动翻页时间
     * @return
     */
    public ConvenientBanner startTurning(long autoTurningTime) {
        if (autoTurningTime < 0) return this;
        //如果是正在翻页的�?先�?�掉
        if (turning) {
            stopTurning();
        }
        //设置�?�以翻页并开�?�翻页
        canTurn = true;
        this.autoTurningTime = autoTurningTime;
        turning = true;
        postDelayed(adSwitchTask, autoTurningTime);
        return this;
    }

    public ConvenientBanner startTurning() {
        startTurning(autoTurningTime);
        return this;
    }


    public void stopTurning() {
        turning = false;
        removeCallbacks(adSwitchTask);
    }

    //触碰控件的时候，翻页应该�?�止，离开的时候如果之�?是开�?�了翻页的�?则�?新�?�动翻页

    float startX , startY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE) {
            // 开始翻页
            if (canTurn) startTurning(autoTurningTime);
        } else if (action == MotionEvent.ACTION_DOWN) {
            // �?�止翻页
            if (canTurn) stopTurning();
        }

        return super.dispatchTouchEvent(ev);
    }

    static class AdSwitchTask implements Runnable {

        private final WeakReference<ConvenientBanner> reference;

        AdSwitchTask(ConvenientBanner convenientBanner) {
            this.reference = new WeakReference<ConvenientBanner>(convenientBanner);
        }

        @Override
        public void run() {
            ConvenientBanner convenientBanner = reference.get();

            if (convenientBanner != null) {
                if (convenientBanner.viewPager != null && convenientBanner.turning) {
                    int page = convenientBanner.cbLoopScaleHelper.getCurrentItem() + 1;
                    convenientBanner.cbLoopScaleHelper.setCurrentItem(page, true);
                    convenientBanner.postDelayed(convenientBanner.adSwitchTask, convenientBanner.autoTurningTime);
                }
            }
        }
    }

}
