/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.xui.widget.tabbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xuexiang.xui.R;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.ColorUtils;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.Utils;

import java.lang.String;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>用于横�?�多个 Tab 的布局，�?�以�?�活�?置 Tab</p>
 * <ul>
 * <li>�?�以用 xml 和 TabSegment �??供的 set 方法统一�?置文字颜色�?icon �?置�?是�?��?下划线等</li>
 * <li>�?个 Tab 都�?�以�?�常�?�活的�?置，如果没有�??供相关�?置，则使用 TabSegment �??供的�?置，具体�?�考 {@link Tab}</li>
 * <li>�?�以通过 {@link #setupWithViewPager(ViewPager)} 与 {@link ViewPager} 绑定</li>
 * </ul>
 * <p>
 * <h3>使用case: </h3>
 * <ul>
 * <li>
 * 如果 {@link ViewPager} 的 {@link PagerAdapter} 有覆写 {@link PagerAdapter#getPageTitle(int)} 方法, 那么直接使用 {@link #setupWithViewPager(ViewPager)} 方法与 {@link ViewPager} 绑定�?��?�。
 * TabSegment 会将 {@link PagerAdapter#getPageTitle(int)} 返回的字符串作为 Tab 的文案
 * </li>
 * <li>
 * 如果你希望自己设置 Tab 的文案或图片，那么通过{@link #addTab(Tab)}添加 Tab:
 * <code>
 * TabSegment mTabSegment = new TabSegment((getContext());
 * // config mTabSegment
 * mTabSegment.addTab(new Tab("item 1"));
 * mTabSegment.addTab(new Tab("item 2"));
 * mTabSegment.setupWithViewPager(viewpager, false); //第二个�?�数�?为false,表示�?从adapter拿数�?�
 * </code>
 * </li>
 * <li>
 * 如果你想更改tab,则调用{@link #updateTabText(int, String)} 或者 {@link #replaceTab(int, Tab)}
 * <code>
 * mTabSegment.updateTabText(1, "update item content");
 * mTabSegment.replaceTab(1, new Tab("replace item"));
 * </code>
 * </li>
 * <li>
 * 如果你想更�?�全部Tab,需�?在addTab�?调用{@link #reset()}进行�?置，addTab�?�调用{@link #notifyDataChanged()} 将数�?�应用到View上：
 * <code>
 * mTabSegment.reset();
 * // update mTabSegment with new config
 * mTabSegment.addTab(new Tab("new item 1"));
 * mTabSegment.addTab(new Tab("new item 2"));
 * mTabSegment.notifyDataChanged();
 * </code>
 * </li>
 * </ul>
 *
 * @author xuexiang
 * @since 2018/12/26 下�?�4:51
 */
public class TabSegment extends HorizontalScrollView {

    private static final String TAG = "TabSegment";

    // mode: 自适应宽度+滚动 / �?�分
    public static final int MODE_SCROLLABLE = 0;
    public static final int MODE_FIXED = 1;
    // icon position
    public static final int ICON_POSITION_LEFT = 0;
    public static final int ICON_POSITION_TOP = 1;
    public static final int ICON_POSITION_RIGHT = 2;
    public static final int ICON_POSITION_BOTTOM = 3;

    private static final int NO_POSITION = -1;
    /**
     * listener
     */
    private final ArrayList<OnTabSelectedListener> mSelectedListeners = new ArrayList<>();
    private Container mContentLayout;

    private int mCurrentSelectedIndex = NO_POSITION;
    private int mPendingSelectedIndex = NO_POSITION;

    /**
     * item的默认字体大�?
     */
    private int mTabTextSize;
    /**
     * 是�?�有Indicator
     */
    private boolean mHasIndicator = true;
    /**
     * Indicator高度
     */
    private int mIndicatorHeight;
    /**
     * indicator在顶部
     */
    private boolean mIndicatorTop = false;
    /**
     * indicator采用drawable
     */
    private Drawable mIndicatorDrawable;
    /**
     * indicator宽度跟�?内容宽度
     */
    private boolean mIsIndicatorWidthFollowContent = true;

    /**
     * indicator rect, draw directly
     */
    private Rect mIndicatorRect = null;

    /**
     * indicator paint, draw directly
     */
    private Paint mIndicatorPaint = null;

    /**
     * item normal color
     */
    private int mNormalColor;
    /**
     * item selected color
     */
    private int mSelectedColor;
    /**
     * item icon的默认�?置
     */
    @IconPosition
    private int mDefaultTabIconPosition;
    /**
     * TabSegmentMode
     */
    @Mode
    private int mMode = MODE_FIXED;
    /**
     * ScrollMode下item的间隙
     */
    private int mItemSpaceInScrollMode;
    /**
     * typeface
     */
    private TypefaceProvider mTypefaceProvider;

    /**
     * 记录 ViewPager 的 scrollState
     */
    private int mViewPagerScrollState = ViewPager.SCROLL_STATE_IDLE;

    private Animator mSelectAnimator;
    private OnTabClickListener mOnTabClickListener;
    protected OnClickListener mTabOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mSelectAnimator != null || mViewPagerScrollState != ViewPager.SCROLL_STATE_IDLE) {
                return;
            }
            int index = (int) v.getTag();
            Tab model = getAdapter().getItem(index);
            if (model != null) {
                selectTab(index, !mHasIndicator && !model.isDynamicChangeIconColor(), true);
            }
            if (mOnTabClickListener != null) {
                mOnTabClickListener.onTabClick(index);
            }
        }
    };
    /**
     * 与ViewPager的�??�?�工作
     */
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private OnTabSelectedListener mViewPagerSelectedListener;
    private AdapterChangeListener mAdapterChangeListener;
    private boolean mIsInSelectTab = false;

    public TabSegment(Context context) {
        this(context, null);
    }

    public TabSegment(Context context, boolean hasIndicator) {
        this(context, null);
        mHasIndicator = hasIndicator;
    }

    public TabSegment(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.TabSegmentStyle);
    }

    public TabSegment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(false);
        setClipToPadding(false);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        String typefaceProviderName;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabSegment, defStyleAttr, 0);
        mSelectedColor = array.getColor(R.styleable.TabSegment_ts_selected_color, ThemeUtils.resolveColor(context, R.attr.colorAccent));
        mNormalColor = array.getColor(R.styleable.TabSegment_ts_normal_color, ContextCompat.getColor(context, R.color.xui_config_color_gray_5));
        mHasIndicator = array.getBoolean(R.styleable.TabSegment_ts_has_indicator, true);
        mIndicatorHeight = array.getDimensionPixelSize(R.styleable.TabSegment_ts_indicator_height, getResources().getDimensionPixelSize(R.dimen.xui_tab_segment_indicator_height));
        mTabTextSize = array.getDimensionPixelSize(R.styleable.TabSegment_android_textSize, getResources().getDimensionPixelSize(R.dimen.xui_tab_segment_text_size));
        mIndicatorTop = array.getBoolean(R.styleable.TabSegment_ts_indicator_top, false);
        mDefaultTabIconPosition = array.getInt(R.styleable.TabSegment_ts_icon_position, ICON_POSITION_LEFT);
        mMode = array.getInt(R.styleable.TabSegment_ts_mode, MODE_FIXED);
        mItemSpaceInScrollMode = array.getDimensionPixelSize(R.styleable.TabSegment_ts_space, DensityUtils.dp2px(context, 10));
        typefaceProviderName = array.getString(R.styleable.TabSegment_ts_typeface_provider);
        array.recycle();

        mContentLayout = new Container(context);
        addView(mContentLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        createTypefaceProvider(context, typefaceProviderName);
    }

    private void createTypefaceProvider(Context context, String className) {
        if (Utils.isNullOrEmpty(className)) {
            return;
        }
        className = className.trim();
        if (className.length() == 0) {
            return;
        }
        className = getFullClassName(context, className);
        //noinspection TryWithIdenticalCatches
        try {
            ClassLoader classLoader;
            if (isInEditMode()) {
                classLoader = this.getClass().getClassLoader();
            } else {
                classLoader = context.getClassLoader();
            }
            Class<? extends TypefaceProvider> providerClass =
                    classLoader.loadClass(className).asSubclass(TypefaceProvider.class);
            Constructor<? extends TypefaceProvider> constructor;
            try {
                constructor = providerClass.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Error creating TypefaceProvider " + className, e);
            }
            constructor.setAccessible(true);
            mTypefaceProvider = constructor.newInstance();
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find TypefaceProvider " + className, e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Could not instantiate the TypefaceProvider: " + className, e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Could not instantiate the TypefaceProvider: " + className, e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot access non-public constructor " + className, e);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Class is not a TypefaceProvider " + className, e);
        }
    }

    private String getFullClassName(Context context, String className) {
        if (className.charAt(0) == '.') {
            return context.getPackageName() + className;
        }
        return className;
    }

    public void setTypefaceProvider(TypefaceProvider typefaceProvider) {
        mTypefaceProvider = typefaceProvider;
    }

    public TabSegment addTab(Tab item) {
        mContentLayout.getTabAdapter().addItem(item);
        return this;
    }

    private TabAdapter getAdapter() {
        return mContentLayout.getTabAdapter();
    }

    public void setTabTextSize(int tabTextSize) {
        mTabTextSize = tabTextSize;
    }

    /**
     * 清空已�?存在的 Tab。
     * 一般先调用本方法清空已加上的 Tab, 然�?��?新 {@link #addTab(Tab)} 添加新的 Tab, 然�?�通过 {@link #notifyDataChanged()} 通知�?�动
     */
    public void reset() {
        mContentLayout.getTabAdapter().clear();
        mCurrentSelectedIndex = NO_POSITION;
        if (mSelectAnimator != null) {
            mSelectAnimator.cancel();
            mSelectAnimator = null;
        }
    }

    /**
     * 通知 TabSegment 数�?��?�动。
     * 一般先调用 {@link #reset()} 清空已加上的 Tab, 然�?��?新 {@link #addTab(Tab)} 添加新的 Tab, 然�?�通过本方法通知�?�动
     */
    public void notifyDataChanged() {
        getAdapter().setup();
        populateFromPagerAdapter(false);
    }

    public void addOnTabSelectedListener(@NonNull OnTabSelectedListener listener) {
        if (!mSelectedListeners.contains(listener)) {
            mSelectedListeners.add(listener);
        }
    }

    public void setItemSpaceInScrollMode(int itemSpaceInScrollMode) {
        mItemSpaceInScrollMode = itemSpaceInScrollMode;
    }

    /**
     * 设置 indicator 为自定义的 Drawable(默认跟�? Tab 的 selectedColor)
     */
    public void setIndicatorDrawable(Drawable indicatorDrawable) {
        mIndicatorDrawable = indicatorDrawable;
        if (indicatorDrawable != null) {
            mIndicatorHeight = indicatorDrawable.getIntrinsicHeight();
        }
        mContentLayout.invalidate();
    }

    /**
     * 设置 indicator的宽度是�?��?内容宽度�?�化
     */
    public void setIndicatorWidthAdjustContent(boolean indicatorWidthFollowContent) {
        if (mIsIndicatorWidthFollowContent != indicatorWidthFollowContent) {
            mIsIndicatorWidthFollowContent = indicatorWidthFollowContent;
            mContentLayout.requestLayout();
        }
    }

    /**
     * 设置 indicator 的�?置
     *
     * @param isIndicatorTop true 时表示 indicator �?置在 Tab 的上方, false 时表示在下方
     */
    public void setIndicatorPosition(boolean isIndicatorTop) {
        if (mIndicatorTop != isIndicatorTop) {
            mIndicatorTop = isIndicatorTop;
            mContentLayout.invalidate();
        }
    }

    /**
     * 设置是�?�需�?显示 indicator
     *
     * @param hasIndicator 是�?�需�?显示 indicator
     */
    public void setHasIndicator(boolean hasIndicator) {
        if (mHasIndicator != hasIndicator) {
            mHasIndicator = hasIndicator;
            invalidate();
        }

    }

    public int getMode() {
        return mMode;
    }

    public void setMode(@Mode int mode) {
        if (mMode != mode) {
            mMode = mode;
            mContentLayout.invalidate();
        }
    }

    public void removeOnTabSelectedListener(@NonNull OnTabSelectedListener listener) {
        mSelectedListeners.remove(listener);
    }

    public void clearOnTabSelectedListeners() {
        mSelectedListeners.clear();
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean useAdapterTitle) {
        setupWithViewPager(viewPager, useAdapterTitle, true);
    }

    /**
     * @param viewPager       需�?关�?�的 ViewPager。
     * @param useAdapterTitle 自动根�?�ViewPager的adapter.getTitle�?�值。
     * @param autoRefresh     adapter有更改时，刷新TabSegment。
     */
    public void setupWithViewPager(@Nullable final ViewPager viewPager, boolean useAdapterTitle, boolean autoRefresh) {
        if (mViewPager != null) {
            // If we've already been setup with a ViewPager, remove us from it
            if (mOnPageChangeListener != null) {
                mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
            }

            if (mAdapterChangeListener != null) {
                mViewPager.removeOnAdapterChangeListener(mAdapterChangeListener);
            }
        }

        if (mViewPagerSelectedListener != null) {
            // If we already have a tab selected listener for the ViewPager, remove it
            removeOnTabSelectedListener(mViewPagerSelectedListener);
            mViewPagerSelectedListener = null;
        }

        if (viewPager != null) {
            mViewPager = viewPager;

            // Add our custom OnPageChangeListener to the ViewPager
            if (mOnPageChangeListener == null) {
                mOnPageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            viewPager.addOnPageChangeListener(mOnPageChangeListener);

            // Now we'll add a tab selected listener to set ViewPager's current item
            mViewPagerSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            addOnTabSelectedListener(mViewPagerSelectedListener);

            final PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                // Now we'll populate ourselves from the pager adapter, adding an observer if
                // autoRefresh is enabled
                setPagerAdapter(adapter, useAdapterTitle, autoRefresh);
            }

            // Add a listener so that we're notified of any adapter changes
            if (mAdapterChangeListener == null) {
                mAdapterChangeListener = new AdapterChangeListener(useAdapterTitle);
            }
            mAdapterChangeListener.setAutoRefresh(autoRefresh);
            viewPager.addOnAdapterChangeListener(mAdapterChangeListener);
        } else {
            // We've been given a null ViewPager so we need to clear out the internal state,
            // listeners and observers
            mViewPager = null;
            setPagerAdapter(null, false, false);
        }
    }

    private void dispatchTabSelected(int index) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabSelected(index);
        }
    }

    private void dispatchTabUnselected(int index) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabUnselected(index);
        }
    }

    private void dispatchTabReselected(int index) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabReselected(index);
        }
    }

    private void dispatchTabDoubleTap(int index) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onDoubleTap(index);
        }
    }

    /**
     * 设置 Tab 正常状�?下的颜色
     */
    public void setDefaultNormalColor(@ColorInt int defaultNormalColor) {
        mNormalColor = defaultNormalColor;
    }

    /**
     * 设置 Tab 选中状�?下的颜色
     */
    public void setDefaultSelectedColor(@ColorInt int defaultSelectedColor) {
        mSelectedColor = defaultSelectedColor;
    }

    /**
     * @param defaultTabIconPosition
     */
    public void setDefaultTabIconPosition(@IconPosition int defaultTabIconPosition) {
        mDefaultTabIconPosition = defaultTabIconPosition;
    }


    private void setViewPagerScrollState(int state) {
        mViewPagerScrollState = state;
        if (mViewPagerScrollState == ViewPager.SCROLL_STATE_IDLE) {
            if (mPendingSelectedIndex != NO_POSITION && mSelectAnimator == null) {
                selectTab(mPendingSelectedIndex, true, false);
                mPendingSelectedIndex = NO_POSITION;
            }
        }
    }

    public void selectTab(int index) {
        selectTab(index, false, false);
    }

    public void selectTab(final int index, boolean noAnimation, boolean fromTabClick) {
        if (mIsInSelectTab) {
            return;
        }
        mIsInSelectTab = true;
        TabAdapter tabAdapter = getAdapter();
        List<TabItemView> listViews = tabAdapter.getViews();

        if (listViews.size() != tabAdapter.getSize()) {
            tabAdapter.setup();
            listViews = tabAdapter.getViews();
        }

        if (listViews.size() == 0 || listViews.size() <= index) {
            mIsInSelectTab = false;
            return;
        }

        if (mSelectAnimator != null || mViewPagerScrollState != ViewPager.SCROLL_STATE_IDLE) {
            mPendingSelectedIndex = index;
            mIsInSelectTab = false;
            return;
        }

        if (mCurrentSelectedIndex == index) {
            if (fromTabClick) {
                // dispatch re select only when click tab
                dispatchTabReselected(index);
            }
            mIsInSelectTab = false;
            // invalidate mContentLayout to sure indicator is drawn if needed
            mContentLayout.invalidate();
            return;
        }


        if (mCurrentSelectedIndex > listViews.size()) {
            Log.i(TAG, "selectTab: current selected index is bigger than views size.");
            mCurrentSelectedIndex = NO_POSITION;
        }

        // first time to select
        if (mCurrentSelectedIndex == NO_POSITION) {
            Tab model = tabAdapter.getItem(index);
            layoutIndicator(model, true);
            TextView selectedTv = listViews.get(index).getTextView();
            setTextViewTypeface(selectedTv, true);
            listViews.get(index).updateDecoration(model, true);
            dispatchTabSelected(index);
            mCurrentSelectedIndex = index;
            mIsInSelectTab = false;
            return;
        }

        final int prev = mCurrentSelectedIndex;
        final Tab prevModel = tabAdapter.getItem(prev);
        final TabItemView prevView = listViews.get(prev);
        final Tab nowModel = tabAdapter.getItem(index);
        final TabItemView nowView = listViews.get(index);

        if (noAnimation) {
            dispatchTabUnselected(prev);
            dispatchTabSelected(index);
            setTextViewTypeface(prevView.getTextView(), false);
            setTextViewTypeface(nowView.getTextView(), true);
            prevView.updateDecoration(prevModel, false);
            nowView.updateDecoration(nowModel, true);
            if (getScrollX() > nowView.getLeft()) {
                smoothScrollTo(nowView.getLeft(), 0);
            } else {
                int realWidth = getWidth() - getPaddingRight() - getPaddingLeft();
                if (getScrollX() + realWidth < nowView.getRight()) {
                    smoothScrollBy(nowView.getRight() - realWidth - getScrollX(), 0);
                }
            }
            mCurrentSelectedIndex = index;
            mIsInSelectTab = false;
            layoutIndicator(nowModel, true);
            return;
        }

        final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animValue = (float) animation.getAnimatedValue();
                int preColor = ColorUtils.computeColor(getTabSelectedColor(prevModel), getTabNormalColor(prevModel), animValue);
                int nowColor = ColorUtils.computeColor(getTabNormalColor(nowModel), getTabSelectedColor(nowModel), animValue);
                prevView.setColorInTransition(prevModel, preColor);
                nowView.setColorInTransition(nowModel, nowColor);
                layoutIndicatorInTransition(prevModel, nowModel, animValue);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mSelectAnimator = animation;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mSelectAnimator = null;
                prevView.updateDecoration(prevModel, false);
                nowView.updateDecoration(nowModel, true);
                dispatchTabSelected(index);
                dispatchTabUnselected(prev);
                setTextViewTypeface(prevView.getTextView(), false);
                setTextViewTypeface(nowView.getTextView(), true);
                mCurrentSelectedIndex = index;
                if (mPendingSelectedIndex != NO_POSITION && mViewPagerScrollState == ViewPager.SCROLL_STATE_IDLE) {
                    selectTab(mPendingSelectedIndex, true, false);
                    mPendingSelectedIndex = NO_POSITION;
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mSelectAnimator = null;
                prevView.updateDecoration(prevModel, true);
                nowView.updateDecoration(nowModel, false);
                layoutIndicator(prevModel, true);

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(200);
        animator.start();
        mIsInSelectTab = false;
    }

    private void layoutIndicator(Tab model, boolean invalidate) {
        if (model == null) {
            return;
        }
        if (mIndicatorRect == null) {
            mIndicatorRect = new Rect(model.contentLeft, 0,
                    model.contentLeft + model.contentWidth, 0);
        } else {
            mIndicatorRect.left = model.contentLeft;
            mIndicatorRect.right = model.contentLeft + model.contentWidth;
        }

        if (mIndicatorPaint == null) {
            mIndicatorPaint = new Paint();
            mIndicatorPaint.setStyle(Paint.Style.FILL);
        }
        mIndicatorPaint.setColor(getTabSelectedColor(model));
        if (invalidate) {
            mContentLayout.invalidate();
        }
    }

    private void layoutIndicatorInTransition(Tab preModel, Tab targetModel, float offsetPercent) {
        final int leftDistance = targetModel.getContentLeft() - preModel.getContentLeft();
        final int widthDistance = targetModel.getContentWidth() - preModel.getContentWidth();
        final int targetLeft = (int) (preModel.getContentLeft() + leftDistance * offsetPercent);
        final int targetWidth = (int) (preModel.getContentWidth() + widthDistance * offsetPercent);
        if (mIndicatorRect == null) {
            mIndicatorRect = new Rect(targetLeft, 0, targetLeft + targetWidth, 0);
        } else {
            mIndicatorRect.left = targetLeft;
            mIndicatorRect.right = targetLeft + targetWidth;
        }

        if (mIndicatorPaint == null) {
            mIndicatorPaint = new Paint();
            mIndicatorPaint.setStyle(Paint.Style.FILL);
        }
        int indicatorColor = ColorUtils.computeColor(
                getTabSelectedColor(preModel), getTabSelectedColor(targetModel), offsetPercent);
        mIndicatorPaint.setColor(indicatorColor);
        mContentLayout.invalidate();
    }


    private void setTextViewTypeface(TextView tv, boolean selected) {
        if (mTypefaceProvider == null || tv == null) {
            return;
        }
        boolean isBold = selected ? mTypefaceProvider.isSelectedTabBold() : mTypefaceProvider.isNormalTabBold();
        tv.setTypeface(mTypefaceProvider.getTypeface(), isBold ? Typeface.BOLD : Typeface.NORMAL);
    }

    public void updateIndicatorPosition(final int index, float offsetPercent) {
        if (mSelectAnimator != null || mIsInSelectTab || offsetPercent == 0) {
            return;
        }

        int targetIndex;
        if (offsetPercent < 0) {
            targetIndex = index - 1;
            offsetPercent = -offsetPercent;
        } else {
            targetIndex = index + 1;
        }

        TabAdapter tabAdapter = getAdapter();
        final List<TabItemView> listViews = tabAdapter.getViews();
        if (listViews.size() <= index || listViews.size() <= targetIndex) {
            return;
        }
        Tab preModel = tabAdapter.getItem(index);
        Tab targetModel = tabAdapter.getItem(targetIndex);
        TabItemView preView = listViews.get(index);
        TabItemView targetView = listViews.get(targetIndex);
        int preColor = ColorUtils.computeColor(getTabSelectedColor(preModel), getTabNormalColor(preModel), offsetPercent);
        int targetColor = ColorUtils.computeColor(getTabNormalColor(targetModel), getTabSelectedColor(targetModel), offsetPercent);
        preView.setColorInTransition(preModel, preColor);
        targetView.setColorInTransition(targetModel, targetColor);
        layoutIndicatorInTransition(preModel, targetModel, offsetPercent);
    }

    /**
     * 改�?� Tab 的文案
     *
     * @param index Tab 的 index
     * @param text  新文案
     */
    public void updateTabText(int index, String text) {
        Tab model = getAdapter().getItem(index);
        if (model == null) {
            return;
        }
        model.setText(text);
        notifyDataChanged();
    }

    /**
     * 整个 Tab 替�?�
     *
     * @param index 需�?被替�?�的 Tab 的 index
     * @param model 新的 Tab
     */
    public void replaceTab(int index, Tab model) {
        try {
            getAdapter().replaceItem(index, model);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    private void setDrawable(TextView tv, Drawable drawable, int iconPosition) {
        tv.setCompoundDrawables(
                iconPosition == ICON_POSITION_LEFT ? drawable : null,
                iconPosition == ICON_POSITION_TOP ? drawable : null,
                iconPosition == ICON_POSITION_RIGHT ? drawable : null,
                iconPosition == ICON_POSITION_BOTTOM ? drawable : null);
    }

    private int getTabNormalColor(Tab item) {
        int color = item.getNormalColor();
        if (color == Tab.USE_TAB_SEGMENT) {
            color = mNormalColor;
        }
        return color;
    }

    private int getTabIconPosition(Tab item) {
        int iconPosition = item.getIconPosition();
        if (iconPosition == Tab.USE_TAB_SEGMENT) {
            iconPosition = mDefaultTabIconPosition;
        }
        return iconPosition;
    }

    private int getTabTextSize(Tab item) {
        int textSize = item.getTextSize();
        if (textSize == Tab.USE_TAB_SEGMENT) {
            textSize = mTabTextSize;
        }
        return textSize;
    }

    private int getTabSelectedColor(Tab item) {
        int color = item.getSelectedColor();
        if (color == Tab.USE_TAB_SEGMENT) {
            color = mSelectedColor;
        }
        return color;
    }

    void populateFromPagerAdapter(boolean useAdapterTitle) {
        if (mPagerAdapter == null) {
            if (useAdapterTitle) {
                reset();
            }
            return;
        }
        final int adapterCount = mPagerAdapter.getCount();
        if (useAdapterTitle) {
            reset();
            for (int i = 0; i < adapterCount; i++) {
                addTab(new Tab(mPagerAdapter.getPageTitle(i)));
            }
            notifyDataChanged();
        }

        if (mViewPager != null && adapterCount > 0) {
            final int curItem = mViewPager.getCurrentItem();
            selectTab(curItem, true, false);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (getChildCount() > 0) {
            final View child = getChildAt(0);
            int paddingHor = getPaddingLeft() + getPaddingRight();
            child.measure(MeasureSpec.makeMeasureSpec(widthSize - paddingHor, MeasureSpec.EXACTLY), heightMeasureSpec);
            if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(Math.min(widthSize, child.getMeasuredWidth() + paddingHor), heightMeasureSpec);
                return;
            }
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    void setPagerAdapter(@Nullable final PagerAdapter adapter, boolean useAdapterTitle, final boolean addObserver) {
        if (mPagerAdapter != null && mPagerAdapterObserver != null) {
            // If we already have a PagerAdapter, unregister our observer
            mPagerAdapter.unregisterDataSetObserver(mPagerAdapterObserver);
        }

        mPagerAdapter = adapter;

        if (addObserver && adapter != null) {
            // Register our observer on the new adapter
            if (mPagerAdapterObserver == null) {
                mPagerAdapterObserver = new PagerAdapterObserver(useAdapterTitle);
            }
            adapter.registerDataSetObserver(mPagerAdapterObserver);
        }

        // Finally make sure we reflect the new adapter
        populateFromPagerAdapter(useAdapterTitle);
    }

    public int getSelectedIndex() {
        return mCurrentSelectedIndex;
    }

    private int getTabCount() {
        return getAdapter().getSize();
    }

    /**
     * 根�?� index 获�?�对应下标的 {@link Tab} 对象
     *
     * @return index 下标对应的 {@link Tab} 对象
     */
    public Tab getTab(int index) {
        return getAdapter().getItem(index);
    }

    /**
     * 根�?� index 在对应的 Tab 上显示未读数或红点
     *
     * @param index �?显示未读数或红点的 Tab 的下标
     * @param count �?为0时红点会显示该数字作为未读数,为0时�?�会显示一个�?红点
     */
    public void showSignCountView(Context context, int index, int count) {
        Tab tab = getAdapter().getItem(index);
        tab.showSignCountView(context, count);
        notifyDataChanged();
    }

    /**
     * 根�?� index 在对应的 Tab 上�?�?红点
     */
    public void hideSignCountView(int index) {
        Tab tab = getAdapter().getItem(index);
        tab.hideSignCountView();
    }

    /**
     * 获�?�当�?的红点数值，如果没有红点则返回 0
     */
    public int getSignCount(int index) {
        Tab tab = getAdapter().getItem(index);
        return tab.getSignCount();
    }

    @IntDef(value = {MODE_SCROLLABLE, MODE_FIXED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    @IntDef(value = {ICON_POSITION_LEFT, ICON_POSITION_TOP, ICON_POSITION_RIGHT, ICON_POSITION_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconPosition {
    }

    public interface OnTabClickListener {
        /**
         * 当�?个 Tab 被点击时会触�?�
         *
         * @param index 被点击的 Tab 下标
         */
        void onTabClick(int index);
    }

    public interface OnTabSelectedListener {
        /**
         * 当�?个 Tab 被选中时会触�?�
         *
         * @param index 被选中的 Tab 下标
         */
        void onTabSelected(int index);

        /**
         * 当�?个 Tab 被�?�消选中时会触�?�
         *
         * @param index 被�?�消选中的 Tab 下标
         */
        void onTabUnselected(int index);

        /**
         * 当�?个 Tab 处于被选中状�?下�?次被点击时会触�?�
         *
         * @param index 被�?次点击的 Tab 下标
         */
        void onTabReselected(int index);

        /**
         * 当�?个 Tab 被�?�击时会触�?�
         *
         * @param index 被�?�击的 Tab 下标
         */
        void onDoubleTap(int index);
    }

    public interface TypefaceProvider {

        boolean isNormalTabBold();

        boolean isSelectedTabBold();

        @Nullable
        Typeface getTypeface();
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final WeakReference<TabSegment> mTabSegmentRef;

        public TabLayoutOnPageChangeListener(TabSegment tabSegment) {
            mTabSegmentRef = new WeakReference<>(tabSegment);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
            final TabSegment tabSegment = mTabSegmentRef.get();
            if (tabSegment != null) {
                tabSegment.setViewPagerScrollState(state);
            }

        }

        @Override
        public void onPageScrolled(final int position, final float positionOffset,
                                   final int positionOffsetPixels) {
            final TabSegment tabSegment = mTabSegmentRef.get();
            if (tabSegment != null) {
                tabSegment.updateIndicatorPosition(position, positionOffset);
            }
        }

        @Override
        public void onPageSelected(final int position) {
            final TabSegment tabSegment = mTabSegmentRef.get();
            if (tabSegment != null && tabSegment.getSelectedIndex() != position
                    && position < tabSegment.getTabCount()) {
                tabSegment.selectTab(position, true, false);
            }
        }
    }

    private static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager mViewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            mViewPager = viewPager;
        }

        @Override
        public void onTabSelected(int index) {
            mViewPager.setCurrentItem(index, false);
        }

        @Override
        public void onTabUnselected(int index) {
        }

        @Override
        public void onTabReselected(int index) {
        }

        @Override
        public void onDoubleTap(int index) {

        }
    }

    public static class Tab {
        public static final int USE_TAB_SEGMENT = Integer.MIN_VALUE;
        private int textSize = USE_TAB_SEGMENT;
        private int normalColor = USE_TAB_SEGMENT;
        private int selectedColor = USE_TAB_SEGMENT;
        private Drawable normalIcon = null;
        private Drawable selectedIcon = null;
        private int contentWidth = 0;
        private int contentLeft = 0;
        private int iconPosition = USE_TAB_SEGMENT;
        private int gravity = Gravity.CENTER;
        private CharSequence text;
        private List<View> mCustomViews;
        private int mSignCountDigits = 2;
        private TextView mSignCountTextView;
        private int mSignCountMarginLeft = 0;
        private int mSignCountMarginTop = 0;
        /**
         * 是�?�动�?更改icon颜色，如果为true, selectedIcon将失效
         */
        private boolean dynamicChangeIconColor = true;

        public Tab(CharSequence text) {
            this.text = text;
        }


        public Tab(Drawable normalIcon, Drawable selectedIcon, CharSequence text, boolean dynamicChangeIconColor) {
            this(normalIcon, selectedIcon, text, dynamicChangeIconColor, true);
        }

        /**
         * 如果你的 icon 显示大�?和实际大�?�?�?��?�:
         * 1. 设置icon 的 bounds
         * 2. 使用此构造器
         * 3. 最�?�一个�?�数（setIntrinsicSize）设置为false
         *
         * @param normalIcon             未选中�? icon
         * @param selectedIcon           选中�? icon
         * @param text                   文字
         * @param dynamicChangeIconColor 是�?�动�?改�?� icon 颜色
         * @param setIntrinsicSize       是�?�设置 icon 的大�?为 intrinsic width 和 intrinsic height。
         */
        public Tab(Drawable normalIcon, Drawable selectedIcon, CharSequence text, boolean dynamicChangeIconColor, boolean setIntrinsicSize) {
            this.normalIcon = normalIcon;
            if (this.normalIcon != null && setIntrinsicSize) {
                this.normalIcon.setBounds(0, 0, normalIcon.getIntrinsicWidth(), normalIcon.getIntrinsicHeight());
            }
            this.selectedIcon = selectedIcon;
            if (this.selectedIcon != null && setIntrinsicSize) {
                this.selectedIcon.setBounds(0, 0, selectedIcon.getIntrinsicWidth(), selectedIcon.getIntrinsicHeight());
            }
            this.text = text;
            this.dynamicChangeIconColor = dynamicChangeIconColor;
        }

        /**
         * 设置红点中数字显示的最大�?数，默认值为 2，超过这个�?数以 99+ 这�?形�?显示。如：110 -> 99+，98 -> 98
         *
         * @param digit 数字显示的最大�?数
         */
        public void setmSignCountDigits(int digit) {
            mSignCountDigits = digit;
        }

        public void setTextColor(@ColorInt int normalColor, @ColorInt int selectedColor) {
            this.normalColor = normalColor;
            this.selectedColor = selectedColor;
        }

        public int getTextSize() {
            return textSize;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public CharSequence getText() {
            return text;
        }

        public void setText(CharSequence text) {
            this.text = text;
        }

        public int getContentLeft() {
            return contentLeft;
        }

        public void setContentLeft(int contentLeft) {
            this.contentLeft = contentLeft;
        }

        public int getContentWidth() {
            return contentWidth;
        }

        public void setContentWidth(int contentWidth) {
            this.contentWidth = contentWidth;
        }

        public int getIconPosition() {
            return iconPosition;
        }

        public void setIconPosition(int iconPosition) {
            this.iconPosition = iconPosition;
        }

        public int getGravity() {
            return gravity;
        }

        public void setGravity(int gravity) {
            this.gravity = gravity;
        }

        public int getNormalColor() {
            return normalColor;
        }

        public Drawable getNormalIcon() {
            return normalIcon;
        }

        public int getSelectedColor() {
            return selectedColor;
        }

        public Drawable getSelectedIcon() {
            return selectedIcon;
        }

        public boolean isDynamicChangeIconColor() {
            return dynamicChangeIconColor;
        }

        public void addCustomView(@NonNull View view) {
            if (mCustomViews == null) {
                mCustomViews = new ArrayList<>();
            }
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(getDefaultCustomLayoutParam());
            }
            mCustomViews.add(view);
        }

        public List<View> getCustomViews() {
            return mCustomViews;
        }

        /**
         * 设置红点的�?置, 注�?红点的默认�?置是在内容的�?�侧并顶对�?
         *
         * @param marginLeft 在红点默认�?置的基础上添加的 marginLeft
         * @param marginTop  在红点默认�?置的基础上添加的 marginTop
         */
        public void setSignCountMargin(int marginLeft, int marginTop) {
            mSignCountMarginLeft = marginLeft;
            mSignCountMarginTop = marginTop;
            if (mSignCountTextView != null && mSignCountTextView.getLayoutParams() != null) {
                ((MarginLayoutParams) mSignCountTextView.getLayoutParams()).leftMargin = marginLeft;
                ((MarginLayoutParams) mSignCountTextView.getLayoutParams()).topMargin = marginTop;
            }
        }

        private TextView ensureSignCountView(Context context) {
            if (mSignCountTextView == null) {
                mSignCountTextView = new TextView(context, null, R.attr.xui_tab_sign_count_view);
                RelativeLayout.LayoutParams signCountLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ThemeUtils.resolveDimension(context, R.attr.xui_tab_sign_count_view_minSize));
                signCountLp.addRule(RelativeLayout.ALIGN_TOP, R.id.xui_tab_segment_item_id);
                signCountLp.addRule(RelativeLayout.RIGHT_OF, R.id.xui_tab_segment_item_id);
                mSignCountTextView.setLayoutParams(signCountLp);
                addCustomView(mSignCountTextView);
            }
            // 确�?在先 setMargin �?� create 的情况下 margin 会生效
            setSignCountMargin(mSignCountMarginLeft, mSignCountMarginTop);
            return mSignCountTextView;
        }

        /**
         * 显示 Tab 上的未读数或红点
         *
         * @param count �?为0时红点会显示该数字作为未读数,为0时�?�会显示一个�?红点
         */
        public void showSignCountView(Context context, int count) {
            ensureSignCountView(context);
            mSignCountTextView.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams signCountLp = (RelativeLayout.LayoutParams) mSignCountTextView.getLayoutParams();
            if (count != 0) {
                // 显示未读数
                signCountLp.height = ThemeUtils.resolveDimension(mSignCountTextView.getContext(), R.attr.xui_tab_sign_count_view_minSize_with_text);
                mSignCountTextView.setLayoutParams(signCountLp);
                mSignCountTextView.setMinHeight(ThemeUtils.resolveDimension(mSignCountTextView.getContext(), R.attr.xui_tab_sign_count_view_minSize_with_text));
                mSignCountTextView.setMinWidth(ThemeUtils.resolveDimension(mSignCountTextView.getContext(), R.attr.xui_tab_sign_count_view_minSize_with_text));
                mSignCountTextView.setText(getNumberDigitsFormattingValue(count));
            } else {
                // 显示红点
                signCountLp.height = ThemeUtils.resolveDimension(mSignCountTextView.getContext(), R.attr.xui_tab_sign_count_view_minSize);
                mSignCountTextView.setLayoutParams(signCountLp);
                mSignCountTextView.setMinHeight(ThemeUtils.resolveDimension(mSignCountTextView.getContext(), R.attr.xui_tab_sign_count_view_minSize));
                mSignCountTextView.setMinWidth(ThemeUtils.resolveDimension(mSignCountTextView.getContext(), R.attr.xui_tab_sign_count_view_minSize));
                mSignCountTextView.setText(null);
            }
        }

        /**
         * �?�? Tab 上的未读数或红点
         */
        public void hideSignCountView() {
            if (mSignCountTextView != null) {
                mSignCountTextView.setVisibility(View.GONE);
            }
        }

        /**
         * 获�?�该 Tab 的未读数
         */
        public int getSignCount() {
            if (mSignCountTextView == null || mSignCountTextView.getVisibility() != VISIBLE) {
                return 0;
            }
            if (!Utils.isNullOrEmpty(mSignCountTextView.getText())) {
                return Integer.parseInt(mSignCountTextView.getText().toString());
            } else {
                return 0;
            }
        }

        private RelativeLayout.LayoutParams getDefaultCustomLayoutParam() {
            return new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }

        private String getNumberDigitsFormattingValue(int number) {
            if (Utils.getNumberDigits(number) > mSignCountDigits) {
                StringBuilder result = new StringBuilder();
                for (int digit = 1; digit <= mSignCountDigits; digit++) {
                    result.append("9");
                }
                result.append("+");
                return result.toString();
            } else {
                return String.valueOf(number);
            }
        }
    }

    public class TabAdapter extends XUIItemViewsAdapter<Tab, TabItemView> {
        public TabAdapter(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        protected TabItemView createView(ViewGroup parentView) {
            return new TabItemView(getContext());
        }

        @Override
        protected void bind(Tab item, TabItemView view, int position) {
            TextView tv = view.getTextView();
            setTextViewTypeface(tv, false);
            // custom view
            List<View> mCustomViews = item.getCustomViews();
            if (mCustomViews != null && mCustomViews.size() > 0) {
                view.setTag(R.id.xui_view_can_not_cache_tag, true);
                for (View v : mCustomViews) {
                    // 防止先 setCustomViews 然�?��? updateTabText 时会�?�?添加 customView 导致 crash
                    if (v.getParent() == null) {
                        view.addView(v);
                    }
                }
            }
            // gravity
            if (mMode == MODE_FIXED) {
                int gravity = item.getGravity();
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv.getLayoutParams();
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, (gravity & Gravity.LEFT) == Gravity.LEFT ? RelativeLayout.TRUE : 0);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL, (gravity & Gravity.CENTER) == Gravity.CENTER ? RelativeLayout.TRUE : 0);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, (gravity & Gravity.RIGHT) == Gravity.RIGHT ? RelativeLayout.TRUE : 0);
                tv.setLayoutParams(lp);
            }

            tv.setText(item.getText());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTabTextSize(item));
            view.updateDecoration(item, mCurrentSelectedIndex == position);
            view.setTag(position);
            view.setOnClickListener(mTabOnClickListener);
        }
    }

    private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        private boolean mAutoRefresh;
        private final boolean mUseAdapterTitle;

        AdapterChangeListener(boolean useAdapterTitle) {
            mUseAdapterTitle = useAdapterTitle;
        }

        @Override
        public void onAdapterChanged(@NonNull ViewPager viewPager,
                                     @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
            if (mViewPager == viewPager) {
                setPagerAdapter(newAdapter, mUseAdapterTitle, mAutoRefresh);
            }
        }

        void setAutoRefresh(boolean autoRefresh) {
            mAutoRefresh = autoRefresh;
        }
    }

    public class TabItemView extends RelativeLayout {
        private AppCompatTextView mTextView;
        private GestureDetector mGestureDetector;

        public TabItemView(Context context) {
            super(context);
            mTextView = new AppCompatTextView(getContext());
            mTextView.setSingleLine(true);
            mTextView.setGravity(Gravity.CENTER);
            mTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            mTextView.setTypeface(XUI.getDefaultTypeface());
            // 用于�??供给customView布局用
            mTextView.setId(R.id.xui_tab_segment_item_id);
            RelativeLayout.LayoutParams tvLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            addView(mTextView, tvLp);
            // 添加�?�击事件
            mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (mSelectedListeners.isEmpty()) {
                        return false;
                    }
                    int index = (int) TabItemView.this.getTag();
                    Tab model = getAdapter().getItem(index);
                    if (model != null) {
                        dispatchTabDoubleTap(index);
                        return true;
                    }
                    return false;
                }
            });
        }

        public TextView getTextView() {
            return mTextView;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return mGestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
        }

        public void setColorInTransition(Tab tab, int color) {
            mTextView.setTextColor(color);
            if (tab.isDynamicChangeIconColor()) {
                Drawable icon = mTextView.getCompoundDrawables()[getTabIconPosition(tab)];
                if (icon != null) {
                    Utils.setDrawableTintColor(icon, color);
                    setDrawable(mTextView, icon, getTabIconPosition(tab));
                }
            }
        }

        public void updateDecoration(Tab tab, boolean isSelected) {


            int color = isSelected ? getTabSelectedColor(tab) : getTabNormalColor(tab);
            mTextView.setTextColor(color);

            Drawable icon = tab.getNormalIcon();
            if (isSelected) {
                if (!tab.isDynamicChangeIconColor()) {
                    icon = tab.getSelectedIcon() != null ? tab.getSelectedIcon() : icon;
                } else if (icon != null) {
                    icon = icon.mutate();
                    Utils.setDrawableTintColor(icon, color);
                }
            }

            if (icon == null) {
                mTextView.setCompoundDrawablePadding(0);
                mTextView.setCompoundDrawables(null, null, null, null);
            } else {
                mTextView.setCompoundDrawablePadding(DensityUtils.dp2px(getContext(), 4));
                setDrawable(mTextView, icon, getTabIconPosition(tab));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mCurrentSelectedIndex != NO_POSITION && mMode == MODE_SCROLLABLE) {
            TabAdapter tabAdapter = getAdapter();
            final TabItemView view = tabAdapter.getViews().get(mCurrentSelectedIndex);
            if (getScrollX() > view.getLeft()) {
                scrollTo(view.getLeft(), 0);
            } else {
                int realWidth = getWidth() - getPaddingRight() - getPaddingLeft();
                if (getScrollX() + realWidth < view.getRight()) {
                    scrollBy(view.getRight() - realWidth - getScrollX(), 0);
                }
            }
        }
    }

    private class PagerAdapterObserver extends DataSetObserver {
        private final boolean mUseAdapterTitle;

        PagerAdapterObserver(boolean useAdapterTitle) {
            mUseAdapterTitle = useAdapterTitle;
        }

        @Override
        public void onChanged() {
            populateFromPagerAdapter(mUseAdapterTitle);
        }

        @Override
        public void onInvalidated() {
            populateFromPagerAdapter(mUseAdapterTitle);
        }
    }

    private final class Container extends ViewGroup {
        private TabAdapter mTabAdapter;

        public Container(Context context) {
            super(context);
            mTabAdapter = new TabAdapter(this);
        }

        public TabAdapter getTabAdapter() {
            return mTabAdapter;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
            List<TabItemView> childViews = mTabAdapter.getViews();
            int size = childViews.size();
            int i;

            int visibleChild = 0;
            for (i = 0; i < size; i++) {
                View child = childViews.get(i);
                if (child.getVisibility() == VISIBLE) {
                    visibleChild++;
                }
            }
            if (size == 0 || visibleChild == 0) {
                setMeasuredDimension(widthSpecSize, heightSpecSize);
                return;
            }

            int childHeight = heightSpecSize - getPaddingTop() - getPaddingBottom();
            int childWidthMeasureSpec, childHeightMeasureSpec, resultWidthSize = 0;
            if (mMode == MODE_FIXED) {
                resultWidthSize = widthSpecSize;
                int modeFixItemWidth = widthSpecSize / visibleChild;
                for (i = 0; i < size; i++) {
                    final View child = childViews.get(i);
                    if (child.getVisibility() != VISIBLE) {
                        continue;
                    }
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(modeFixItemWidth, MeasureSpec.EXACTLY);
                    childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                }
            } else {
                for (i = 0; i < size; i++) {
                    final View child = childViews.get(i);
                    if (child.getVisibility() != VISIBLE) {
                        continue;
                    }
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSpecSize, MeasureSpec.AT_MOST);
                    childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    resultWidthSize += child.getMeasuredWidth() + mItemSpaceInScrollMode;
                }
                resultWidthSize -= mItemSpaceInScrollMode;
            }

            setMeasuredDimension(resultWidthSize, heightSpecSize);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            List<TabItemView> childViews = mTabAdapter.getViews();
            int size = childViews.size();
            int i;
            int visibleChild = 0;
            for (i = 0; i < size; i++) {
                View child = childViews.get(i);
                if (child.getVisibility() == VISIBLE) {
                    visibleChild++;
                }
            }

            if (size == 0 || visibleChild == 0) {
                return;
            }

            int usedLeft = getPaddingLeft();
            for (i = 0; i < size; i++) {
                TabItemView childView = childViews.get(i);
                if (childView.getVisibility() != VISIBLE) {
                    continue;
                }
                final int childMeasureWidth = childView.getMeasuredWidth();
                childView.layout(usedLeft, getPaddingTop(), usedLeft + childMeasureWidth, b - t - getPaddingBottom());


                Tab model = mTabAdapter.getItem(i);
                int oldLeft, oldWidth, newLeft, newWidth;
                oldLeft = model.getContentLeft();
                oldWidth = model.getContentWidth();
                if (mMode == MODE_FIXED && mIsIndicatorWidthFollowContent) {
                    TextView contentView = childView.getTextView();
                    newLeft = usedLeft + contentView.getLeft();
                    newWidth = contentView.getWidth();
                } else {
                    newLeft = usedLeft;
                    newWidth = childMeasureWidth;
                }
                if (oldLeft != newLeft || oldWidth != newWidth) {
                    model.setContentLeft(newLeft);
                    model.setContentWidth(newWidth);
                }
                usedLeft = usedLeft + childMeasureWidth + (mMode == MODE_SCROLLABLE ? mItemSpaceInScrollMode : 0);
            }

            if (mCurrentSelectedIndex != NO_POSITION && mSelectAnimator == null
                    && mViewPagerScrollState == ViewPager.SCROLL_STATE_IDLE) {
                layoutIndicator(mTabAdapter.getItem(mCurrentSelectedIndex), false);
            }
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            if (mHasIndicator && mIndicatorRect != null) {
                if (mIndicatorTop) {
                    mIndicatorRect.top = getPaddingTop();
                    mIndicatorRect.bottom = mIndicatorRect.top + mIndicatorHeight;
                } else {
                    mIndicatorRect.bottom = getHeight() - getPaddingBottom();
                    mIndicatorRect.top = mIndicatorRect.bottom - mIndicatorHeight;
                }
                if (mIndicatorDrawable != null) {
                    mIndicatorDrawable.setBounds(mIndicatorRect);
                    mIndicatorDrawable.draw(canvas);
                } else {
                    canvas.drawRect(mIndicatorRect, mIndicatorPaint);
                }
            }
        }
    }
}
