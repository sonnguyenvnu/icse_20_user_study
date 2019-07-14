private void releaseDisabledStreams(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams){
  for (int i=0; i < selections.length; i++) {
    if (selections[i] == null || !mayRetainStreamFlags[i]) {
      if (streams[i] instanceof ChunkSampleStream) {
        @SuppressWarnings("unchecked") ChunkSampleStream<DashChunkSource> stream=(ChunkSampleStream<DashChunkSource>)streams[i];
        stream.release(this);
      }
 else       if (streams[i] instanceof EmbeddedSampleStream) {
        ((EmbeddedSampleStream)streams[i]).release();
      }
      streams[i]=null;
    }
  }
}
