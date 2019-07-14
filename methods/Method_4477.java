private void notifyTrackSelectionDiscontinuity(){
  MediaPeriodHolder periodHolder=queue.getFrontPeriod();
  while (periodHolder != null) {
    TrackSelectorResult trackSelectorResult=periodHolder.getTrackSelectorResult();
    if (trackSelectorResult != null) {
      TrackSelection[] trackSelections=trackSelectorResult.selections.getAll();
      for (      TrackSelection trackSelection : trackSelections) {
        if (trackSelection != null) {
          trackSelection.onDiscontinuity();
        }
      }
    }
    periodHolder=periodHolder.getNext();
  }
}
