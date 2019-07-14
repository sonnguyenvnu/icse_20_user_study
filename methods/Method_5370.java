private boolean hasValidSampleQueueIndex(){
  return sampleQueueIndex != HlsSampleStreamWrapper.SAMPLE_QUEUE_INDEX_PENDING && sampleQueueIndex != HlsSampleStreamWrapper.SAMPLE_QUEUE_INDEX_NO_MAPPING_NON_FATAL && sampleQueueIndex != HlsSampleStreamWrapper.SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL;
}
