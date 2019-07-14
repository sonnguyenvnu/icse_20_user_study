/** 
 * Create a SimpleScheduleBuilder set to repeat the given number of times - 1  with a 1 second interval. <p>Note: Total count = 1 (at start time) + repeat count</p>
 * @return the new SimpleScheduleBuilder
 */
public static SimpleScheduleBuilder repeatSecondlyForTotalCount(int count){
  if (count < 1)   throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);
  return simpleSchedule().withIntervalInSeconds(1).withRepeatCount(count - 1);
}
