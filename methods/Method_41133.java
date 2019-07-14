/** 
 * Create a CronScheduleBuilder with a cron-expression that sets the schedule to fire at the given day at the given time (hour and minute) on the given days of the week.
 * @param daysOfWeek the dasy of the week to fire
 * @param hour the hour of day to fire
 * @param minute the minute of the given hour to fire
 * @return the new CronScheduleBuilder
 * @see CronExpression
 * @see DateBuilder#MONDAY
 * @see DateBuilder#TUESDAY
 * @see DateBuilder#WEDNESDAY
 * @see DateBuilder#THURSDAY
 * @see DateBuilder#FRIDAY
 * @see DateBuilder#SATURDAY
 * @see DateBuilder#SUNDAY
 */
public static CronScheduleBuilder atHourAndMinuteOnGivenDaysOfWeek(int hour,int minute,Integer... daysOfWeek){
  if (daysOfWeek == null || daysOfWeek.length == 0)   throw new IllegalArgumentException("You must specify at least one day of week.");
  for (  int dayOfWeek : daysOfWeek)   DateBuilder.validateDayOfWeek(dayOfWeek);
  DateBuilder.validateHour(hour);
  DateBuilder.validateMinute(minute);
  String cronExpression=String.format("0 %d %d ? * %d",minute,hour,daysOfWeek[0]);
  for (int i=1; i < daysOfWeek.length; i++)   cronExpression=cronExpression + "," + daysOfWeek[i];
  return cronScheduleNoParseException(cronExpression);
}
