private void releaseOrphanEmbeddedStreams(TrackSelection[] selections,SampleStream[] streams,int[] streamIndexToTrackGroupIndex){
  for (int i=0; i < selections.length; i++) {
    if (streams[i] instanceof EmptySampleStream || streams[i] instanceof EmbeddedSampleStream) {
      int primaryStreamIndex=getPrimaryStreamIndex(i,streamIndexToTrackGroupIndex);
      boolean mayRetainStream;
      if (primaryStreamIndex == C.INDEX_UNSET) {
        mayRetainStream=streams[i] instanceof EmptySampleStream;
      }
 else {
        mayRetainStream=(streams[i] instanceof EmbeddedSampleStream) && ((EmbeddedSampleStream)streams[i]).parent == streams[primaryStreamIndex];
      }
      if (!mayRetainStream) {
        if (streams[i] instanceof EmbeddedSampleStream) {
          ((EmbeddedSampleStream)streams[i]).release();
        }
        streams[i]=null;
      }
    }
  }
}
