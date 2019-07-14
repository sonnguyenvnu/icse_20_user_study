@Nullable private static TrackSelection.Definition selectAdaptiveVideoTrack(TrackGroupArray groups,int[][] formatSupport,int mixedMimeTypeAdaptationSupports,Parameters params){
  int requiredAdaptiveSupport=params.allowVideoNonSeamlessAdaptiveness ? (RendererCapabilities.ADAPTIVE_NOT_SEAMLESS | RendererCapabilities.ADAPTIVE_SEAMLESS) : RendererCapabilities.ADAPTIVE_SEAMLESS;
  boolean allowMixedMimeTypes=params.allowVideoMixedMimeTypeAdaptiveness && (mixedMimeTypeAdaptationSupports & requiredAdaptiveSupport) != 0;
  for (int i=0; i < groups.length; i++) {
    TrackGroup group=groups.get(i);
    int[] adaptiveTracks=getAdaptiveVideoTracksForGroup(group,formatSupport[i],allowMixedMimeTypes,requiredAdaptiveSupport,params.maxVideoWidth,params.maxVideoHeight,params.maxVideoFrameRate,params.maxVideoBitrate,params.viewportWidth,params.viewportHeight,params.viewportOrientationMayChange);
    if (adaptiveTracks.length > 0) {
      return new TrackSelection.Definition(group,adaptiveTracks);
    }
  }
  return null;
}
