private void updateVideoState(MessageObject messageObject,int playCount[],boolean destroyAtEnd,boolean playWhenReady,int playbackState){
  if (videoPlayer == null) {
    return;
  }
  if (playbackState != ExoPlayer.STATE_ENDED && playbackState != ExoPlayer.STATE_IDLE) {
    try {
      baseActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else {
    try {
      baseActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  if (playbackState == ExoPlayer.STATE_READY) {
    playerWasReady=true;
    if (playingMessageObject != null && (playingMessageObject.isVideo() || playingMessageObject.isRoundVideo())) {
      AndroidUtilities.cancelRunOnUIThread(setLoadingRunnable);
      FileLoader.getInstance(messageObject.currentAccount).removeLoadingVideo(playingMessageObject.getDocument(),true,false);
    }
    currentAspectRatioFrameLayoutReady=true;
  }
 else   if (playbackState == ExoPlayer.STATE_BUFFERING) {
    if (playWhenReady && playingMessageObject != null && (playingMessageObject.isVideo() || playingMessageObject.isRoundVideo())) {
      if (playerWasReady) {
        setLoadingRunnable.run();
      }
 else {
        AndroidUtilities.runOnUIThread(setLoadingRunnable,1000);
      }
    }
  }
 else   if (videoPlayer.isPlaying() && playbackState == ExoPlayer.STATE_ENDED) {
    if (playingMessageObject.isVideo() && !destroyAtEnd && (playCount == null || playCount[0] < 4)) {
      videoPlayer.seekTo(0);
      if (playCount != null) {
        playCount[0]++;
      }
    }
 else {
      cleanupPlayer(true,true,true,false);
    }
  }
}
