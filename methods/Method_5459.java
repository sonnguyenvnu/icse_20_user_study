/** 
 * Attempts to discard samples from the end of the queue to allow samples starting from the specified timestamp to be spliced in. Samples will not be discarded prior to the read position.
 * @param timeUs The timestamp at which the splice occurs.
 * @return Whether the splice was successful.
 */
public synchronized boolean attemptSplice(long timeUs){
  if (length == 0) {
    return timeUs > largestDiscardedTimestampUs;
  }
  long largestReadTimestampUs=Math.max(largestDiscardedTimestampUs,getLargestTimestamp(readPosition));
  if (largestReadTimestampUs >= timeUs) {
    return false;
  }
  int retainCount=length;
  int relativeSampleIndex=getRelativeIndex(length - 1);
  while (retainCount > readPosition && timesUs[relativeSampleIndex] >= timeUs) {
    retainCount--;
    relativeSampleIndex--;
    if (relativeSampleIndex == -1) {
      relativeSampleIndex=capacity - 1;
    }
  }
  discardUpstreamSamples(absoluteFirstIndex + retainCount);
  return true;
}
