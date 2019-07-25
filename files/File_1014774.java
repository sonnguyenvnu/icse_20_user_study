package com.cgfay.facedetect.engine;

import com.cgfay.facedetect.listener.FaceTrackerCallback;
import com.megvii.facepp.sdk.Facepp;

/**
 * 人脸检测�?�数
 */
public final class FaceTrackParam {

    // 是�?��?许检测
    boolean canFaceTrack = false;
    // 旋转角度
    public int rotateAngle;
    // 是�?�相机预览检测，true为预览检测，false为�?��?图片检测
    public boolean previewTrack;
    // 是�?��?许3D姿�?角
    public boolean enable3DPose;
    // 是�?��?许区域检测
    public boolean enableROIDetect;
    // 检测区域缩放比例
    public float roiRatio;
    // 是�?��?许106个关键点
    public boolean enable106Points;
    // 是�?��?�置摄�?头
    public boolean isBackCamera;
    // 是�?��?许人脸年龄检测
    public boolean enableFaceProperty;
    // 是�?��?许多人脸检测
    public boolean enableMultiFace;
    // 最�?人脸大�?
    public int minFaceSize;
    // 检测间隔
    public int detectInterval;
    // 检测模�?
    public int trackMode;
    // 检测回调
    public FaceTrackerCallback trackerCallback;

    private static class FaceParamHolder {
        public static FaceTrackParam instance = new FaceTrackParam();
    }

    private FaceTrackParam() {
        reset();
    }

    public static FaceTrackParam getInstance() {
        return FaceParamHolder.instance;
    }

    /**
     * �?置为�?始状�?
     */
    public void reset() {
        previewTrack = true;
        enable3DPose = false;
        enableROIDetect = false;
        roiRatio = 0.8f;
        enable106Points = true;
        isBackCamera = false;
        enableFaceProperty = false;
        enableMultiFace = true;
        minFaceSize = 200;
        detectInterval = 25;
        trackMode = Facepp.FaceppConfig.DETECTION_MODE_TRACKING_SMOOTH;
        trackerCallback = null;
    }

}
