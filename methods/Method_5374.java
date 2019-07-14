public void unbindSampleQueue(int trackGroupIndex){
  int sampleQueueIndex=trackGroupToSampleQueueIndex[trackGroupIndex];
  Assertions.checkState(sampleQueuesEnabledStates[sampleQueueIndex]);
  sampleQueuesEnabledStates[sampleQueueIndex]=false;
}
