/** 
 * Set the date from milliseconds. The time part of this object will be unaffected.
 * @param instant  an instant to copy the date from, time part ignored
 * @throws IllegalArgumentException if the value is invalid
 */
public void setDate(final long instant){
  setMillis(getChronology().millisOfDay().set(instant,getMillisOfDay()));
}
