@Override public void maybeThrowError() throws IOException {
  if (sampleQueueIndex == HlsSampleStreamWrapper.SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL) {
    throw new SampleQueueMappingException(sampleStreamWrapper.getTrackGroups().get(trackGroupIndex).getFormat(0).sampleMimeType);
  }
  sampleStreamWrapper.maybeThrowError();
}
