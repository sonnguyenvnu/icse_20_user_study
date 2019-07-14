@Override public boolean isReady(){
  return sampleQueueIndex == HlsSampleStreamWrapper.SAMPLE_QUEUE_INDEX_NO_MAPPING_NON_FATAL || (hasValidSampleQueueIndex() && sampleStreamWrapper.isReady(sampleQueueIndex));
}
