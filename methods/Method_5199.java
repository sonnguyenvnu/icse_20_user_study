private int[] getStreamIndexToTrackGroupIndex(TrackSelection[] selections){
  int[] streamIndexToTrackGroupIndex=new int[selections.length];
  for (int i=0; i < selections.length; i++) {
    if (selections[i] != null) {
      streamIndexToTrackGroupIndex[i]=trackGroups.indexOf(selections[i].getTrackGroup());
    }
 else {
      streamIndexToTrackGroupIndex[i]=C.INDEX_UNSET;
    }
  }
  return streamIndexToTrackGroupIndex;
}
