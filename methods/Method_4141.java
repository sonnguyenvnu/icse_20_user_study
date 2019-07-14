@Override public final void onPositionDiscontinuity(@Player.DiscontinuityReason int reason){
  mediaPeriodQueueTracker.onPositionDiscontinuity(reason);
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onPositionDiscontinuity(eventTime,reason);
  }
}
