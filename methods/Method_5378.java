public boolean isReady(int sampleQueueIndex){
  return loadingFinished || (!isPendingReset() && sampleQueues[sampleQueueIndex].hasNextSample());
}
