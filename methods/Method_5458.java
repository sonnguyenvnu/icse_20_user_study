/** 
 * Discards all samples in the queue. The read position is also advanced.
 * @return The corresponding offset up to which data should be discarded, or{@link C#POSITION_UNSET} if no discarding of data is necessary.
 */
public synchronized long discardToEnd(){
  if (length == 0) {
    return C.POSITION_UNSET;
  }
  return discardSamples(length);
}
