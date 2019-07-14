/** 
 * Extracts the millis from an object of this converter's type.
 * @param object  the object to convert, must not be null
 * @return the millisecond value
 * @throws NullPointerException if the object is null
 * @throws ClassCastException if the object is an invalid type
 * @throws IllegalArgumentException if the object is invalid
 */
public long getDurationMillis(Object object){
  return ((ReadableDuration)object).getMillis();
}
