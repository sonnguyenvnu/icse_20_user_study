public int bindSampleQueueToSampleStream(int trackGroupIndex){
  int sampleQueueIndex=trackGroupToSampleQueueIndex[trackGroupIndex];
  if (sampleQueueIndex == C.INDEX_UNSET) {
    return optionalTrackGroups.indexOf(trackGroups.get(trackGroupIndex)) == C.INDEX_UNSET ? SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL : SAMPLE_QUEUE_INDEX_NO_MAPPING_NON_FATAL;
  }
  if (sampleQueuesEnabledStates[sampleQueueIndex]) {
    return SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL;
  }
  sampleQueuesEnabledStates[sampleQueueIndex]=true;
  return sampleQueueIndex;
}
