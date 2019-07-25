package com.cgfay.filter.glfilter.stickers;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.cgfay.filter.glfilter.resource.ResourceCodec;
import com.cgfay.filter.glfilter.resource.ResourceIndexCodec;
import com.cgfay.filter.glfilter.stickers.bean.DynamicStickerData;
import com.cgfay.filter.glfilter.utils.OpenGLUtils;
import com.cgfay.landmark.LandmarkEngine;
import com.cgfay.uitls.utils.BitmapUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * 动�?贴纸加载器
 */
public class DynamicStickerLoader {

    public boolean isStaticSticker=false;
    private static final String TAG = "DynamicStickerLoader";

    // 贴纸纹�?�
    private int mStickerTexture;
    // 暂存纹�?�id，用于�?用
    private int mRestoreTexture;
    // 贴纸所在的文件夹
    private String mFolderPath;
    // 贴纸数�?�
    private DynamicStickerData mStickerData;
    // 索引索引加载器
    private ResourceIndexCodec mResourceIndexCodec;
    // 当�?索引
    private int mFrameIndex = -1;
    // 当�?时间
    private long mCurrentTime = -1L;
    // 贴纸滤镜
    private final WeakReference<DynamicStickerBaseFilter> mWeakFilter;
    public DynamicStickerLoader(boolean isStaticSticker,DynamicStickerBaseFilter filter, DynamicStickerData stickerData, String folderPath) {
        this.isStaticSticker=isStaticSticker;
        mWeakFilter = new WeakReference<>(filter);
        mStickerTexture = OpenGLUtils.GL_NOT_TEXTURE;
        mRestoreTexture = OpenGLUtils.GL_NOT_TEXTURE;
        mFolderPath = folderPath.startsWith("file://") ? folderPath.substring("file://".length()) : folderPath;
        mStickerData = stickerData;
        Pair pair = ResourceCodec.getResourceFile(mFolderPath);
        if (pair != null) {
            mResourceIndexCodec = new ResourceIndexCodec(mFolderPath + "/" + (String) pair.first, mFolderPath + "/" + (String) pair.second);
        }
        if (mResourceIndexCodec != null) {
            try {
                mResourceIndexCodec.init();
            } catch (IOException e) {
                Log.e(TAG, "init merge res reader failed", e);
                mResourceIndexCodec = null;
            }
        }
        mStickerTexture = OpenGLUtils.GL_NOT_TEXTURE;
        mRestoreTexture = OpenGLUtils.GL_NOT_TEXTURE;
        // 如果存在的音�?路径，则播放需�?�?始化路径
        if (!TextUtils.isEmpty(mStickerData.audioPath)) {
            String str;
            if (mFolderPath.startsWith("file://")) {
                str = mFolderPath.substring("file://".length());
            } else {
                str = mFolderPath;
            }
            if (mWeakFilter.get() != null) {
                mWeakFilter.get().setAudioPath(Uri.parse(str + "/" + mStickerData.audioPath));
                mWeakFilter.get().setLooping(mStickerData.audioLooping);
            }
        }
    }
    public DynamicStickerLoader(DynamicStickerBaseFilter filter, DynamicStickerData stickerData, String folderPath) {
       this(false,filter,stickerData,folderPath);
    }

    /**
     * 更新贴纸纹�?�
     */
    public void updateStickerTexture() {
        // 判断人脸是�?�存在
        if (!LandmarkEngine.getInstance().hasFace()&&!isStaticSticker) {
            mCurrentTime = -1L;
            if (mWeakFilter.get() != null) {
                mWeakFilter.get().stopPlayer();
            }
            return;
        }

        // 如果如果存在音�?并且动作标记为0，则表示属于默认音�?，直接开始播放音�?
        if (!TextUtils.isEmpty(mStickerData.audioPath) && mStickerData.action == 0) {
            if (mWeakFilter.get() != null) {
                mWeakFilter.get().startPlayer();
            }
        }

        // 处�?�贴纸索引
        if (mCurrentTime == -1L) {
            mCurrentTime = System.currentTimeMillis();
        }
        int frameIndex = (int) ((System.currentTimeMillis() - mCurrentTime) / mStickerData.duration);
        if (frameIndex >= mStickerData.frames) {
            if (!mStickerData.stickerLooping) {
                mCurrentTime = -1L;
                mRestoreTexture = mStickerTexture;
                mStickerTexture = OpenGLUtils.GL_NOT_TEXTURE;
                mFrameIndex = -1;
                return;
            }
            frameIndex = 0;
            mCurrentTime = System.currentTimeMillis();
        }
        if (frameIndex < 0) {
            frameIndex = 0;
        }
        if (mFrameIndex == frameIndex) {
            return;
        }

        // �?新播放音�?，音�?对�?
        if ((frameIndex == 0) && (mStickerData.audioLooping)) {
            if (mWeakFilter.get() != null) {
                mWeakFilter.get().restartPlayer();
            }
        }
        // 根�?�帧索引读�?�贴纸
        Bitmap bitmap = null;
        if (mResourceIndexCodec != null) {
            bitmap = mResourceIndexCodec.loadResource(frameIndex);
        }
        if (bitmap == null) {
            String path = String.format(mStickerData.stickerName + "_%03d.png", new Object[]{frameIndex});
            bitmap = BitmapUtils.getBitmapFromFile(mFolderPath + "/" + path);
        }
        if (null != bitmap) {
            // 如果此时暂存的纹�?�ID存在，则�?用该ID
            if (mStickerTexture == OpenGLUtils.GL_NOT_TEXTURE
                    && mRestoreTexture != OpenGLUtils.GL_NOT_TEXTURE) {
                mStickerTexture = mRestoreTexture;
            }
            if (mStickerTexture == OpenGLUtils.GL_NOT_TEXTURE) {
                mStickerTexture = OpenGLUtils.createTexture(bitmap);
            } else {
                mStickerTexture = OpenGLUtils.createTexture(bitmap, mStickerTexture);
            }
            mRestoreTexture = mStickerTexture;
            mFrameIndex = frameIndex;
            bitmap.recycle();
        } else {
            mRestoreTexture = mStickerTexture;
            mStickerTexture = OpenGLUtils.GL_NOT_TEXTURE;
            mFrameIndex = -1;
        }
    }

    /**
     * 释放资�?
     */
    public void release() {
        if (mStickerTexture == OpenGLUtils.GL_NOT_TEXTURE) {
            mStickerTexture = mRestoreTexture;
        }
        OpenGLUtils.deleteTexture(mStickerTexture);
        mStickerTexture = OpenGLUtils.GL_NOT_TEXTURE;
        mRestoreTexture = OpenGLUtils.GL_NOT_TEXTURE;
        if (mWeakFilter.get() != null) {
            mWeakFilter.clear();
        }
    }

    /**
     * 获�?�贴纸纹�?�
     * @return
     */
    public int getStickerTexture() {
        return mStickerTexture;
    }

    /**
     * 最大贴纸渲染次数
     * @return
     */
    public int getMaxCount() {
        return mStickerData == null ? 0 : mStickerData.maxCount;
    }

    /**
     * 获�?�贴纸�?�数对象
     * @return
     */
    public DynamicStickerData getStickerData() {
        return mStickerData;
    }
}
