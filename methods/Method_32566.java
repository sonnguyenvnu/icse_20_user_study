/** 
 * Sets this interval from two millisecond instants retaining the chronology.
 * @param startInstant  the start of the time interval
 * @param endInstant  the start of the time interval
 * @throws IllegalArgumentException if the end is before the start
 */
public void setInterval(long startInstant,long endInstant){
  super.setInterval(startInstant,endInstant,getChronology());
}
