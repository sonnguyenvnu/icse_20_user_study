@Override public long seekToUs(long positionUs){
  pendingInitialDiscontinuityPositionUs=C.TIME_UNSET;
  for (  ClippingSampleStream sampleStream : sampleStreams) {
    if (sampleStream != null) {
      sampleStream.clearSentEos();
    }
  }
  long seekUs=mediaPeriod.seekToUs(positionUs);
  Assertions.checkState(seekUs == positionUs || (seekUs >= startUs && (endUs == C.TIME_END_OF_SOURCE || seekUs <= endUs)));
  return seekUs;
}
