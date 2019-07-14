/** 
 * Discards samples up to but not including the read position.
 * @return The corresponding offset up to which data should be discarded, or{@link C#POSITION_UNSET} if no discarding of data is necessary.
 */
public synchronized long discardToRead(){
  if (readPosition == 0) {
    return C.POSITION_UNSET;
  }
  return discardSamples(readPosition);
}
