@Override public final void onDrmKeysRestored(){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDrmKeysRestored(eventTime);
  }
}
