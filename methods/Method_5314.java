boolean isReady(int track){
  return !suppressRead() && (loadingFinished || sampleQueues[track].hasNextSample());
}
