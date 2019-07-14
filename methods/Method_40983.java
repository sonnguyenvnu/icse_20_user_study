/** 
 * Specify an interval in the IntervalUnit.MINUTE that the produced  Trigger will repeat at.
 * @param intervalInMinutes the number of minutes at which the trigger should repeat.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getRepeatInterval()
 * @see CalendarIntervalTrigger#getRepeatIntervalUnit()
 */
public CalendarIntervalScheduleBuilder withIntervalInMinutes(int intervalInMinutes){
  validateInterval(intervalInMinutes);
  this.interval=intervalInMinutes;
  this.intervalUnit=IntervalUnit.MINUTE;
  return this;
}
