private void playNextMessageWithoutOrder(boolean byStop){
  ArrayList<MessageObject> currentPlayList=SharedConfig.shuffleMusic ? shuffledPlaylist : playlist;
  if (byStop && SharedConfig.repeatMode == 2 && !forceLoopCurrentPlaylist) {
    cleanupPlayer(false,false);
    MessageObject messageObject=currentPlayList.get(currentPlaylistNum);
    messageObject.audioProgress=0;
    messageObject.audioProgressSec=0;
    playMessage(messageObject);
    return;
  }
  boolean last=false;
  if (SharedConfig.playOrderReversed) {
    currentPlaylistNum++;
    if (currentPlaylistNum >= currentPlayList.size()) {
      currentPlaylistNum=0;
      last=true;
    }
  }
 else {
    currentPlaylistNum--;
    if (currentPlaylistNum < 0) {
      currentPlaylistNum=currentPlayList.size() - 1;
      last=true;
    }
  }
  if (last && byStop && SharedConfig.repeatMode == 0 && !forceLoopCurrentPlaylist) {
    if (audioPlayer != null || videoPlayer != null) {
      if (audioPlayer != null) {
        try {
          audioPlayer.releasePlayer(true);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        audioPlayer=null;
      }
 else       if (videoPlayer != null) {
        currentAspectRatioFrameLayout=null;
        currentTextureViewContainer=null;
        currentAspectRatioFrameLayoutReady=false;
        currentTextureView=null;
        videoPlayer.releasePlayer(true);
        videoPlayer=null;
        try {
          baseActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        AndroidUtilities.cancelRunOnUIThread(setLoadingRunnable);
        FileLoader.getInstance(playingMessageObject.currentAccount).removeLoadingVideo(playingMessageObject.getDocument(),true,false);
      }
      stopProgressTimer();
      lastProgress=0;
      isPaused=true;
      playingMessageObject.audioProgress=0.0f;
      playingMessageObject.audioProgressSec=0;
      NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged,playingMessageObject.getId(),0);
      NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged,playingMessageObject.getId());
    }
    return;
  }
  if (currentPlaylistNum < 0 || currentPlaylistNum >= currentPlayList.size()) {
    return;
  }
  if (playingMessageObject != null) {
    playingMessageObject.resetPlayingProgress();
  }
  playMusicAgain=true;
  playMessage(currentPlayList.get(currentPlaylistNum));
}
