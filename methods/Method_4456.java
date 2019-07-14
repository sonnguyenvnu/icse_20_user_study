private void maybeNotifyPlaybackInfoChanged(){
  if (playbackInfoUpdate.hasPendingUpdate(playbackInfo)) {
    eventHandler.obtainMessage(MSG_PLAYBACK_INFO_CHANGED,playbackInfoUpdate.operationAcks,playbackInfoUpdate.positionDiscontinuity ? playbackInfoUpdate.discontinuityReason : C.INDEX_UNSET,playbackInfo).sendToTarget();
    playbackInfoUpdate.reset(playbackInfo);
  }
}
