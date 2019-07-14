private static int[] getAdaptiveVideoTracksForGroup(TrackGroup group,int[] formatSupport,boolean allowMixedMimeTypes,int requiredAdaptiveSupport,int maxVideoWidth,int maxVideoHeight,int maxVideoFrameRate,int maxVideoBitrate,int viewportWidth,int viewportHeight,boolean viewportOrientationMayChange){
  if (group.length < 2) {
    return NO_TRACKS;
  }
  List<Integer> selectedTrackIndices=getViewportFilteredTrackIndices(group,viewportWidth,viewportHeight,viewportOrientationMayChange);
  if (selectedTrackIndices.size() < 2) {
    return NO_TRACKS;
  }
  String selectedMimeType=null;
  if (!allowMixedMimeTypes) {
    HashSet<@NullableType String> seenMimeTypes=new HashSet<>();
    int selectedMimeTypeTrackCount=0;
    for (int i=0; i < selectedTrackIndices.size(); i++) {
      int trackIndex=selectedTrackIndices.get(i);
      String sampleMimeType=group.getFormat(trackIndex).sampleMimeType;
      if (seenMimeTypes.add(sampleMimeType)) {
        int countForMimeType=getAdaptiveVideoTrackCountForMimeType(group,formatSupport,requiredAdaptiveSupport,sampleMimeType,maxVideoWidth,maxVideoHeight,maxVideoFrameRate,maxVideoBitrate,selectedTrackIndices);
        if (countForMimeType > selectedMimeTypeTrackCount) {
          selectedMimeType=sampleMimeType;
          selectedMimeTypeTrackCount=countForMimeType;
        }
      }
    }
  }
  filterAdaptiveVideoTrackCountForMimeType(group,formatSupport,requiredAdaptiveSupport,selectedMimeType,maxVideoWidth,maxVideoHeight,maxVideoFrameRate,maxVideoBitrate,selectedTrackIndices);
  return selectedTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(selectedTrackIndices);
}
