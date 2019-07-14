@Override public long getBufferedPositionUs(){
  boolean[] trackIsAudioVideoFlags=getPreparedState().trackIsAudioVideoFlags;
  if (loadingFinished) {
    return C.TIME_END_OF_SOURCE;
  }
 else   if (isPendingReset()) {
    return pendingResetPositionUs;
  }
  long largestQueuedTimestampUs=Long.MAX_VALUE;
  if (haveAudioVideoTracks) {
    largestQueuedTimestampUs=Long.MAX_VALUE;
    int trackCount=sampleQueues.length;
    for (int i=0; i < trackCount; i++) {
      if (trackIsAudioVideoFlags[i] && !sampleQueues[i].isLastSampleQueued()) {
        largestQueuedTimestampUs=Math.min(largestQueuedTimestampUs,sampleQueues[i].getLargestQueuedTimestampUs());
      }
    }
  }
  if (largestQueuedTimestampUs == Long.MAX_VALUE) {
    largestQueuedTimestampUs=getLargestQueuedTimestampUs();
  }
  return largestQueuedTimestampUs == Long.MIN_VALUE ? lastSeekPositionUs : largestQueuedTimestampUs;
}
