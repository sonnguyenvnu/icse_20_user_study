/** 
 * Called to configure a retry when a load error occurs.
 * @param loadable The current loadable for which the error was encountered.
 * @param currentExtractedSampleCount The current number of samples that have been extracted intothe sample queues.
 * @return Whether the loader should retry with the current loadable. False indicates a deferredretry.
 */
private boolean configureRetry(ExtractingLoadable loadable,int currentExtractedSampleCount){
  if (length != C.LENGTH_UNSET || (seekMap != null && seekMap.getDurationUs() != C.TIME_UNSET)) {
    extractedSamplesCountAtStartOfLoad=currentExtractedSampleCount;
    return true;
  }
 else   if (prepared && !suppressRead()) {
    pendingDeferredRetry=true;
    return false;
  }
 else {
    notifyDiscontinuity=prepared;
    lastSeekPositionUs=0;
    extractedSamplesCountAtStartOfLoad=0;
    for (    SampleQueue sampleQueue : sampleQueues) {
      sampleQueue.reset();
    }
    loadable.setLoadPosition(0,0);
    return true;
  }
}
