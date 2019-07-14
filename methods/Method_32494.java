/** 
 * Returns a copy of this datetime with the millis of second field updated. <p> LocalDateTime is immutable, so there are no set methods. Instead, this method returns a new instance with the value of millis of second changed.
 * @param millis  the millis of second to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 */
public LocalDateTime withMillisOfSecond(int millis){
  return withLocalMillis(getChronology().millisOfSecond().set(getLocalMillis(),millis));
}
