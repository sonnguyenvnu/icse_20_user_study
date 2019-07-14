@Override public final void onVideoInputFormatChanged(Format format){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDecoderInputFormatChanged(eventTime,C.TRACK_TYPE_VIDEO,format);
  }
}
