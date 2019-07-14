/** 
 * Creates a new interval with the specified duration before the end instant.
 * @param duration  the duration to subtract from the end to get the new start instant, null means zero
 * @return an interval with the end from this interval and a calculated start
 * @throws IllegalArgumentException if the duration is negative
 */
public Interval withDurationBeforeEnd(ReadableDuration duration){
  long durationMillis=DateTimeUtils.getDurationMillis(duration);
  if (durationMillis == toDurationMillis()) {
    return this;
  }
  Chronology chrono=getChronology();
  long endMillis=getEndMillis();
  long startMillis=chrono.add(endMillis,durationMillis,-1);
  return new Interval(startMillis,endMillis,chrono);
}
