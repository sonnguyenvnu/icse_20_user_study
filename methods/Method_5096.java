@Override public TrackSelectionArray getCurrentTrackSelections(){
  verifyApplicationThread();
  return player.getCurrentTrackSelections();
}
