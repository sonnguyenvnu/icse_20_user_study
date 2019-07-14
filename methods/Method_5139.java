/** 
 * Returns an estimate of the position up to which data is buffered.
 * @return An estimate of the absolute position in microseconds up to which data is buffered, or{@link C#TIME_END_OF_SOURCE} if the track is fully buffered.
 */
@Override public long getBufferedPositionUs(){
  if (loadingFinished) {
    return C.TIME_END_OF_SOURCE;
  }
 else   if (isPendingReset()) {
    return pendingResetPositionUs;
  }
 else {
    long bufferedPositionUs=lastSeekPositionUs;
    BaseMediaChunk lastMediaChunk=getLastMediaChunk();
    BaseMediaChunk lastCompletedMediaChunk=lastMediaChunk.isLoadCompleted() ? lastMediaChunk : mediaChunks.size() > 1 ? mediaChunks.get(mediaChunks.size() - 2) : null;
    if (lastCompletedMediaChunk != null) {
      bufferedPositionUs=Math.max(bufferedPositionUs,lastCompletedMediaChunk.endTimeUs);
    }
    return Math.max(bufferedPositionUs,primarySampleQueue.getLargestQueuedTimestampUs());
  }
}
