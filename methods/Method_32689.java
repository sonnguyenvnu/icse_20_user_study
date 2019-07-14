/** 
 * Returns a copy of this time with the millis of second field updated. <p> TimeOfDay is immutable, so there are no set methods. Instead, this method returns a new instance with the value of millis of second changed.
 * @param millis  the millis of second to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 * @since 1.3
 */
public TimeOfDay withMillisOfSecond(int millis){
  int[] newValues=getValues();
  newValues=getChronology().millisOfSecond().set(this,MILLIS_OF_SECOND,newValues,millis);
  return new TimeOfDay(this,newValues);
}
