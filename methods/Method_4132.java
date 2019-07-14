@Override public final void onMediaPeriodCreated(int windowIndex,MediaPeriodId mediaPeriodId){
  mediaPeriodQueueTracker.onMediaPeriodCreated(windowIndex,mediaPeriodId);
  EventTime eventTime=generateMediaPeriodEventTime(windowIndex,mediaPeriodId);
  for (  AnalyticsListener listener : listeners) {
    listener.onMediaPeriodCreated(eventTime);
  }
}
