@Override public final void onTracksChanged(TrackGroupArray trackGroups,TrackSelectionArray trackSelections){
  EventTime eventTime=generatePlayingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onTracksChanged(eventTime,trackGroups,trackSelections);
  }
}
