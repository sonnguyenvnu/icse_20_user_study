private String getEventTimeString(EventTime eventTime){
  String windowPeriodString="window=" + eventTime.windowIndex;
  if (eventTime.mediaPeriodId != null) {
    windowPeriodString+=", period=" + eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid);
    if (eventTime.mediaPeriodId.isAd()) {
      windowPeriodString+=", adGroup=" + eventTime.mediaPeriodId.adGroupIndex;
      windowPeriodString+=", ad=" + eventTime.mediaPeriodId.adIndexInAdGroup;
    }
  }
  return getTimeString(eventTime.realtimeMs - startTimeMs) + ", " + getTimeString(eventTime.currentPlaybackPositionMs) + ", " + windowPeriodString;
}
