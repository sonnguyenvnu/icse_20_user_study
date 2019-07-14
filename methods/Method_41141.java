/** 
 * Specify an interval in the IntervalUnit.HOUR that the produced  Trigger will repeat at.
 * @param intervalInHours the number of hours at which the trigger should repeat.
 * @return the updated DailyTimeIntervalScheduleBuilder
 * @see DailyTimeIntervalTrigger#getRepeatInterval()
 * @see DailyTimeIntervalTrigger#getRepeatIntervalUnit()
 */
public DailyTimeIntervalScheduleBuilder withIntervalInHours(int intervalInHours){
  withInterval(intervalInHours,IntervalUnit.HOUR);
  return this;
}
