private static int[] getAdaptiveAudioTracks(TrackGroup group,int[] formatSupport,boolean allowMixedMimeTypeAdaptiveness,boolean allowMixedSampleRateAdaptiveness){
  int selectedConfigurationTrackCount=0;
  AudioConfigurationTuple selectedConfiguration=null;
  HashSet<AudioConfigurationTuple> seenConfigurationTuples=new HashSet<>();
  for (int i=0; i < group.length; i++) {
    Format format=group.getFormat(i);
    AudioConfigurationTuple configuration=new AudioConfigurationTuple(format.channelCount,format.sampleRate,format.sampleMimeType);
    if (seenConfigurationTuples.add(configuration)) {
      int configurationCount=getAdaptiveAudioTrackCount(group,formatSupport,configuration,allowMixedMimeTypeAdaptiveness,allowMixedSampleRateAdaptiveness);
      if (configurationCount > selectedConfigurationTrackCount) {
        selectedConfiguration=configuration;
        selectedConfigurationTrackCount=configurationCount;
      }
    }
  }
  if (selectedConfigurationTrackCount > 1) {
    int[] adaptiveIndices=new int[selectedConfigurationTrackCount];
    int index=0;
    for (int i=0; i < group.length; i++) {
      if (isSupportedAdaptiveAudioTrack(group.getFormat(i),formatSupport[i],Assertions.checkNotNull(selectedConfiguration),allowMixedMimeTypeAdaptiveness,allowMixedSampleRateAdaptiveness)) {
        adaptiveIndices[index++]=i;
      }
    }
    return adaptiveIndices;
  }
  return NO_TRACKS;
}
