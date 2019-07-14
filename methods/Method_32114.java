/** 
 * Returns a copy of this datetime with the second of minute field updated. <p> DateTime is immutable, so there are no set methods. Instead, this method returns a new instance with the value of second of minute changed.
 * @param second  the second of minute to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 * @since 1.3
 */
public DateTime withSecondOfMinute(int second){
  return withMillis(getChronology().secondOfMinute().set(getMillis(),second));
}
