/** 
 * Creates a new interval with the specified duration after the start instant.
 * @param duration  the duration to add to the start to get the new end instant, null means zero
 * @return an interval with the start from this interval and a calculated end
 * @throws IllegalArgumentException if the duration is negative
 */
public Interval withDurationAfterStart(ReadableDuration duration){
  long durationMillis=DateTimeUtils.getDurationMillis(duration);
  if (durationMillis == toDurationMillis()) {
    return this;
  }
  Chronology chrono=getChronology();
  long startMillis=getStartMillis();
  long endMillis=chrono.add(startMillis,durationMillis,1);
  return new Interval(startMillis,endMillis,chrono);
}
