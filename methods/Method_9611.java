private void preparePlayer(File file){
  if (parentActivity == null) {
    return;
  }
  releasePlayer();
  if (videoTextureView == null) {
    aspectRatioFrameLayout=new AspectRatioFrameLayout(parentActivity);
    aspectRatioFrameLayout.setVisibility(View.INVISIBLE);
    containerView.addView(aspectRatioFrameLayout,0,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
    videoTextureView=new TextureView(parentActivity);
    videoTextureView.setOpaque(false);
    aspectRatioFrameLayout.addView(videoTextureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
  }
  textureUploaded=false;
  videoCrossfadeStarted=false;
  videoTextureView.setAlpha(videoCrossfadeAlpha=0.0f);
  if (videoPlayer == null) {
    videoPlayer=new VideoPlayer();
    videoPlayer.setTextureView(videoTextureView);
    videoPlayer.setDelegate(new VideoPlayer.VideoPlayerDelegate(){
      @Override public void onStateChanged(      boolean playWhenReady,      int playbackState){
        if (videoPlayer == null || currentMessageObject == null) {
          return;
        }
        if (playbackState != ExoPlayer.STATE_ENDED && playbackState != ExoPlayer.STATE_IDLE) {
          try {
            parentActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
 else {
          try {
            parentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        if (playbackState == ExoPlayer.STATE_READY && aspectRatioFrameLayout.getVisibility() != View.VISIBLE) {
          aspectRatioFrameLayout.setVisibility(View.VISIBLE);
        }
        if (videoPlayer.isPlaying() && playbackState != ExoPlayer.STATE_ENDED) {
          if (!isPlaying) {
            isPlaying=true;
          }
        }
 else         if (isPlaying) {
          isPlaying=false;
          if (playbackState == ExoPlayer.STATE_ENDED) {
            videoWatchedOneTime=true;
            if (closeVideoAfterWatch) {
              closePhoto(true,true);
            }
 else {
              videoPlayer.seekTo(0);
              videoPlayer.play();
            }
          }
        }
      }
      @Override public void onError(      Exception e){
        if (playerRetryPlayCount > 0) {
          playerRetryPlayCount--;
          AndroidUtilities.runOnUIThread(() -> preparePlayer(file),100);
        }
 else {
          FileLog.e(e);
        }
      }
      @Override public void onVideoSizeChanged(      int width,      int height,      int unappliedRotationDegrees,      float pixelWidthHeightRatio){
        if (aspectRatioFrameLayout != null) {
          if (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270) {
            int temp=width;
            width=height;
            height=temp;
          }
          aspectRatioFrameLayout.setAspectRatio(height == 0 ? 1 : (width * pixelWidthHeightRatio) / height,unappliedRotationDegrees);
        }
      }
      @Override public void onRenderedFirstFrame(){
        if (!textureUploaded) {
          textureUploaded=true;
          containerView.invalidate();
        }
      }
      @Override public boolean onSurfaceDestroyed(      SurfaceTexture surfaceTexture){
        return false;
      }
      @Override public void onSurfaceTextureUpdated(      SurfaceTexture surfaceTexture){
      }
    }
);
  }
  videoPlayer.preparePlayer(Uri.fromFile(file),"other");
  videoPlayer.setPlayWhenReady(true);
}
