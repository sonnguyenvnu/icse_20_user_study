@Override public final void onDroppedFrames(int count,long elapsedMs){
  EventTime eventTime=generateLastReportedPlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDroppedVideoFrames(eventTime,count,elapsedMs);
  }
}
