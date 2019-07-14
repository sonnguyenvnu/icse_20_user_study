@Override public final void onDrmKeysLoaded(){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDrmKeysLoaded(eventTime);
  }
}
