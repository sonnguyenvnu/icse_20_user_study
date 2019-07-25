/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wx.wheelview.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.common.WheelConstants;
import com.wx.wheelview.util.WheelUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 滚轮对�?框
 *
 * @author venshine
 */
public class WheelViewDialog<T> implements View.OnClickListener {

    private TextView mTitle;

    private View mLine1, mLine2;

    private WheelView<T> mWheelView;

    private WheelView.WheelViewStyle mStyle;

    private TextView mButton;

    private AlertDialog mDialog;

    private Context mContext;

    private OnDialogItemClickListener mOnDialogItemClickListener;

    private int mSelectedPos;

    private T mSelectedText;

    public WheelViewDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(WheelUtils.dip2px(mContext, 20), 0, WheelUtils.dip2px(mContext, 20), 0);

        mTitle = new TextView(mContext);
        mTitle.setTextColor(WheelConstants.DIALOG_WHEEL_COLOR);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        mTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                WheelUtils.dip2px(mContext, 50));
        layout.addView(mTitle, titleParams);

        mLine1 = new View(mContext);
        mLine1.setBackgroundColor(WheelConstants.DIALOG_WHEEL_COLOR);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                WheelUtils.dip2px(mContext, 2));
        layout.addView(mLine1, lineParams);

        mWheelView = new WheelView(mContext);
        mWheelView.setSkin(WheelView.Skin.Holo);
        mWheelView.setWheelAdapter(new ArrayWheelAdapter(mContext));
        mStyle = new WheelView.WheelViewStyle();
        mStyle.textColor = Color.GRAY;
        mStyle.selectedTextZoom = 1.2f;
        mWheelView.setStyle(mStyle);

        mWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener<T>() {
            @Override
            public void onItemSelected(int position, T text) {
                mSelectedPos = position;
                mSelectedText = text;
            }
        });
        ViewGroup.MarginLayoutParams wheelParams = new ViewGroup.MarginLayoutParams(LinearLayout.LayoutParams
                .MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mWheelView, wheelParams);

        mLine2 = new View(mContext);
        mLine2.setBackgroundColor(WheelConstants.DIALOG_WHEEL_COLOR);
        LinearLayout.LayoutParams line2Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                WheelUtils.dip2px(mContext, 1f));
        layout.addView(mLine2, line2Params);

        mButton = new TextView(mContext);
        mButton.setTextColor(WheelConstants.DIALOG_WHEEL_COLOR);
        mButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mButton.setGravity(Gravity.CENTER);
        mButton.setClickable(true);
        mButton.setOnClickListener(this);
        mButton.setText("OK");
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                WheelUtils.dip2px(mContext, 45));
        layout.addView(mButton, buttonParams);

        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setView(layout);
        mDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 点击事件
     *
     * @param onDialogItemClickListener
     * @return
     */
    public WheelViewDialog setOnDialogItemClickListener(OnDialogItemClickListener onDialogItemClickListener) {
        mOnDialogItemClickListener = onDialogItemClickListener;
        return this;
    }

    /**
     * 设置dialog外观颜色
     *
     * @param color
     * @return
     */
    public WheelViewDialog setDialogStyle(int color) {
        mTitle.setTextColor(color);
        mLine1.setBackgroundColor(color);
        mLine2.setBackgroundColor(color);
        mButton.setTextColor(color);
        mStyle.selectedTextColor = color;
        mStyle.holoBorderColor = color;
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public WheelViewDialog setTitle(String title) {
        mTitle.setText(title);
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param color
     * @return
     */
    public WheelViewDialog setTextColor(int color) {
        mTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置标题大�?
     *
     * @param size
     * @return
     */
    public WheelViewDialog setTextSize(int size) {
        mTitle.setTextSize(size);
        return this;
    }

    /**
     * 设置按钮文本
     *
     * @param text
     * @return
     */
    public WheelViewDialog setButtonText(String text) {
        mButton.setText(text);
        return this;
    }

    /**
     * 设置按钮文本颜色
     *
     * @param color
     * @return
     */
    public WheelViewDialog setButtonColor(int color) {
        mButton.setTextColor(color);
        return this;
    }

    /**
     * 设置按钮文本尺寸
     *
     * @param size
     * @return
     */
    public WheelViewDialog setButtonSize(int size) {
        mButton.setTextSize(size);
        return this;
    }

    /**
     * 设置数�?�项显示个数
     *
     * @param count
     */
    public WheelViewDialog setCount(int count) {
        mWheelView.setWheelSize(count);
        return this;
    }

    /**
     * 数�?�项是�?�循环显示
     *
     * @param loop
     */
    public WheelViewDialog setLoop(boolean loop) {
        mWheelView.setLoop(loop);
        return this;
    }

    /**
     * 设置数�?�项显示�?置
     *
     * @param selection
     */
    public WheelViewDialog setSelection(int selection) {
        mWheelView.setSelection(selection);
        return this;
    }

    /**
     * 设置数�?�项
     *
     * @param arrays
     */
    public WheelViewDialog setItems(T[] arrays) {
        return setItems(Arrays.asList(arrays));
    }

    /**
     * 设置数�?�项
     *
     * @param list
     */
    public WheelViewDialog setItems(List<T> list) {
        mWheelView.setWheelData(list);
        return this;
    }

    /**
     * 显示
     */
    public WheelViewDialog show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    /**
     * �?�?
     */
    public WheelViewDialog dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (null != mOnDialogItemClickListener) {
            mOnDialogItemClickListener.onItemClick(mSelectedPos, mSelectedText);
        }
    }

    public interface OnDialogItemClickListener<T> {
        void onItemClick(int position, T s);
    }
}
