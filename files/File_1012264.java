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

package com.xuexiang.xui.widget.imageview.preview;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.xuexiang.xui.widget.imageview.preview.enitity.IPreviewInfo;
import com.xuexiang.xui.widget.imageview.preview.loader.OnVideoClickListener;
import com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment;
import com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity;

import java.util.ArrayList;
import java.util.List;

import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_DRAG;
import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_SENSITIVITY;
import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_SING_FILING;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_CLASSNAME;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_DURATION;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_IMAGE_PATHS;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_IS_FULLSCREEN;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_IS_SHOW;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_POSITION;
import static com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity.KEY_TYPE;

/**
 * 构建者
 *
 * @author xuexiang
 * @since 2018/12/5 上�?�11:46
 */
public final class PreviewBuilder {
    private Activity mContext;
    private Intent intent;
    private Class className;
    private OnVideoClickListener videoClickListener;

    private PreviewBuilder(@NonNull Activity activity) {
        mContext = activity;
        intent = new Intent();
    }

    /**
     * 设置开始�?�动预览
     *
     * @param activity �?�动界�?�
     * @return
     */
    public static PreviewBuilder from(@NonNull Activity activity) {
        return new PreviewBuilder(activity);
    }

    /**
     * 设置开始�?�动预览
     *
     * @param fragment �?�动界�?�
     * @return
     */
    public static PreviewBuilder from(@NonNull Fragment fragment) {
        return new PreviewBuilder(fragment.getActivity());
    }

    /****
     * 自定义预览activity 类�??
     * @param className   继承GPreviewActivity
     * @return PreviewBuilder
     */
    public PreviewBuilder to(@NonNull Class className) {
        this.className = className;
        intent.setClass(mContext, className);
        return this;
    }

    /**
     * 设置图片数�?��?
     *
     * @param imgUrls 数�?�
     * @param <T>
     * @return
     */
    public <T extends IPreviewInfo> PreviewBuilder setImgs(@NonNull List<T> imgUrls) {
        intent.putParcelableArrayListExtra(KEY_IMAGE_PATHS, new ArrayList<Parcelable>(imgUrls));
        return this;
    }

    /***
     * 设置�?�个图片数�?��?
     * @param imgUrl 数�?�
     * @param <E> 你的实体类类型
     * @return PreviewBuilder
     */
    public <E extends IPreviewInfo> PreviewBuilder setImg(@NonNull E imgUrl) {
        ArrayList arrayList = new ArrayList<Parcelable>();
        arrayList.add(imgUrl);
        intent.putParcelableArrayListExtra(KEY_IMAGE_PATHS, arrayList);
        return this;
    }

    /***
     * 设置图片预览fragment
     * @param className 你的Fragment类
     * @return PreviewBuilder
     * **/
    public PreviewBuilder setPhotoFragment(@NonNull Class<? extends BasePhotoFragment> className) {
        intent.putExtra(KEY_CLASSNAME, className);
        return this;
    }

    /***
     * 设置默认索引
     * @param currentIndex 数�?�
     * @return PreviewBuilder
     */
    public PreviewBuilder setCurrentIndex(int currentIndex) {
        intent.putExtra(KEY_POSITION, currentIndex);
        return this;
    }

    /***
     * 设置指示器类型
     * @param indicatorType 枚举
     * @return PreviewBuilder
     */
    public PreviewBuilder setType(@NonNull IndicatorType indicatorType) {
        intent.putExtra(KEY_TYPE, indicatorType);
        return this;
    }

    /***
     * 设置图片�?用拖拽返回
     * @param isDrag  true  �?�以 false 默认 true
     * @return PreviewBuilder
     */
    public PreviewBuilder setDrag(boolean isDrag) {
        intent.putExtra(KEY_DRAG, isDrag);
        return this;
    }

    /***
     * 设置图片�?用拖拽返回
     * @param isDrag  true  �?�以 false 默认 true
     * @param sensitivity   sensitivity mMaxTransScale 的值�?�控制�?��?度。
     * @return PreviewBuilder
     */
    public PreviewBuilder setDrag(boolean isDrag, float sensitivity) {
        intent.putExtra(KEY_DRAG, isDrag);
        intent.putExtra(KEY_SENSITIVITY, sensitivity);
        return this;
    }

    /***
     * 是�?�设置为一张图片时 显示指示器  默认显示
     * @param isShow   true  显示 false �?显示
     * @return PreviewBuilder
     */
    public PreviewBuilder setSingleShowType(boolean isShow) {
        intent.putExtra(KEY_IS_SHOW, isShow);
        return this;
    }

    /***
     * 设置超出内容点击退出（黑色区域）
     * @param isSingleFling  true  �?�以 false
     * @return PreviewBuilder
     */
    public PreviewBuilder setSingleFling(boolean isSingleFling) {
        intent.putExtra(KEY_SING_FILING, isSingleFling);
        return this;
    }

    /***
     * 设置动画的时长
     * @param setDuration  �?��?毫秒
     * @return PreviewBuilder
     */
    public PreviewBuilder setDuration(int setDuration) {
        intent.putExtra(KEY_DURATION, setDuration);
        return this;
    }

    /***
     *  设置是�?�全�?
     * @param isFullscreen  �?��?毫秒
     * @return PreviewBuilder
     */
    public PreviewBuilder setFullscreen(boolean isFullscreen) {
        intent.putExtra(KEY_IS_FULLSCREEN, isFullscreen);
        return this;
    }

    /***
     *  设置是怕你点击播放回调
     * @return PreviewBuilder
     */
    public PreviewBuilder setOnVideoPlayerListener(OnVideoClickListener listener) {
        this.videoClickListener = listener;
        return this;
    }

    /***
     * �?�动
     */
    public void start() {
        if (className == null) {
            intent.setClass(mContext, PreviewActivity.class);
        } else {
            intent.setClass(mContext, className);
        }
        BasePhotoFragment.listener = videoClickListener;
        mContext.startActivity(intent);
        mContext.overridePendingTransition(0, 0);
        intent = null;
        mContext = null;
    }

    /**
     * 指示器类型
     */
    public enum IndicatorType {
        /**
         * 点
         */
        Dot,
        /**
         * 数字
         */
        Number
    }
}
