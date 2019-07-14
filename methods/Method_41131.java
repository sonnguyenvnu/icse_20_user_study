/** 
 * Create a CronScheduleBuilder with the given cron-expression string - which may not be a valid cron expression (and hence a ParseException will be thrown if it is not).
 * @param cronExpression the cron expression string to base the schedule on.
 * @return the new CronScheduleBuilder
 * @throws ParseException if the expression is invalid
 * @see CronExpression
 */
public static CronScheduleBuilder cronScheduleNonvalidatedExpression(String cronExpression) throws ParseException {
  return cronSchedule(new CronExpression(cronExpression));
}
