@Override public final void onLoadCanceled(int windowIndex,@Nullable MediaPeriodId mediaPeriodId,LoadEventInfo loadEventInfo,MediaLoadData mediaLoadData){
  EventTime eventTime=generateMediaPeriodEventTime(windowIndex,mediaPeriodId);
  for (  AnalyticsListener listener : listeners) {
    listener.onLoadCanceled(eventTime,loadEventInfo,mediaLoadData);
  }
}
