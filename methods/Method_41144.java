/** 
 * Set the trigger to fire on the days from Monday through Friday.
 * @return the updated DailyTimeIntervalScheduleBuilder
 */
public DailyTimeIntervalScheduleBuilder onMondayThroughFriday(){
  this.daysOfWeek=MONDAY_THROUGH_FRIDAY;
  return this;
}
