/** 
 * Specify an interval in the IntervalUnit.WEEK that the produced  Trigger will repeat at.
 * @param intervalInWeeks the number of weeks at which the trigger should repeat.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getRepeatInterval()
 * @see CalendarIntervalTrigger#getRepeatIntervalUnit()
 */
public CalendarIntervalScheduleBuilder withIntervalInWeeks(int intervalInWeeks){
  validateInterval(intervalInWeeks);
  this.interval=intervalInWeeks;
  this.intervalUnit=IntervalUnit.WEEK;
  return this;
}
