@Override public final void onVideoDisabled(DecoderCounters counters){
  EventTime eventTime=generateLastReportedPlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDecoderDisabled(eventTime,C.TRACK_TYPE_VIDEO,counters);
  }
}
