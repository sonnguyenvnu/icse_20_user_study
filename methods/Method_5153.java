@Override public long selectTracks(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams,boolean[] streamResetFlags,long positionUs){
  sampleStreams=new ClippingSampleStream[streams.length];
  SampleStream[] childStreams=new SampleStream[streams.length];
  for (int i=0; i < streams.length; i++) {
    sampleStreams[i]=(ClippingSampleStream)streams[i];
    childStreams[i]=sampleStreams[i] != null ? sampleStreams[i].childStream : null;
  }
  long enablePositionUs=mediaPeriod.selectTracks(selections,mayRetainStreamFlags,childStreams,streamResetFlags,positionUs);
  pendingInitialDiscontinuityPositionUs=isPendingInitialDiscontinuity() && positionUs == startUs && shouldKeepInitialDiscontinuity(startUs,selections) ? enablePositionUs : C.TIME_UNSET;
  Assertions.checkState(enablePositionUs == positionUs || (enablePositionUs >= startUs && (endUs == C.TIME_END_OF_SOURCE || enablePositionUs <= endUs)));
  for (int i=0; i < streams.length; i++) {
    if (childStreams[i] == null) {
      sampleStreams[i]=null;
    }
 else     if (streams[i] == null || sampleStreams[i].childStream != childStreams[i]) {
      sampleStreams[i]=new ClippingSampleStream(childStreams[i]);
    }
    streams[i]=sampleStreams[i];
  }
  return enablePositionUs;
}
