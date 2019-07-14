private void updatePlayerState(boolean playWhenReady,int playbackState){
  if (videoPlayer == null) {
    return;
  }
  if (isStreaming) {
    if (playbackState == ExoPlayer.STATE_BUFFERING && skipFirstBufferingProgress) {
      if (playWhenReady) {
        skipFirstBufferingProgress=false;
      }
    }
 else {
      toggleMiniProgress(seekToProgressPending != 0 || playbackState == ExoPlayer.STATE_BUFFERING,true);
    }
  }
  if (playWhenReady && (playbackState != ExoPlayer.STATE_ENDED && playbackState != ExoPlayer.STATE_IDLE)) {
    try {
      parentActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      keepScreenOnFlagSet=true;
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else {
    try {
      parentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      keepScreenOnFlagSet=false;
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  if (seekToProgressPending != 0 && (playbackState == ExoPlayer.STATE_READY || playbackState == ExoPlayer.STATE_IDLE)) {
    int seekTo=(int)(videoPlayer.getDuration() * seekToProgressPending);
    videoPlayer.seekTo(seekTo);
    seekToProgressPending=0;
    if (currentMessageObject != null && !FileLoader.getInstance(currentMessageObject.currentAccount).isLoadingVideoAny(currentMessageObject.getDocument())) {
      skipFirstBufferingProgress=true;
    }
  }
  if (playbackState == ExoPlayer.STATE_READY) {
    if (aspectRatioFrameLayout.getVisibility() != View.VISIBLE) {
      aspectRatioFrameLayout.setVisibility(View.VISIBLE);
    }
    if (!pipItem.isEnabled()) {
      pipAvailable=true;
      pipItem.setEnabled(true);
      pipItem.setAlpha(1.0f);
    }
    playerWasReady=true;
    if (currentMessageObject != null && currentMessageObject.isVideo()) {
      AndroidUtilities.cancelRunOnUIThread(setLoadingRunnable);
      FileLoader.getInstance(currentMessageObject.currentAccount).removeLoadingVideo(currentMessageObject.getDocument(),true,false);
    }
  }
 else   if (playbackState == ExoPlayer.STATE_BUFFERING) {
    if (playWhenReady && currentMessageObject != null && currentMessageObject.isVideo()) {
      if (playerWasReady) {
        setLoadingRunnable.run();
      }
 else {
        AndroidUtilities.runOnUIThread(setLoadingRunnable,1000);
      }
    }
  }
  if (videoPlayer.isPlaying() && playbackState != ExoPlayer.STATE_ENDED) {
    if (!isPlaying) {
      isPlaying=true;
      videoPlayButton.setImageResource(R.drawable.inline_video_pause);
      AndroidUtilities.runOnUIThread(updateProgressRunnable);
    }
  }
 else   if (isPlaying) {
    isPlaying=false;
    videoPlayButton.setImageResource(R.drawable.inline_video_play);
    AndroidUtilities.cancelRunOnUIThread(updateProgressRunnable);
    if (playbackState == ExoPlayer.STATE_ENDED) {
      if (isCurrentVideo) {
        if (!videoTimelineView.isDragging()) {
          videoTimelineView.setProgress(0.0f);
          if (!inPreview && videoTimelineView.getVisibility() == View.VISIBLE) {
            videoPlayer.seekTo((int)(videoTimelineView.getLeftProgress() * videoPlayer.getDuration()));
          }
 else {
            videoPlayer.seekTo(0);
          }
          videoPlayer.pause();
          containerView.invalidate();
        }
      }
 else {
        if (!isActionBarVisible) {
          toggleActionBar(true,true);
        }
        if (!videoPlayerSeekbar.isDragging()) {
          videoPlayerSeekbar.setProgress(0.0f);
          videoPlayerControlFrameLayout.invalidate();
          if (!inPreview && videoTimelineView.getVisibility() == View.VISIBLE) {
            videoPlayer.seekTo((int)(videoTimelineView.getLeftProgress() * videoPlayer.getDuration()));
          }
 else {
            videoPlayer.seekTo(0);
          }
          videoPlayer.pause();
        }
      }
      if (pipVideoView != null) {
        pipVideoView.onVideoCompleted();
      }
    }
  }
  if (pipVideoView != null) {
    pipVideoView.updatePlayButton();
  }
  updateVideoPlayerTime();
}
