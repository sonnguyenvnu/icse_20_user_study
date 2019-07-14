public void bindSampleQueue(){
  Assertions.checkArgument(sampleQueueIndex == HlsSampleStreamWrapper.SAMPLE_QUEUE_INDEX_PENDING);
  sampleQueueIndex=sampleStreamWrapper.bindSampleQueueToSampleStream(trackGroupIndex);
}
