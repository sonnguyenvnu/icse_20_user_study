@Override public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> stream){
  PlayerTrackEmsgHandler trackEmsgHandler=trackEmsgHandlerBySampleStream.remove(stream);
  if (trackEmsgHandler != null) {
    trackEmsgHandler.release();
  }
}
