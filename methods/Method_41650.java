/** 
 * Create a SimpleScheduleBuilder set to repeat forever with an interval of the given number of hours.
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatHourlyForever(int hours){
  return simpleSchedule().withIntervalInHours(hours).repeatForever();
}
