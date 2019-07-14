@Override public void prepare(MediaSource mediaSource,boolean resetPosition,boolean resetState){
  playbackError=null;
  this.mediaSource=mediaSource;
  PlaybackInfo playbackInfo=getResetPlaybackInfo(resetPosition,resetState,Player.STATE_BUFFERING);
  hasPendingPrepare=true;
  pendingOperationAcks++;
  internalPlayer.prepare(mediaSource,resetPosition,resetState);
  updatePlaybackInfo(playbackInfo,false,DISCONTINUITY_REASON_INTERNAL,TIMELINE_CHANGE_REASON_RESET,false);
}
