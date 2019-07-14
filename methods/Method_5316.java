int skipData(int track,long positionUs){
  if (suppressRead()) {
    return 0;
  }
  maybeNotifyDownstreamFormat(track);
  SampleQueue sampleQueue=sampleQueues[track];
  int skipCount;
  if (loadingFinished && positionUs > sampleQueue.getLargestQueuedTimestampUs()) {
    skipCount=sampleQueue.advanceToEnd();
  }
 else {
    skipCount=sampleQueue.advanceTo(positionUs,true,true);
    if (skipCount == SampleQueue.ADVANCE_FAILED) {
      skipCount=0;
    }
  }
  if (skipCount == 0) {
    maybeStartDeferredRetry(track);
  }
  return skipCount;
}
