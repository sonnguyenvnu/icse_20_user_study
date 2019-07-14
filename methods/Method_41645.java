/** 
 * Create a SimpleScheduleBuilder set to repeat forever with a 1 minute interval.
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatMinutelyForever(){
  return simpleSchedule().withIntervalInMinutes(1).repeatForever();
}
