/** 
 * Discards the specified number of samples.
 * @param discardCount The number of samples to discard.
 * @return The corresponding offset up to which data should be discarded.
 */
private long discardSamples(int discardCount){
  largestDiscardedTimestampUs=Math.max(largestDiscardedTimestampUs,getLargestTimestamp(discardCount));
  length-=discardCount;
  absoluteFirstIndex+=discardCount;
  relativeFirstIndex+=discardCount;
  if (relativeFirstIndex >= capacity) {
    relativeFirstIndex-=capacity;
  }
  readPosition-=discardCount;
  if (readPosition < 0) {
    readPosition=0;
  }
  if (length == 0) {
    int relativeLastDiscardIndex=(relativeFirstIndex == 0 ? capacity : relativeFirstIndex) - 1;
    return offsets[relativeLastDiscardIndex] + sizes[relativeLastDiscardIndex];
  }
 else {
    return offsets[relativeFirstIndex];
  }
}
