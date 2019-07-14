/** 
 * Specify the time unit and interval for the Trigger to be produced.
 * @param timeInterval the interval at which the trigger should repeat.
 * @param unit  the time unit (IntervalUnit) of the interval.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getRepeatInterval()
 * @see CalendarIntervalTrigger#getRepeatIntervalUnit()
 */
public CalendarIntervalScheduleBuilder withInterval(int timeInterval,IntervalUnit unit){
  if (unit == null)   throw new IllegalArgumentException("TimeUnit must be specified.");
  validateInterval(timeInterval);
  this.interval=timeInterval;
  this.intervalUnit=unit;
  return this;
}
