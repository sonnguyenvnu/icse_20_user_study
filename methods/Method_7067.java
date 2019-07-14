private void updatePlaybackState(String error){
  long position=PlaybackState.PLAYBACK_POSITION_UNKNOWN;
  MessageObject playingMessageObject=MediaController.getInstance().getPlayingMessageObject();
  if (playingMessageObject != null) {
    position=playingMessageObject.audioProgressSec * 1000L;
  }
  PlaybackState.Builder stateBuilder=new PlaybackState.Builder().setActions(getAvailableActions());
  int state;
  if (playingMessageObject == null) {
    state=PlaybackState.STATE_STOPPED;
  }
 else {
    if (MediaController.getInstance().isDownloadingCurrentMessage()) {
      state=PlaybackState.STATE_BUFFERING;
    }
 else {
      state=MediaController.getInstance().isMessagePaused() ? PlaybackState.STATE_PAUSED : PlaybackState.STATE_PLAYING;
    }
  }
  if (error != null) {
    stateBuilder.setErrorMessage(error);
    state=PlaybackState.STATE_ERROR;
  }
  stateBuilder.setState(state,position,1.0f,SystemClock.elapsedRealtime());
  if (playingMessageObject != null) {
    stateBuilder.setActiveQueueItemId(MediaController.getInstance().getPlayingMessageObjectNum());
  }
 else {
    stateBuilder.setActiveQueueItemId(0);
  }
  mediaSession.setPlaybackState(stateBuilder.build());
}
