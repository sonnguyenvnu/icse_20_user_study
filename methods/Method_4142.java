@Override public final void onSeekProcessed(){
  if (mediaPeriodQueueTracker.isSeeking()) {
    mediaPeriodQueueTracker.onSeekProcessed();
    EventTime eventTime=generatePlayingMediaPeriodEventTime();
    for (    AnalyticsListener listener : listeners) {
      listener.onSeekProcessed(eventTime);
    }
  }
}
