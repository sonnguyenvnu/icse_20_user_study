/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.xui.widget.progress.loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xuexiang.xui.R;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;

/**
 * 自定义加载布局
 *
 * @author xuexiang
 * @since 2019/1/11 下�?�3:58
 */
public class LoadingViewLayout extends LinearLayout implements IMessageLoader {

    /**
     * loading控件
     */
    private ARCLoadingView mLoadingView;
    /**
     * �??示文字
     */
    private TextView mTvTipMessage;

    public LoadingViewLayout(@NonNull Context context) {
        super(context);
        initView(context);
        initAttrs(context, null);
    }

    public LoadingViewLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public LoadingViewLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }

    /**
     * 加载控件
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.xui_layout_loading_view, this, true);
        mLoadingView = findViewById(R.id.arc_loading_view);
        mTvTipMessage = findViewById(R.id.tv_tip_message);

        initLayoutStyle(context);
    }

    private void initLayoutStyle(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setVisibility(GONE);
        int padding = ThemeUtils.resolveDimension(context, R.attr.xui_dialog_loading_padding_size);
        setPadding(padding, padding, padding, padding);
        int minSize = ThemeUtils.resolveDimension(context, R.attr.xui_dialog_loading_min_size);
        setMinimumHeight(minSize);
        setMinimumWidth(minSize);
    }

    /**
     * �?始化属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingViewLayout);
            String message = typedArray.getString(R.styleable.LoadingViewLayout_lvl_message);
            if (!TextUtils.isEmpty(message)) {
                updateMessage(message);
            }
            setLoadingIcon(typedArray.getDrawable(R.styleable.LoadingViewLayout_lvl_icon));
            typedArray.recycle();
        }
    }

    /**
     * 更新�??示信�?�
     * @param tipMessage
     * @return
     */
    @Override
    public void updateMessage(String tipMessage) {
        if (mTvTipMessage != null) {
            mTvTipMessage.setText(tipMessage);
        }
    }

    /**
     * 更新�??示信�?�
     * @param tipMessageId
     * @return
     */
    @Override
    public void updateMessage(int tipMessageId) {
        updateMessage(getString(tipMessageId));
    }

    /**
     * 设置loading的图标
     * @param icon
     * @return
     */
    public LoadingViewLayout setLoadingIcon(Drawable icon) {
        if (mLoadingView != null) {
            mLoadingView.setLoadingIcon(icon);
        }
        return this;
    }

    /**
     * 设置图标的缩�?比例
     *
     * @param iconScale
     * @return
     */
    public LoadingViewLayout setIconScale(float iconScale) {
        if (mLoadingView != null) {
            mLoadingView.setIconScale(iconScale);
        }
        return this;
    }


    /**
     * 设置loading的图标
     * @param iconResId
     * @return
     */
    public LoadingViewLayout setLoadingIcon(int iconResId) {
        return setLoadingIcon(getDrawable(iconResId));
    }

    /**
     * 显示
     */
    @Override
    public void show() {
        setVisibility(VISIBLE);
        if (mLoadingView != null) {
            mLoadingView.start();
        }
    }

    /**
     * �?�?
     */
    @Override
    public void dismiss() {
        if (mLoadingView != null) {
            mLoadingView.stop();
        }
        setVisibility(GONE);
    }

    /**
     * 资�?释放
     */
    @Override
    public void recycle() {
        if (mLoadingView != null) {
            mLoadingView.recycle();
        }
    }

    @Override
    public boolean isLoading() {
        return getVisibility() == VISIBLE;
    }

    @Override
    public void setCancelable(boolean cancelable) {  //�?�?��?�消

    }

    @Override
    public void setLoadingCancelListener(LoadingCancelListener listener) {
    }

    public String getString(int resId){
        return getContext().getResources().getString(resId);
    }

    public Drawable getDrawable(int resId){
        return ResUtils.getDrawable(getContext(), resId);
    }
}
