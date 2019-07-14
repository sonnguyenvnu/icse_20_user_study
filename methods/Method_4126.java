@Override public final void onVideoEnabled(DecoderCounters counters){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDecoderEnabled(eventTime,C.TRACK_TYPE_VIDEO,counters);
  }
}
