@Override public final void onRepeatModeChanged(@Player.RepeatMode int repeatMode){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onRepeatModeChanged(eventTime,repeatMode);
  }
}
