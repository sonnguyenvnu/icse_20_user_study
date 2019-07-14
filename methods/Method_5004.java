/** 
 * Runs the track selection for a given period index with the current parameters. The selected tracks will be added to  {@link #trackSelectionsByPeriodAndRenderer}.
 */
@SuppressWarnings("ReferenceEquality") @RequiresNonNull({"trackGroupArrays","trackSelectionsByPeriodAndRenderer"}) private TrackSelectorResult runTrackSelection(int periodIndex){
  MediaPeriodId dummyMediaPeriodId=new MediaPeriodId(new Object());
  Timeline dummyTimeline=Timeline.EMPTY;
  currentTrackSelectionPeriodIndex=periodIndex;
  try {
    TrackSelectorResult trackSelectorResult=trackSelector.selectTracks(rendererCapabilities,trackGroupArrays[periodIndex],dummyMediaPeriodId,dummyTimeline);
    for (int i=0; i < trackSelectorResult.length; i++) {
      TrackSelection newSelection=trackSelectorResult.selections.get(i);
      if (newSelection == null) {
        continue;
      }
      List<TrackSelection> existingSelectionList=trackSelectionsByPeriodAndRenderer[currentTrackSelectionPeriodIndex][i];
      boolean mergedWithExistingSelection=false;
      for (int j=0; j < existingSelectionList.size(); j++) {
        TrackSelection existingSelection=existingSelectionList.get(j);
        if (existingSelection.getTrackGroup() == newSelection.getTrackGroup()) {
          scratchSet.clear();
          for (int k=0; k < existingSelection.length(); k++) {
            scratchSet.put(existingSelection.getIndexInTrackGroup(k),0);
          }
          for (int k=0; k < newSelection.length(); k++) {
            scratchSet.put(newSelection.getIndexInTrackGroup(k),0);
          }
          int[] mergedTracks=new int[scratchSet.size()];
          for (int k=0; k < scratchSet.size(); k++) {
            mergedTracks[k]=scratchSet.keyAt(k);
          }
          existingSelectionList.set(j,new DownloadTrackSelection(existingSelection.getTrackGroup(),mergedTracks));
          mergedWithExistingSelection=true;
          break;
        }
      }
      if (!mergedWithExistingSelection) {
        existingSelectionList.add(newSelection);
      }
    }
    return trackSelectorResult;
  }
 catch (  ExoPlaybackException e) {
    throw new UnsupportedOperationException(e);
  }
}
