@Override public void play(QueueItem item,boolean isPlayWhenReady){
  mPlayOnFocusGain=true;
  String mediaId=item.getDescription().getMediaId();
  boolean mediaHasChanged=!TextUtils.equals(mediaId,mCurrentMediaId);
  if (mediaHasChanged) {
    mCurrentMediaId=mediaId;
  }
  if (mediaHasChanged || mExoPlayer == null) {
    releaseResources(false);
    MediaMetadataCompat track=mMusicProvider.getMusic(item.getDescription().getMediaId());
    String source=track.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI);
    if (TextUtils.isEmpty(source)) {
      return;
    }
    source=source.replaceAll(" ","%20");
    if (ExoDownload.getInstance().isOpenCache()) {
      ExoDownload.getInstance().getDownloadTracker().toggleDownload(mediaId,Uri.parse(source),"");
    }
    if (mExoPlayer == null) {
      TrackSelection.Factory trackSelectionFactory;
      if (abrAlgorithm.equals(ABR_ALGORITHM_DEFAULT)) {
        trackSelectionFactory=new AdaptiveTrackSelection.Factory();
      }
 else {
        trackSelectionFactory=new RandomTrackSelection.Factory();
      }
      @DefaultRenderersFactory.ExtensionRendererMode int extensionRendererMode=rendererMode.equals(EXTENSION_RENDERER_MODE_ON) ? DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF;
      DefaultRenderersFactory renderersFactory=new DefaultRenderersFactory(mContext,extensionRendererMode);
      trackSelector=new DefaultTrackSelector(trackSelectionFactory);
      trackSelector.setParameters(trackSelectorParameters);
      DefaultDrmSessionManager<FrameworkMediaCrypto> drmSessionManager=null;
      mExoPlayer=ExoPlayerFactory.newSimpleInstance(mContext,renderersFactory,trackSelector,drmSessionManager);
      mExoPlayer.addListener(mEventListener);
      mExoPlayer.addAnalyticsListener(new EventLogger(trackSelector));
    }
    final AudioAttributes audioAttributes=new AudioAttributes.Builder().setContentType(CONTENT_TYPE_MUSIC).setUsage(USAGE_MEDIA).build();
    mExoPlayer.setAudioAttributes(audioAttributes,true);
    DataSource.Factory dataSourceFactory=ExoDownload.getInstance().buildDataSourceFactory(mContext);
    MediaSource mediaSource=buildMediaSource(dataSourceFactory,Uri.parse(source),null);
    mExoPlayer.prepare(mediaSource);
  }
  if (isPlayWhenReady) {
    mExoPlayer.setPlayWhenReady(true);
  }
}
