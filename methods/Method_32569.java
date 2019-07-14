/** 
 * Sets the end of this time interval.
 * @param endInstant  the end of the time interval,millisecond instant from 1970-01-01T00:00:00Z
 * @throws IllegalArgumentException if the end is before the start
 */
public void setEndMillis(long endInstant){
  super.setInterval(getStartMillis(),endInstant,getChronology());
}
