@Override public void releasePeriod(MediaPeriod mediaPeriod){
  Assertions.checkState(mediaPeriods.remove(mediaPeriod));
  mediaSource.releasePeriod(((ClippingMediaPeriod)mediaPeriod).mediaPeriod);
  if (mediaPeriods.isEmpty() && !allowDynamicClippingUpdates) {
    refreshClippedTimeline(clippingTimeline.timeline);
  }
}
