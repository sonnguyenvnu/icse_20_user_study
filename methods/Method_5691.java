private static int getAdaptiveAudioTrackCount(TrackGroup group,int[] formatSupport,AudioConfigurationTuple configuration,boolean allowMixedMimeTypeAdaptiveness,boolean allowMixedSampleRateAdaptiveness){
  int count=0;
  for (int i=0; i < group.length; i++) {
    if (isSupportedAdaptiveAudioTrack(group.getFormat(i),formatSupport[i],configuration,allowMixedMimeTypeAdaptiveness,allowMixedSampleRateAdaptiveness)) {
      count++;
    }
  }
  return count;
}
