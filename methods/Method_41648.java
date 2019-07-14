/** 
 * Create a SimpleScheduleBuilder set to repeat forever with an interval of the given number of seconds.
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatSecondlyForever(int seconds){
  return simpleSchedule().withIntervalInSeconds(seconds).repeatForever();
}
