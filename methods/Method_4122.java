@Override public final void onAudioInputFormatChanged(Format format){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDecoderInputFormatChanged(eventTime,C.TRACK_TYPE_AUDIO,format);
  }
}
