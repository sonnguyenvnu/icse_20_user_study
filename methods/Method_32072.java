/** 
 * Extracts duration values from an object of this converter's type, and sets them into the given ReadWritableDuration.
 * @param writablePeriod  period to get modified
 * @param object  the object to convert, must not be null
 * @param chrono  the chronology to use, must not be null
 * @throws NullPointerException if the duration or object is null
 * @throws ClassCastException if the object is an invalid type
 * @throws IllegalArgumentException if the object is invalid
 */
public void setInto(ReadWritablePeriod writablePeriod,Object object,Chronology chrono){
  ReadableDuration dur=(ReadableDuration)object;
  chrono=DateTimeUtils.getChronology(chrono);
  long duration=dur.getMillis();
  int[] values=chrono.get(writablePeriod,duration);
  for (int i=0; i < values.length; i++) {
    writablePeriod.setValue(i,values[i]);
  }
}
