/** 
 * Sets all the fields in one go from a duration dividing the fields using the period type. <p> When dividing the duration, only precise fields in the period type will be used. For large durations, all the remaining duration will be stored in the largest available precise field.
 * @param duration  the duration to set, null means zero length
 * @param chrono  the chronology to use, null means ISO default
 * @throws ArithmeticException if the set exceeds the capacity of the period
 */
public void setPeriod(ReadableDuration duration,Chronology chrono){
  long durationMillis=DateTimeUtils.getDurationMillis(duration);
  setPeriod(durationMillis,chrono);
}
