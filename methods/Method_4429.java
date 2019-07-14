@Override public void stop(boolean reset){
  if (reset) {
    playbackError=null;
    mediaSource=null;
  }
  PlaybackInfo playbackInfo=getResetPlaybackInfo(reset,reset,Player.STATE_IDLE);
  pendingOperationAcks++;
  internalPlayer.stop(reset);
  updatePlaybackInfo(playbackInfo,false,DISCONTINUITY_REASON_INTERNAL,TIMELINE_CHANGE_REASON_RESET,false);
}
