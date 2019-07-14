private void maybeUpdateSourceInfo(){
  if (adPlaybackState != null && contentTimeline != null) {
    adPlaybackState=adPlaybackState.withAdDurationsUs(getAdDurations(adGroupTimelines,period));
    Timeline timeline=adPlaybackState.adGroupCount == 0 ? contentTimeline : new SinglePeriodAdTimeline(contentTimeline,adPlaybackState);
    refreshSourceInfo(timeline,contentManifest);
  }
}
