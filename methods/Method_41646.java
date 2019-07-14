/** 
 * Create a SimpleScheduleBuilder set to repeat forever with an interval of the given number of minutes.
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatMinutelyForever(int minutes){
  return simpleSchedule().withIntervalInMinutes(minutes).repeatForever();
}
