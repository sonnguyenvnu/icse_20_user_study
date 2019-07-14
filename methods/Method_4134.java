@Override public final void onLoadError(int windowIndex,@Nullable MediaPeriodId mediaPeriodId,LoadEventInfo loadEventInfo,MediaLoadData mediaLoadData,IOException error,boolean wasCanceled){
  EventTime eventTime=generateMediaPeriodEventTime(windowIndex,mediaPeriodId);
  for (  AnalyticsListener listener : listeners) {
    listener.onLoadError(eventTime,loadEventInfo,mediaLoadData,error,wasCanceled);
  }
}
