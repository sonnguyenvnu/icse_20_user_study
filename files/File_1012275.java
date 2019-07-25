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

package com.xuexiang.xui.widget.popupwindow.bar;

import android.app.Activity;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import static com.xuexiang.xui.widget.popupwindow.bar.Cookie.DEFAULT_COOKIE_DURATION;

/**
 * 顶部和底部信�?�消�?�显示�?�<p>
 * <pre>
 * new CookieBar
 *      .Builder(MainActivity.this)
 *      .setTitle("TITLE")
 *      .setMessage("MESSAGE")
 *      .setAction("ACTION", new OnActionClickListener() {})
 *      .show();
 * </pre>
 * <p>
 *
 * @author xuexiang
 * @since 2018/12/19 上�?�9:30
 */
public final class CookieBar {

    private Cookie mCookieView;
    private WeakReference<Activity> mActivityWeakRef;

    private CookieBar() {
    }

    private CookieBar(Activity activity, Params params) {
        mActivityWeakRef = new WeakReference<>(activity);
        mCookieView = new Cookie(getActivity());
        mCookieView.setParams(params);
    }

    /**
     * 显示
     */
    public void show() {
        if (mCookieView != null && getActivity() != null) {
            final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
            final ViewGroup content = decorView.findViewById(android.R.id.content);
            if (mCookieView.getParent() == null) {
                if (mCookieView.getLayoutGravity() == Gravity.BOTTOM) {
                    content.addView(mCookieView);
                } else {
                    decorView.addView(mCookieView);
                }
            }
        }
    }

    /**
     * 消失
     */
    public void dismiss() {
        if (mCookieView != null) {
            mCookieView.dismiss();
        }
    }

    /**
     * 获�?� Activity
     *
     * @return
     */
    public Activity getActivity() {
        if (mActivityWeakRef != null && mActivityWeakRef.get() != null) {
            return mActivityWeakRef.get();
        } else {
            return null;
        }
    }

    /**
     * 获�?�构建者
     *
     * @param activity
     * @return
     */
    public static Builder builder(Activity activity) {
        return new Builder(activity);
    }

    public static class Builder {
        private Params params = new Params();

        public Activity context;

        /**
         * Create a builder for an cookie.
         */
        public Builder(Activity activity) {
            this.context = activity;
        }

        public Builder setIcon(@DrawableRes int iconResId) {
            params.iconResId = iconResId;
            return this;
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int resId) {
            params.title = context.getString(resId);
            return this;
        }

        public Builder setMessage(String message) {
            params.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            params.message = context.getString(resId);
            return this;
        }

        public Builder setDuration(long duration) {
            params.duration = duration;
            return this;
        }

        public Builder setTitleColor(@ColorRes int titleColor) {
            params.titleColor = titleColor;
            return this;
        }

        public Builder setMessageColor(@ColorRes int messageColor) {
            params.messageColor = messageColor;
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColor) {
            params.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setActionColor(@ColorRes int actionColor) {
            params.actionColor = actionColor;
            return this;
        }

        public Builder setAction(String action, OnClickListener onActionClickListener) {
            params.action = action;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setAction(@StringRes int resId, OnClickListener onActionClickListener) {
            params.action = context.getString(resId);
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setActionWithIcon(@DrawableRes int resId,
                                         OnClickListener onActionClickListener) {
            params.actionIcon = resId;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setLayoutGravity(int layoutGravity) {
            params.layoutGravity = layoutGravity;
            return this;
        }

        public CookieBar create() {
            return new CookieBar(context, params);
        }

        public CookieBar show() {
            final CookieBar cookie = create();
            cookie.show();
            return cookie;
        }
    }

    final static class Params {

        /**
         * 标题
         */
        public String title;

        /**
         * 文字信�?�
         */
        public String message;

        /**
         * 按钮文字
         */
        public String action;

        /**
         * 按钮图片资�?
         */
        public int actionIcon;

        /**
         * 按钮点击时间
         */
        public OnClickListener onActionClickListener;

        /**
         * 左侧图标资�?
         */
        public int iconResId;

        /**
         * 背景颜色
         */
        public int backgroundColor;

        /**
         * 标题文字颜色
         */
        public int titleColor;

        /**
         * �??示信�?�文字颜色
         */
        public int messageColor;

        /**
         * 按钮文字颜色
         */
        public int actionColor;

        /**
         * 显示�?续时间
         */
        public long duration = DEFAULT_COOKIE_DURATION;

        /**
         * 布局对�?方�?
         */
        public int layoutGravity = Gravity.TOP;


    }

}
