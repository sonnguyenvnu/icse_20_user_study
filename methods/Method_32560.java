/** 
 * Set the time from milliseconds. The date part of this object will be unaffected.
 * @param millis  an instant to copy the time from, date part ignored
 * @throws IllegalArgumentException if the value is invalid
 */
public void setTime(final long millis){
  int millisOfDay=ISOChronology.getInstanceUTC().millisOfDay().get(millis);
  setMillis(getChronology().millisOfDay().set(getMillis(),millisOfDay));
}
