private void mapSampleQueuesToMatchTrackGroups(){
  int trackGroupCount=trackGroups.length;
  trackGroupToSampleQueueIndex=new int[trackGroupCount];
  Arrays.fill(trackGroupToSampleQueueIndex,C.INDEX_UNSET);
  for (int i=0; i < trackGroupCount; i++) {
    for (int queueIndex=0; queueIndex < sampleQueues.length; queueIndex++) {
      SampleQueue sampleQueue=sampleQueues[queueIndex];
      if (formatsMatch(sampleQueue.getUpstreamFormat(),trackGroups.get(i).getFormat(0))) {
        trackGroupToSampleQueueIndex[i]=queueIndex;
        break;
      }
    }
  }
  for (  HlsSampleStream sampleStream : hlsSampleStreams) {
    sampleStream.bindSampleQueue();
  }
}
