/** 
 * Create a CronScheduleBuilder with a cron-expression that sets the schedule to fire one per month on the given day of month at the given time (hour and minute).
 * @param dayOfMonth the day of the month to fire
 * @param hour the hour of day to fire
 * @param minute the minute of the given hour to fire
 * @return the new CronScheduleBuilder
 * @see CronExpression
 */
public static CronScheduleBuilder monthlyOnDayAndHourAndMinute(int dayOfMonth,int hour,int minute){
  DateBuilder.validateDayOfMonth(dayOfMonth);
  DateBuilder.validateHour(hour);
  DateBuilder.validateMinute(minute);
  String cronExpression=String.format("0 %d %d %d * ?",minute,hour,dayOfMonth);
  return cronScheduleNoParseException(cronExpression);
}
