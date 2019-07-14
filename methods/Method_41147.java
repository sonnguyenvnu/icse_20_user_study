/** 
 * Set the startTimeOfDay for this trigger to end firing each day at the given time.
 * @return the updated DailyTimeIntervalScheduleBuilder
 */
public DailyTimeIntervalScheduleBuilder endingDailyAt(TimeOfDay timeOfDay){
  this.endTimeOfDay=timeOfDay;
  return this;
}
