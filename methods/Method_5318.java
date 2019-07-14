private void maybeStartDeferredRetry(int track){
  boolean[] trackIsAudioVideoFlags=getPreparedState().trackIsAudioVideoFlags;
  if (!pendingDeferredRetry || !trackIsAudioVideoFlags[track] || sampleQueues[track].hasNextSample()) {
    return;
  }
  pendingResetPositionUs=0;
  pendingDeferredRetry=false;
  notifyDiscontinuity=true;
  lastSeekPositionUs=0;
  extractedSamplesCountAtStartOfLoad=0;
  for (  SampleQueue sampleQueue : sampleQueues) {
    sampleQueue.reset();
  }
  Assertions.checkNotNull(callback).onContinueLoadingRequested(this);
}
