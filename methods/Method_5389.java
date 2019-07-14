private void maybeFinishPrepare(){
  if (released || trackGroupToSampleQueueIndex != null || !sampleQueuesBuilt) {
    return;
  }
  for (  SampleQueue sampleQueue : sampleQueues) {
    if (sampleQueue.getUpstreamFormat() == null) {
      return;
    }
  }
  if (trackGroups != null) {
    mapSampleQueuesToMatchTrackGroups();
  }
 else {
    buildTracksFromSampleStreams();
    prepared=true;
    callback.onPrepared();
  }
}
