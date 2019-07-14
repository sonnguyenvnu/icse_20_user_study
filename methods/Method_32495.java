/** 
 * Returns a copy of this datetime with the millis of day field updated. <p> LocalDateTime is immutable, so there are no set methods. Instead, this method returns a new instance with the value of millis of day changed.
 * @param millis  the millis of day to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 */
public LocalDateTime withMillisOfDay(int millis){
  return withLocalMillis(getChronology().millisOfDay().set(getLocalMillis(),millis));
}
