@SuppressLint("NewApi") private void preparePlayer(File file,boolean playWhenReady){
  if (parentActivity == null) {
    return;
  }
  releasePlayer();
  if (videoTextureView == null) {
    aspectRatioFrameLayout=new AspectRatioFrameLayout(parentActivity);
    aspectRatioFrameLayout.setVisibility(View.INVISIBLE);
    photoContainerView.addView(aspectRatioFrameLayout,0,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
    videoTextureView=new TextureView(parentActivity);
    videoTextureView.setOpaque(false);
    aspectRatioFrameLayout.addView(videoTextureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
  }
  textureUploaded=false;
  videoCrossfadeStarted=false;
  videoTextureView.setAlpha(videoCrossfadeAlpha=0.0f);
  videoPlayButton.setImageResource(R.drawable.inline_video_play);
  if (videoPlayer == null) {
    videoPlayer=new VideoPlayer();
    videoPlayer.setTextureView(videoTextureView);
    videoPlayer.setDelegate(new VideoPlayer.VideoPlayerDelegate(){
      @Override public void onStateChanged(      boolean playWhenReady,      int playbackState){
        if (videoPlayer == null) {
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
            videoPlayButton.setImageResource(R.drawable.inline_video_pause);
            AndroidUtilities.runOnUIThread(updateProgressRunnable);
          }
        }
 else         if (isPlaying) {
          isPlaying=false;
          videoPlayButton.setImageResource(R.drawable.inline_video_play);
          AndroidUtilities.cancelRunOnUIThread(updateProgressRunnable);
          if (playbackState == ExoPlayer.STATE_ENDED) {
            if (!videoPlayerSeekbar.isDragging()) {
              videoPlayerSeekbar.setProgress(0.0f);
              videoPlayerControlFrameLayout.invalidate();
              videoPlayer.seekTo(0);
              videoPlayer.pause();
            }
          }
        }
        updateVideoPlayerTime();
      }
      @Override public void onError(      Exception e){
        FileLog.e(e);
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
    long duration;
    if (videoPlayer != null) {
      duration=videoPlayer.getDuration();
      if (duration == C.TIME_UNSET) {
        duration=0;
      }
    }
 else {
      duration=0;
    }
    duration/=1000;
    int size=(int)Math.ceil(videoPlayerTime.getPaint().measureText(String.format("%02d:%02d / %02d:%02d",duration / 60,duration % 60,duration / 60,duration % 60)));
  }
  videoPlayer.preparePlayer(Uri.fromFile(file),"other");
  bottomLayout.setVisibility(View.VISIBLE);
  videoPlayer.setPlayWhenReady(playWhenReady);
}
