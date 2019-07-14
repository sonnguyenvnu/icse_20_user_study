/** 
 * Extracts the millis from an object of this converter's type. <p> This implementation returns the current time.
 * @param object  the object to convert
 * @param chrono  the chronology to use, which is always non-null
 * @return the millisecond value
 */
public long getInstantMillis(Object object,Chronology chrono){
  return DateTimeUtils.currentTimeMillis();
}
