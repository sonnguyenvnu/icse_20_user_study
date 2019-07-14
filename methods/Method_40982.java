/** 
 * Specify an interval in the IntervalUnit.SECOND that the produced  Trigger will repeat at.
 * @param intervalInSeconds the number of seconds at which the trigger should repeat.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getRepeatInterval()
 * @see CalendarIntervalTrigger#getRepeatIntervalUnit()
 */
public CalendarIntervalScheduleBuilder withIntervalInSeconds(int intervalInSeconds){
  validateInterval(intervalInSeconds);
  this.interval=intervalInSeconds;
  this.intervalUnit=IntervalUnit.SECOND;
  return this;
}
