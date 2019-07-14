private void updateTrackSelectionPlaybackSpeed(float playbackSpeed){
  MediaPeriodHolder periodHolder=queue.getFrontPeriod();
  while (periodHolder != null && periodHolder.prepared) {
    TrackSelection[] trackSelections=periodHolder.getTrackSelectorResult().selections.getAll();
    for (    TrackSelection trackSelection : trackSelections) {
      if (trackSelection != null) {
        trackSelection.onPlaybackSpeed(playbackSpeed);
      }
    }
    periodHolder=periodHolder.getNext();
  }
}
