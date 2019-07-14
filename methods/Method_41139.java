/** 
 * Specify the time unit and interval for the Trigger to be produced.
 * @param timeInterval the interval at which the trigger should repeat.
 * @param unit the time unit (IntervalUnit) of the interval. The only intervals that are valid for this type of trigger are  {@link IntervalUnit#SECOND},  {@link IntervalUnit#MINUTE}, and  {@link IntervalUnit#HOUR}.
 * @return the updated DailyTimeIntervalScheduleBuilder
 * @see DailyTimeIntervalTrigger#getRepeatInterval()
 * @see DailyTimeIntervalTrigger#getRepeatIntervalUnit()
 */
public DailyTimeIntervalScheduleBuilder withInterval(int timeInterval,IntervalUnit unit){
  if (unit == null || !(unit.equals(IntervalUnit.SECOND) || unit.equals(IntervalUnit.MINUTE) || unit.equals(IntervalUnit.HOUR)))   throw new IllegalArgumentException("Invalid repeat IntervalUnit (must be SECOND, MINUTE or HOUR).");
  validateInterval(timeInterval);
  this.interval=timeInterval;
  this.intervalUnit=unit;
  return this;
}
