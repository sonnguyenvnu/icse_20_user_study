/** 
 * Create a TimeOfDay instance for the given hour and minute (at the zero second of the minute).
 * @param hour The hour of day, between 0 and 23.
 * @param minute The minute of the hour, between 0 and 59.
 * @throws IllegalArgumentException if one or more of the input values is out of their valid range.
 */
public static TimeOfDay hourAndMinuteOfDay(int hour,int minute){
  return new TimeOfDay(hour,minute);
}
