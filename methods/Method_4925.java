/** 
 * For each renderer of type  {@link C#TRACK_TYPE_NONE} that was enabled, we will associate it witha dummy  {@link EmptySampleStream}.
 */
private void associateNoSampleRenderersWithEmptySampleStream(@NullableType SampleStream[] sampleStreams){
  TrackSelectorResult trackSelectorResult=Assertions.checkNotNull(this.trackSelectorResult);
  for (int i=0; i < rendererCapabilities.length; i++) {
    if (rendererCapabilities[i].getTrackType() == C.TRACK_TYPE_NONE && trackSelectorResult.isRendererEnabled(i)) {
      sampleStreams[i]=new EmptySampleStream();
    }
  }
}
