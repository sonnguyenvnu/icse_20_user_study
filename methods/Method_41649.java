/** 
 * Create a SimpleScheduleBuilder set to repeat forever with a 1 hour interval.
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatHourlyForever(){
  return simpleSchedule().withIntervalInHours(1).repeatForever();
}
