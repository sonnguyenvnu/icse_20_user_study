@Override public final void onAudioEnabled(DecoderCounters counters){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDecoderEnabled(eventTime,C.TRACK_TYPE_AUDIO,counters);
  }
}
