private void setOverride(int group,int[] tracks,boolean enableRandomAdaptation){
  TrackSelection.Factory factory=tracks.length == 1 ? FIXED_FACTORY : (enableRandomAdaptation ? RANDOM_FACTORY : adaptiveVideoTrackSelectionFactory);
  override=new SelectionOverride(factory,group,tracks);
}
