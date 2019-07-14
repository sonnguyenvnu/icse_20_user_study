@Override public final void onPlayerStateChanged(boolean playWhenReady,int playbackState){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onPlayerStateChanged(eventTime,playWhenReady,playbackState);
  }
}
