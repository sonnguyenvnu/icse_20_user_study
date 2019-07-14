public void injectVideoPlayer(VideoPlayer player,MessageObject messageObject){
  if (player == null || messageObject == null) {
    return;
  }
  FileLoader.getInstance(messageObject.currentAccount).setLoadingVideoForPlayer(messageObject.getDocument(),true);
  playerWasReady=false;
  boolean destroyAtEnd=true;
  int[] playCount=null;
  playlist.clear();
  shuffledPlaylist.clear();
  videoPlayer=player;
  playingMessageObject=messageObject;
  videoPlayer.setDelegate(new VideoPlayer.VideoPlayerDelegate(){
    @Override public void onStateChanged(    boolean playWhenReady,    int playbackState){
      updateVideoState(messageObject,playCount,destroyAtEnd,playWhenReady,playbackState);
    }
    @Override public void onError(    Exception e){
      FileLog.e(e);
    }
    @Override public void onVideoSizeChanged(    int width,    int height,    int unappliedRotationDegrees,    float pixelWidthHeightRatio){
      currentAspectRatioFrameLayoutRotation=unappliedRotationDegrees;
      if (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270) {
        int temp=width;
        width=height;
        height=temp;
      }
      currentAspectRatioFrameLayoutRatio=height == 0 ? 1 : (width * pixelWidthHeightRatio) / height;
      if (currentAspectRatioFrameLayout != null) {
        currentAspectRatioFrameLayout.setAspectRatio(currentAspectRatioFrameLayoutRatio,currentAspectRatioFrameLayoutRotation);
      }
    }
    @Override public void onRenderedFirstFrame(){
      if (currentAspectRatioFrameLayout != null && !currentAspectRatioFrameLayout.isDrawingReady()) {
        isDrawingWasReady=true;
        currentAspectRatioFrameLayout.setDrawingReady(true);
        currentTextureViewContainer.setTag(1);
      }
    }
    @Override public boolean onSurfaceDestroyed(    SurfaceTexture surfaceTexture){
      if (videoPlayer == null) {
        return false;
      }
      if (pipSwitchingState == 2) {
        if (currentAspectRatioFrameLayout != null) {
          if (isDrawingWasReady) {
            currentAspectRatioFrameLayout.setDrawingReady(true);
          }
          if (currentAspectRatioFrameLayout.getParent() == null) {
            currentTextureViewContainer.addView(currentAspectRatioFrameLayout);
          }
          if (currentTextureView.getSurfaceTexture() != surfaceTexture) {
            currentTextureView.setSurfaceTexture(surfaceTexture);
          }
          videoPlayer.setTextureView(currentTextureView);
        }
        pipSwitchingState=0;
        return true;
      }
 else       if (pipSwitchingState == 1) {
        if (baseActivity != null) {
          if (pipRoundVideoView == null) {
            try {
              pipRoundVideoView=new PipRoundVideoView();
              pipRoundVideoView.show(baseActivity,() -> cleanupPlayer(true,true));
            }
 catch (            Exception e) {
              pipRoundVideoView=null;
            }
          }
          if (pipRoundVideoView != null) {
            if (pipRoundVideoView.getTextureView().getSurfaceTexture() != surfaceTexture) {
              pipRoundVideoView.getTextureView().setSurfaceTexture(surfaceTexture);
            }
            videoPlayer.setTextureView(pipRoundVideoView.getTextureView());
          }
        }
        pipSwitchingState=0;
        return true;
      }
 else       if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isInjectingVideoPlayer()) {
        PhotoViewer.getInstance().injectVideoPlayerSurface(surfaceTexture);
        return true;
      }
      return false;
    }
    @Override public void onSurfaceTextureUpdated(    SurfaceTexture surfaceTexture){
    }
  }
);
  currentAspectRatioFrameLayoutReady=false;
  if (currentTextureView != null) {
    videoPlayer.setTextureView(currentTextureView);
  }
  checkAudioFocus(messageObject);
  setPlayerVolume();
  isPaused=false;
  lastProgress=0;
  playingMessageObject=messageObject;
  if (!SharedConfig.raiseToSpeak) {
    startRaiseToEarSensors(raiseChat);
  }
  startProgressTimer(playingMessageObject);
  NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidStart,messageObject);
}
