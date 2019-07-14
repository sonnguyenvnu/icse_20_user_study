public int skipData(int sampleQueueIndex,long positionUs){
  if (isPendingReset()) {
    return 0;
  }
  SampleQueue sampleQueue=sampleQueues[sampleQueueIndex];
  if (loadingFinished && positionUs > sampleQueue.getLargestQueuedTimestampUs()) {
    return sampleQueue.advanceToEnd();
  }
 else {
    int skipCount=sampleQueue.advanceTo(positionUs,true,true);
    return skipCount == SampleQueue.ADVANCE_FAILED ? 0 : skipCount;
  }
}
