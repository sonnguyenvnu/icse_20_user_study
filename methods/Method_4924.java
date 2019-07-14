/** 
 * For each renderer of type  {@link C#TRACK_TYPE_NONE}, we will remove the dummy  {@link EmptySampleStream} that was associated with it.
 */
private void disassociateNoSampleRenderersWithEmptySampleStream(@NullableType SampleStream[] sampleStreams){
  for (int i=0; i < rendererCapabilities.length; i++) {
    if (rendererCapabilities[i].getTrackType() == C.TRACK_TYPE_NONE) {
      sampleStreams[i]=null;
    }
  }
}
