@SuppressWarnings("unchecked") @EnsuresNonNull("trackSelectionsByPeriodAndRenderer") private void initializeTrackSelectionLists(int periodCount,int rendererCount){
  trackSelectionsByPeriodAndRenderer=(List<TrackSelection>[][])new List<?>[periodCount][rendererCount];
  immutableTrackSelectionsByPeriodAndRenderer=(List<TrackSelection>[][])new List<?>[periodCount][rendererCount];
  for (int i=0; i < periodCount; i++) {
    for (int j=0; j < rendererCount; j++) {
      trackSelectionsByPeriodAndRenderer[i][j]=new ArrayList<>();
      immutableTrackSelectionsByPeriodAndRenderer[i][j]=Collections.unmodifiableList(trackSelectionsByPeriodAndRenderer[i][j]);
    }
  }
}
