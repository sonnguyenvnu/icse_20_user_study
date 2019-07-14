/** 
 * Sets all the fields in one go from a millisecond duration. <p> When dividing the duration, only precise fields in the period type will be used. For large durations, all the remaining duration will be stored in the largest available precise field.
 * @param duration  the duration, in milliseconds
 * @param chrono  the chronology to use, null means ISO chronology
 * @throws ArithmeticException if the set exceeds the capacity of the period
 */
public void setPeriod(long duration,Chronology chrono){
  chrono=DateTimeUtils.getChronology(chrono);
  setValues(chrono.get(this,duration));
}
