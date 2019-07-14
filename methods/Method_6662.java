public boolean seekToProgress(MessageObject messageObject,float progress){
  if (audioPlayer == null && videoPlayer == null || messageObject == null || playingMessageObject == null || !isSamePlayingMessage(messageObject)) {
    return false;
  }
  try {
    if (audioPlayer != null) {
      long duration=audioPlayer.getDuration();
      if (duration == C.TIME_UNSET) {
        seekToProgressPending=progress;
      }
 else {
        int seekTo=(int)(duration * progress);
        audioPlayer.seekTo(seekTo);
        lastProgress=seekTo;
      }
    }
 else     if (videoPlayer != null) {
      videoPlayer.seekTo((long)(videoPlayer.getDuration() * progress));
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return false;
  }
  NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidSeek,playingMessageObject.getId(),progress);
  return true;
}
