private EventTime generateEventTime(@Nullable MediaPeriodInfo mediaPeriodInfo){
  Assertions.checkNotNull(player);
  if (mediaPeriodInfo == null) {
    int windowIndex=player.getCurrentWindowIndex();
    mediaPeriodInfo=mediaPeriodQueueTracker.tryResolveWindowIndex(windowIndex);
    if (mediaPeriodInfo == null) {
      Timeline timeline=player.getCurrentTimeline();
      boolean windowIsInTimeline=windowIndex < timeline.getWindowCount();
      return generateEventTime(windowIsInTimeline ? timeline : Timeline.EMPTY,windowIndex,null);
    }
  }
  return generateEventTime(mediaPeriodInfo.timeline,mediaPeriodInfo.windowIndex,mediaPeriodInfo.mediaPeriodId);
}
