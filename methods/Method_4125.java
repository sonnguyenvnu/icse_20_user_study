@Override public void onAudioAttributesChanged(AudioAttributes audioAttributes){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onAudioAttributesChanged(eventTime,audioAttributes);
  }
}
