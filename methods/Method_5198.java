@Override public long seekToUs(long positionUs){
  for (  ChunkSampleStream<DashChunkSource> sampleStream : sampleStreams) {
    sampleStream.seekToUs(positionUs);
  }
  for (  EventSampleStream sampleStream : eventSampleStreams) {
    sampleStream.seekToUs(positionUs);
  }
  return positionUs;
}
