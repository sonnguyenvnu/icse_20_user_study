/** 
 * Clears all sample metadata from the queue.
 * @param resetUpstreamFormat Whether the upstream format should be cleared. If set to false,samples queued after the reset (and before a subsequent call to  {@link #format(Format)}) are assumed to have the current upstream format. If set to true,  {@link #format(Format)}must be called after the reset before any more samples can be queued.
 */
public void reset(boolean resetUpstreamFormat){
  length=0;
  absoluteFirstIndex=0;
  relativeFirstIndex=0;
  readPosition=0;
  upstreamKeyframeRequired=true;
  largestDiscardedTimestampUs=Long.MIN_VALUE;
  largestQueuedTimestampUs=Long.MIN_VALUE;
  isLastSampleQueued=false;
  if (resetUpstreamFormat) {
    upstreamFormat=null;
    upstreamFormatRequired=true;
  }
}
