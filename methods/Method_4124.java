@Override public final void onAudioDisabled(DecoderCounters counters){
  EventTime eventTime=generateLastReportedPlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDecoderDisabled(eventTime,C.TRACK_TYPE_AUDIO,counters);
  }
}
