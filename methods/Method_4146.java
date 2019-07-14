/** 
 * Returns a new  {@link EventTime} for the specified timeline, window and media period id. 
 */
@RequiresNonNull("player") protected EventTime generateEventTime(Timeline timeline,int windowIndex,@Nullable MediaPeriodId mediaPeriodId){
  if (timeline.isEmpty()) {
    mediaPeriodId=null;
  }
  long realtimeMs=clock.elapsedRealtime();
  long eventPositionMs;
  boolean isInCurrentWindow=timeline == player.getCurrentTimeline() && windowIndex == player.getCurrentWindowIndex();
  if (mediaPeriodId != null && mediaPeriodId.isAd()) {
    boolean isCurrentAd=isInCurrentWindow && player.getCurrentAdGroupIndex() == mediaPeriodId.adGroupIndex && player.getCurrentAdIndexInAdGroup() == mediaPeriodId.adIndexInAdGroup;
    eventPositionMs=isCurrentAd ? player.getCurrentPosition() : 0;
  }
 else   if (isInCurrentWindow) {
    eventPositionMs=player.getContentPosition();
  }
 else {
    eventPositionMs=timeline.isEmpty() ? 0 : timeline.getWindow(windowIndex,window).getDefaultPositionMs();
  }
  return new EventTime(realtimeMs,timeline,windowIndex,mediaPeriodId,eventPositionMs,player.getCurrentPosition(),player.getTotalBufferedDuration());
}
