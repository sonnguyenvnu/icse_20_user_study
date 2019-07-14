/** 
 * Create a SimpleScheduleBuilder set to repeat forever with a 1 second interval.
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatSecondlyForever(){
  return simpleSchedule().withIntervalInSeconds(1).repeatForever();
}
