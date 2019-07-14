private void startAudioAgain(boolean paused){
  if (playingMessageObject == null) {
    return;
  }
  NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.audioRouteChanged,useFrontSpeaker);
  if (videoPlayer != null) {
    videoPlayer.setStreamType(useFrontSpeaker ? AudioManager.STREAM_VOICE_CALL : AudioManager.STREAM_MUSIC);
    if (!paused) {
      videoPlayer.play();
    }
 else {
      pauseMessage(playingMessageObject);
    }
  }
 else {
    boolean post=audioPlayer != null;
    final MessageObject currentMessageObject=playingMessageObject;
    float progress=playingMessageObject.audioProgress;
    cleanupPlayer(false,true);
    currentMessageObject.audioProgress=progress;
    playMessage(currentMessageObject);
    if (paused) {
      if (post) {
        AndroidUtilities.runOnUIThread(() -> pauseMessage(currentMessageObject),100);
      }
 else {
        pauseMessage(currentMessageObject);
      }
    }
  }
}
