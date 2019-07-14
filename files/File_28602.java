package com.bigkoo.convenientbanner.helper;

import android.view.View;
import android.view.ViewTreeObserver;

import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by jameson on 8/30/16.
 * changed by äºŒç²¾-éœ?é›ªæ¸…è™¹ on 2017/11/19
 * changed by Sai on 2018/04/25
 */
public class CBLoopScaleHelper {
    private CBLoopViewPager mRecyclerView;

    private int mPagePadding = 0; // å?¡ç‰‡çš„padding, å?¡ç‰‡é—´çš„è·?ç¦»ç­‰äºŽ2å€?çš„mPagePadding
    private int mShowLeftCardWidth = 0;   // å·¦è¾¹å?¡ç‰‡æ˜¾ç¤ºå¤§å°?

    private int mFirstItemPos;

    private PagerSnapHelper mPagerSnapHelper = new PagerSnapHelper();
    private OnPageChangeListener onPageChangeListener;

    public void attachToRecyclerView(final CBLoopViewPager mRecyclerView) {
        if (mRecyclerView == null) {
            return;
        }
        this.mRecyclerView = mRecyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = getCurrentItem();
                //è¿™é‡Œå?˜æ?¢ä½?ç½®å®žçŽ°å¾ªçŽ¯
                CBPageAdapter adapter = (CBPageAdapter) mRecyclerView.getAdapter();
                int count = adapter.getRealItemCount();
                if(adapter.isCanLoop()) {
                    if (position < count) {
                        position = count + position;
                        setCurrentItem(position);
                    } else if (position >= 2 * count) {
                        position = position - count;
                        setCurrentItem(position);
                    }
                }
                if(onPageChangeListener != null) {
                    onPageChangeListener.onScrollStateChanged(recyclerView, newState);
                    if(count != 0)
                        onPageChangeListener.onPageSelected(position % count);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //Log.e("TAG", String.format("onScrolled dx=%s, dy=%s", dx, dy));
                super.onScrolled(recyclerView, dx, dy);
                if(onPageChangeListener != null)
                    onPageChangeListener.onScrolled(recyclerView, dx, dy);
                onScrolledChangedCallback();
            }
        });
        initWidth();
        mPagerSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * åˆ?å§‹åŒ–å?¡ç‰‡å®½åº¦
     */
    private void initWidth() {
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                scrollToPosition(mFirstItemPos);
            }
        });
    }

    public void setCurrentItem(int item) {
        setCurrentItem(item,false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        if (mRecyclerView == null) {
            return;
        }
        if (smoothScroll) {
            mRecyclerView.smoothScrollToPosition(item);
        }else {
            scrollToPosition(item);
        }
    }

    public void scrollToPosition(int pos) {
        if (mRecyclerView == null) {
            return;
        }
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).
                scrollToPositionWithOffset(pos,
                        (mPagePadding + mShowLeftCardWidth));
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                onScrolledChangedCallback();
            }
        });
    }

    public void setFirstItemPos(int firstItemPos) {
        this.mFirstItemPos = firstItemPos;
    }


    /**
     * RecyclerViewä½?ç§»äº‹ä»¶ç›‘å?¬, viewå¤§å°?éš?ä½?ç§»äº‹ä»¶å?˜åŒ–
     */
    private void onScrolledChangedCallback() {

    }

    public int getCurrentItem() {
        try {
            RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
            View view = mPagerSnapHelper.findSnapView(layoutManager);
            if (view != null)
                return layoutManager.getPosition(view);
        }catch (NullPointerException e){e.printStackTrace();}
        return 0;
    }
    public int getRealCurrentItem() {
        CBPageAdapter adapter = (CBPageAdapter) mRecyclerView.getAdapter();
        int count = adapter.getRealItemCount();
        return getCurrentItem()%count;
    }

    public void setPagePadding(int pagePadding) {
        mPagePadding = pagePadding;
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        mShowLeftCardWidth = showLeftCardWidth;
    }

    public int getFirstItemPos() {
        return mFirstItemPos;
    }

    public int getRealItemCount(){
        CBPageAdapter adapter = (CBPageAdapter) mRecyclerView.getAdapter();
        int count = adapter.getRealItemCount();
        return count;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }
}
