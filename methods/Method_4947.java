private boolean isLastInTimeline(MediaPeriodId id,boolean isLastMediaPeriodInPeriod){
  int periodIndex=timeline.getIndexOfPeriod(id.periodUid);
  int windowIndex=timeline.getPeriod(periodIndex,period).windowIndex;
  return !timeline.getWindow(windowIndex,window).isDynamic && timeline.isLastPeriod(periodIndex,period,window,repeatMode,shuffleModeEnabled) && isLastMediaPeriodInPeriod;
}
