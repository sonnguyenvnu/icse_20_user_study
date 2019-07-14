/** 
 * Specify an interval in the IntervalUnit.YEAR that the produced  Trigger will repeat at.
 * @param intervalInYears the number of years at which the trigger should repeat.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getRepeatInterval()
 * @see CalendarIntervalTrigger#getRepeatIntervalUnit()
 */
public CalendarIntervalScheduleBuilder withIntervalInYears(int intervalInYears){
  validateInterval(intervalInYears);
  this.interval=intervalInYears;
  this.intervalUnit=IntervalUnit.YEAR;
  return this;
}
