/** 
 * Extracts the millis from an object of this converter's type.
 * @param object  the ReadableInstant to convert, must not be null
 * @param chrono  the non-null result of getChronology
 * @return the millisecond value
 * @throws NullPointerException if the object is null
 * @throws ClassCastException if the object is an invalid type
 */
public long getInstantMillis(Object object,Chronology chrono){
  return ((ReadableInstant)object).getMillis();
}
