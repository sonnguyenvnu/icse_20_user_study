/** 
 * Gets the millis, which is the Calendar millis value.
 * @param object  the Calendar to convert, must not be null
 * @param chrono  the chronology result from getChronology, non-null
 * @return the millisecond value
 * @throws NullPointerException if the object is null
 * @throws ClassCastException if the object is an invalid type
 */
public long getInstantMillis(Object object,Chronology chrono){
  Calendar calendar=(Calendar)object;
  return calendar.getTime().getTime();
}
