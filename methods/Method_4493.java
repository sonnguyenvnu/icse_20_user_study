private void updateLoadControlTrackSelection(TrackGroupArray trackGroups,TrackSelectorResult trackSelectorResult){
  loadControl.onTracksSelected(renderers,trackGroups,trackSelectorResult.selections);
}
