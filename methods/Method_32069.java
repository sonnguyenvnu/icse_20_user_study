/** 
 * Sets the given ReadWritableDuration to zero milliseconds.
 * @param duration duration to get modified
 * @param object  the object to convert, which is null
 * @param chrono  the chronology to use
 * @throws NullPointerException if the duration is null
 */
public void setInto(ReadWritablePeriod duration,Object object,Chronology chrono){
  duration.setPeriod((Period)null);
}
