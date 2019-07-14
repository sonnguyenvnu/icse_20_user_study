private EventTime generateMediaPeriodEventTime(int windowIndex,@Nullable MediaPeriodId mediaPeriodId){
  Assertions.checkNotNull(player);
  if (mediaPeriodId != null) {
    MediaPeriodInfo mediaPeriodInfo=mediaPeriodQueueTracker.getMediaPeriodInfo(mediaPeriodId);
    return mediaPeriodInfo != null ? generateEventTime(mediaPeriodInfo) : generateEventTime(Timeline.EMPTY,windowIndex,mediaPeriodId);
  }
  Timeline timeline=player.getCurrentTimeline();
  boolean windowIsInTimeline=windowIndex < timeline.getWindowCount();
  return generateEventTime(windowIsInTimeline ? timeline : Timeline.EMPTY,windowIndex,null);
}
