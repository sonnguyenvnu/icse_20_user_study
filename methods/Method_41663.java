/** 
 * Create a TimeOfDay instance for the given hour, minute and second.
 * @param hour The hour of day, between 0 and 23.
 * @param minute The minute of the hour, between 0 and 59.
 * @param second The second of the minute, between 0 and 59.
 * @throws IllegalArgumentException if one or more of the input values is out of their valid range.
 */
public static TimeOfDay hourMinuteAndSecondOfDay(int hour,int minute,int second){
  return new TimeOfDay(hour,minute,second);
}
