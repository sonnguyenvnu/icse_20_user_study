@Override public long getBufferedPositionUs(){
  if (loadingFinished) {
    return C.TIME_END_OF_SOURCE;
  }
 else   if (isPendingReset()) {
    return pendingResetPositionUs;
  }
 else {
    long bufferedPositionUs=lastSeekPositionUs;
    HlsMediaChunk lastMediaChunk=getLastMediaChunk();
    HlsMediaChunk lastCompletedMediaChunk=lastMediaChunk.isLoadCompleted() ? lastMediaChunk : mediaChunks.size() > 1 ? mediaChunks.get(mediaChunks.size() - 2) : null;
    if (lastCompletedMediaChunk != null) {
      bufferedPositionUs=Math.max(bufferedPositionUs,lastCompletedMediaChunk.endTimeUs);
    }
    if (sampleQueuesBuilt) {
      for (      SampleQueue sampleQueue : sampleQueues) {
        bufferedPositionUs=Math.max(bufferedPositionUs,sampleQueue.getLargestQueuedTimestampUs());
      }
    }
    return bufferedPositionUs;
  }
}
