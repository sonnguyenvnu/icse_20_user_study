public boolean resumeAudio(MessageObject messageObject){
  if (audioPlayer == null && videoPlayer == null || messageObject == null || playingMessageObject == null || !isSamePlayingMessage(messageObject)) {
    return false;
  }
  try {
    startProgressTimer(playingMessageObject);
    if (audioPlayer != null) {
      audioPlayer.play();
    }
 else     if (videoPlayer != null) {
      videoPlayer.play();
    }
    checkAudioFocus(messageObject);
    isPaused=false;
    NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged,playingMessageObject.getId());
  }
 catch (  Exception e) {
    FileLog.e(e);
    return false;
  }
  return true;
}
