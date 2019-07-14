@Override public final void onShuffleModeEnabledChanged(boolean shuffleModeEnabled){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onShuffleModeChanged(eventTime,shuffleModeEnabled);
  }
}
