@Override public final void onRenderedFirstFrame(@Nullable Surface surface){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onRenderedFirstFrame(eventTime,surface);
  }
}
