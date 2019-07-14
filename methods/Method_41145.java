/** 
 * Set the trigger to fire on the days Saturday and Sunday.
 * @return the updated DailyTimeIntervalScheduleBuilder
 */
public DailyTimeIntervalScheduleBuilder onSaturdayAndSunday(){
  this.daysOfWeek=SATURDAY_AND_SUNDAY;
  return this;
}
