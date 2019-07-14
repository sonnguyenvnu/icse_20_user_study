/** 
 * Discards samples from the write side of the queue.
 * @param discardFromIndex The absolute index of the first sample to be discarded.
 * @return The reduced total number of bytes written after the samples have been discarded, or 0if the queue is now empty.
 */
public long discardUpstreamSamples(int discardFromIndex){
  int discardCount=getWriteIndex() - discardFromIndex;
  Assertions.checkArgument(0 <= discardCount && discardCount <= (length - readPosition));
  length-=discardCount;
  largestQueuedTimestampUs=Math.max(largestDiscardedTimestampUs,getLargestTimestamp(length));
  isLastSampleQueued=discardCount == 0 && isLastSampleQueued;
  if (length == 0) {
    return 0;
  }
 else {
    int relativeLastWriteIndex=getRelativeIndex(length - 1);
    return offsets[relativeLastWriteIndex] + sizes[relativeLastWriteIndex];
  }
}
