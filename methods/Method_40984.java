/** 
 * Specify an interval in the IntervalUnit.DAY that the produced  Trigger will repeat at.
 * @param intervalInDays the number of days at which the trigger should repeat.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getRepeatInterval()
 * @see CalendarIntervalTrigger#getRepeatIntervalUnit()
 */
public CalendarIntervalScheduleBuilder withIntervalInDays(int intervalInDays){
  validateInterval(intervalInDays);
  this.interval=intervalInDays;
  this.intervalUnit=IntervalUnit.DAY;
  return this;
}
