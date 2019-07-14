@Override public final void onUpstreamDiscarded(int windowIndex,@Nullable MediaPeriodId mediaPeriodId,MediaLoadData mediaLoadData){
  EventTime eventTime=generateMediaPeriodEventTime(windowIndex,mediaPeriodId);
  for (  AnalyticsListener listener : listeners) {
    listener.onUpstreamDiscarded(eventTime,mediaLoadData);
  }
}
