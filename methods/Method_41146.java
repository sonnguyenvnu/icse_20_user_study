/** 
 * Set the trigger to begin firing each day at the given time.
 * @return the updated DailyTimeIntervalScheduleBuilder
 */
public DailyTimeIntervalScheduleBuilder startingDailyAt(TimeOfDay timeOfDay){
  if (timeOfDay == null)   throw new IllegalArgumentException("Start time of day cannot be null!");
  this.startTimeOfDay=timeOfDay;
  return this;
}
