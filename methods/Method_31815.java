/** 
 * Is this interval equal to the specified interval ignoring the chronology. <p> This compares the underlying instants, ignoring the chronology.
 * @param other  a readable interval to check against
 * @return true if the intervals are equal comparing the start and end millis
 * @since 2.3
 */
public boolean isEqual(ReadableInterval other){
  return getStartMillis() == other.getStartMillis() && getEndMillis() == other.getEndMillis();
}
