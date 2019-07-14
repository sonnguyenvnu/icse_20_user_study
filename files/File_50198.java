package cn.finalteam.rxgalleryfinal.presenter.impl;

import android.content.Context;

import java.util.List;

import cn.finalteam.rxgalleryfinal.bean.BucketBean;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.interactor.MediaBucketFactoryInteractor;
import cn.finalteam.rxgalleryfinal.interactor.MediaSrcFactoryInteractor;
import cn.finalteam.rxgalleryfinal.interactor.impl.MediaBucketFactoryInteractorImpl;
import cn.finalteam.rxgalleryfinal.interactor.impl.MediaSrcFactoryInteractorImpl;
import cn.finalteam.rxgalleryfinal.presenter.MediaGridPresenter;
import cn.finalteam.rxgalleryfinal.view.MediaGridView;

/**
 * Desction:
 * Author:pengjianbo  Dujinyang
 * Date:16/5/14 ä¸Šå?ˆ10:58
 */
public class MediaGridPresenterImpl implements MediaGridPresenter, MediaSrcFactoryInteractor.OnGenerateMediaListener,
        MediaBucketFactoryInteractor.OnGenerateBucketListener {

    private final MediaSrcFactoryInteractor mediaSrcFactoryInteractor;
    private final MediaBucketFactoryInteractor mediaBucketFactoryInteractor;

    private MediaGridView mediaGridView;

    public MediaGridPresenterImpl(Context context, boolean isImage) {
        this.mediaSrcFactoryInteractor = new MediaSrcFactoryInteractorImpl(context, isImage, this);
        this.mediaBucketFactoryInteractor = new MediaBucketFactoryInteractorImpl(context, isImage, this);
    }

    /**
     * è®¾ç½®MVP view(æ“?ä½œUIæŽ¥å?£)
     */
    @Override
    public void setMediaGridView(MediaGridView mediaGridView) {
        this.mediaGridView = mediaGridView;
    }

    /**
     * åˆ†é¡µèŽ·å?–media
     */
    @Override
    public void getMediaList(String bucketId, int pageSize, int currentOffset) {
        mediaSrcFactoryInteractor.generateMeidas(bucketId, pageSize, currentOffset);
    }

    @Override
    public void getBucketList() {
        mediaBucketFactoryInteractor.generateBuckets();
    }

    /**
     * MediaèŽ·å?–äº‹ä»¶å›žè°ƒ
     */
    @Override
    public void onFinished(String bucketId, int pageSize, int currentOffset, List<MediaBean> list) {
        mediaGridView.onRequestMediaCallback(list);
    }

    /**
     * BUCKETèŽ·å?–äº‹ä»¶å›žè°ƒ
     */
    @Override
    public void onFinished(List<BucketBean> list) {
        mediaGridView.onRequestBucketCallback(list);
    }
}
