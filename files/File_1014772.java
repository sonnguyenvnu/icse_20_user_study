package com.cgfay.camera.engine;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.app.Fragment;

import com.cgfay.cameralibrary.R;
import com.cgfay.camera.activity.CameraActivity;
import com.cgfay.camera.engine.camera.CameraParam;
import com.cgfay.camera.listener.OnGallerySelectedListener;
import com.cgfay.camera.listener.OnPreviewCaptureListener;
import com.cgfay.camera.engine.model.AspectRatio;

public final class PreviewBuilder {

    private PreviewEngine mPreviewEngine;
    private CameraParam mCameraParam;

    public PreviewBuilder(PreviewEngine engine, AspectRatio ratio) {
        mPreviewEngine = engine;
        mCameraParam = CameraParam.getInstance();
        mCameraParam.setAspectRatio(ratio);
    }

    /**
     * 是�?�显示人脸关键点
     * @param show
     * @return
     */
    public PreviewBuilder showFacePoints(boolean show) {
        mCameraParam.drawFacePoints = show;
        return this;
    }

    /**
     * 是�?�显示fps
     * @param show
     * @return
     */
    public PreviewBuilder showFps(boolean show) {
        mCameraParam.showFps = show;
        return this;
    }

    /**
     * 期望预览帧率
     * @param fps
     * @return
     */
    public PreviewBuilder expectFps(int fps) {
        mCameraParam.expectFps = fps;
        return this;
    }

    /**
     * 期望宽度
     * @param width
     * @return
     */
    public PreviewBuilder expectWidth(int width) {
        mCameraParam.expectWidth = width;
        return this;
    }

    /**
     * 期望高度
     * @param height
     * @return
     */
    public PreviewBuilder expectHeight(int height) {
        mCameraParam.expectHeight = height;
        return this;
    }

    /**
     * 是�?�高清�?照
     * @param highDefinition
     * @return
     */
    public PreviewBuilder highDefinition(boolean highDefinition) {
        mCameraParam.highDefinition = highDefinition;
        return this;
    }

    /**
     * 是�?�打开�?�置摄�?头
     * @param backCamera
     * @return
     */
    public PreviewBuilder backCamera(boolean backCamera) {
        mCameraParam.backCamera = backCamera;
        if (mCameraParam.backCamera) {
            mCameraParam.cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        return this;
    }

    /**
     * 对焦�?��?
     * @param weight
     * @return
     */
    public PreviewBuilder focusWeight(int weight) {
        mCameraParam.setFocusWeight(weight);
        return this;
    }

    /**
     * 是�?��?许录制
     * @param recordable
     * @return
     */
    public PreviewBuilder recordable(boolean recordable) {
        mCameraParam.recordable = recordable;
        return this;
    }

    /**
     * 录制时间
     * @param recordTime
     * @return
     */
    public PreviewBuilder recordTime(int recordTime) {
        mCameraParam.recordTime = recordTime;
        return this;
    }

    /**
     * 是�?�录制音频
     * @param recordAudio
     * @return
     */
    public PreviewBuilder recordAudio(boolean recordAudio) {
        mCameraParam.recordAudio = recordAudio;
        return this;
    }

    /**
     * 延时�?摄
     * @param takeDelay
     * @return
     */
    public PreviewBuilder takeDelay(boolean takeDelay) {
        mCameraParam.takeDelay = takeDelay;
        return this;
    }

    /**
     * 是�?�开�?�夜光补�?�
     * @param luminousEnhancement
     * @return
     */
    public PreviewBuilder luminousEnhancement(boolean luminousEnhancement) {
        mCameraParam.luminousEnhancement = luminousEnhancement;
        return this;
    }

    /**
     * 设置�?照监�?�器
     * @param listener
     * @return
     */
    public PreviewBuilder setPreviewCaptureListener(OnPreviewCaptureListener listener) {
        mCameraParam.captureListener = listener;
        return this;
    }

    /**
     *
     * @param listener
     * @return
     */
    public PreviewBuilder setGalleryListener(OnGallerySelectedListener listener) {
        mCameraParam.gallerySelectedListener = listener;
        return this;
    }

    /**
     * 打开预览
     * @param requestCode
     */
    public void startPreviewForResult(int requestCode) {
        Activity activity = mPreviewEngine.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, CameraActivity.class);
        Fragment fragment = mPreviewEngine.getFragment();
        if (fragment != null) {
            fragment.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 打开预览
     */
    public void startPreview() {
        Activity activity = mPreviewEngine.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, CameraActivity.class);
        Fragment fragment = mPreviewEngine.getFragment();
        if (fragment != null) {
            fragment.startActivity(intent);
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.anim_slide_up, 0);
        }
    }
}
