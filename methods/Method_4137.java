@Override public final void onLoadingChanged(boolean isLoading){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onLoadingChanged(eventTime,isLoading);
  }
}
