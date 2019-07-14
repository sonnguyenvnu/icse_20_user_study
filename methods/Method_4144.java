@Override public final void onDrmSessionManagerError(Exception error){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onDrmSessionManagerError(eventTime,error);
  }
}
