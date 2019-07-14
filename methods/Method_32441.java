/** 
 * Gets a copy of this instant with the specified duration added. <p> If the addition is zero, then <code>this</code> is returned.
 * @param durationToAdd  the duration to add to this one
 * @param scalar  the amount of times to add, such as -1 to subtract once
 * @return a copy of this instant with the duration added
 * @throws ArithmeticException if the new instant exceeds the capacity of a long
 */
public Instant withDurationAdded(long durationToAdd,int scalar){
  if (durationToAdd == 0 || scalar == 0) {
    return this;
  }
  long instant=getChronology().add(getMillis(),durationToAdd,scalar);
  return withMillis(instant);
}
