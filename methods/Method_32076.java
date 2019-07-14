/** 
 * Extracts duration values from an object of this converter's type, and sets them into the given ReadWritablePeriod.
 * @param duration duration to get modified
 * @param object  the object to convert, must not be null
 * @param chrono  the chronology to use
 * @throws NullPointerException if the duration or object is null
 * @throws ClassCastException if the object is an invalid type
 * @throws IllegalArgumentException if the object is invalid
 */
public void setInto(ReadWritablePeriod duration,Object object,Chronology chrono){
  duration.setPeriod((ReadablePeriod)object);
}
