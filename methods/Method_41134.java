/** 
 * Create a CronScheduleBuilder with a cron-expression that sets the schedule to fire one per week on the given day at the given time (hour and minute).
 * @param dayOfWeek the day of the week to fire
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
public static CronScheduleBuilder weeklyOnDayAndHourAndMinute(int dayOfWeek,int hour,int minute){
  DateBuilder.validateDayOfWeek(dayOfWeek);
  DateBuilder.validateHour(hour);
  DateBuilder.validateMinute(minute);
  String cronExpression=String.format("0 %d %d ? * %d",minute,hour,dayOfWeek);
  return cronScheduleNoParseException(cronExpression);
}
