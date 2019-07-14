private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup group,int[] formatSupport,int requiredAdaptiveSupport,@Nullable String mimeType,int maxVideoWidth,int maxVideoHeight,int maxVideoFrameRate,int maxVideoBitrate,List<Integer> selectedTrackIndices){
  for (int i=selectedTrackIndices.size() - 1; i >= 0; i--) {
    int trackIndex=selectedTrackIndices.get(i);
    if (!isSupportedAdaptiveVideoTrack(group.getFormat(trackIndex),mimeType,formatSupport[trackIndex],requiredAdaptiveSupport,maxVideoWidth,maxVideoHeight,maxVideoFrameRate,maxVideoBitrate)) {
      selectedTrackIndices.remove(i);
    }
  }
}
