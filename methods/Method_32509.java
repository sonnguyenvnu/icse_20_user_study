/** 
 * Returns a copy of this time with the second of minute field updated. <p> LocalTime is immutable, so there are no set methods. Instead, this method returns a new instance with the value of second of minute changed.
 * @param second  the second of minute to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 */
public LocalTime withSecondOfMinute(int second){
  return withLocalMillis(getChronology().secondOfMinute().set(getLocalMillis(),second));
}
