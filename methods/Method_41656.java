/** 
 * Create a SimpleScheduleBuilder set to repeat the given number of times - 1  with an interval of the given number of hours. <p>Note: Total count = 1 (at start time) + repeat count</p>
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatHourlyForTotalCount(int count,int hours){
  if (count < 1)   throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);
  return simpleSchedule().withIntervalInHours(hours).withRepeatCount(count - 1);
}
