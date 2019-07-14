/** 
 * Sets the desired result of the first call to  {@link #adjustSampleTimestamp(long)}. Can only be called before any timestamps have been adjusted.
 * @param firstSampleTimestampUs The first adjusted sample timestamp in microseconds, or{@link #DO_NOT_OFFSET} if presentation timestamps should not be offset.
 */
public synchronized void setFirstSampleTimestampUs(long firstSampleTimestampUs){
  Assertions.checkState(lastSampleTimestampUs == C.TIME_UNSET);
  this.firstSampleTimestampUs=firstSampleTimestampUs;
}
