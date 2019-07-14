private void enableTrackSelectionsInResult(){
  TrackSelectorResult trackSelectorResult=this.trackSelectorResult;
  if (!isLoadingMediaPeriod() || trackSelectorResult == null) {
    return;
  }
  for (int i=0; i < trackSelectorResult.length; i++) {
    boolean rendererEnabled=trackSelectorResult.isRendererEnabled(i);
    TrackSelection trackSelection=trackSelectorResult.selections.get(i);
    if (rendererEnabled && trackSelection != null) {
      trackSelection.enable();
    }
  }
}
