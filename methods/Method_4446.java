private void handlePlaybackInfo(PlaybackInfo playbackInfo,int operationAcks,boolean positionDiscontinuity,@DiscontinuityReason int positionDiscontinuityReason){
  pendingOperationAcks-=operationAcks;
  if (pendingOperationAcks == 0) {
    if (playbackInfo.startPositionUs == C.TIME_UNSET) {
      playbackInfo=playbackInfo.resetToNewPosition(playbackInfo.periodId,0,playbackInfo.contentPositionUs);
    }
    if ((!this.playbackInfo.timeline.isEmpty() || hasPendingPrepare) && playbackInfo.timeline.isEmpty()) {
      maskingPeriodIndex=0;
      maskingWindowIndex=0;
      maskingWindowPositionMs=0;
    }
    @Player.TimelineChangeReason int timelineChangeReason=hasPendingPrepare ? Player.TIMELINE_CHANGE_REASON_PREPARED : Player.TIMELINE_CHANGE_REASON_DYNAMIC;
    boolean seekProcessed=hasPendingSeek;
    hasPendingPrepare=false;
    hasPendingSeek=false;
    updatePlaybackInfo(playbackInfo,positionDiscontinuity,positionDiscontinuityReason,timelineChangeReason,seekProcessed);
  }
}
