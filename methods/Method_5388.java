private boolean finishedReadingChunk(HlsMediaChunk chunk){
  int chunkUid=chunk.uid;
  int sampleQueueCount=sampleQueues.length;
  for (int i=0; i < sampleQueueCount; i++) {
    if (sampleQueuesEnabledStates[i] && sampleQueues[i].peekSourceId() == chunkUid) {
      return false;
    }
  }
  return true;
}
