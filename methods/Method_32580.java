/** 
 * Sets all the fields in one go from a millisecond interval.
 * @param startInstant  interval start, in milliseconds
 * @param endInstant  interval end, in milliseconds
 * @param chrono  the chronology to use, null means ISO chronology
 * @throws ArithmeticException if the set exceeds the capacity of the period
 */
public void setPeriod(long startInstant,long endInstant,Chronology chrono){
  chrono=DateTimeUtils.getChronology(chrono);
  setValues(chrono.get(this,startInstant,endInstant));
}
