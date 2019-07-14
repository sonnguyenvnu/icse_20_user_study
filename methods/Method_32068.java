/** 
 * Gets the millis, which is the Date millis value.
 * @param object  the Date to convert, must not be null
 * @param chrono  the non-null result of getChronology
 * @return the millisecond value
 * @throws NullPointerException if the object is null
 * @throws ClassCastException if the object is an invalid type
 */
public long getInstantMillis(Object object,Chronology chrono){
  Date date=(Date)object;
  return date.getTime();
}
