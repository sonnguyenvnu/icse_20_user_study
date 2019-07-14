/** 
 * Specify an interval in the IntervalUnit.MINUTE that the produced  Trigger will repeat at.
 * @param intervalInMinutes the number of minutes at which the trigger should repeat.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see DailyTimeIntervalTrigger#getRepeatInterval()
 * @see DailyTimeIntervalTrigger#getRepeatIntervalUnit()
 */
public DailyTimeIntervalScheduleBuilder withIntervalInMinutes(int intervalInMinutes){
  withInterval(intervalInMinutes,IntervalUnit.MINUTE);
  return this;
}
