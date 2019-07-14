/** 
 * Create a CronScheduleBuilder with the given cron-expression string - which is presumed to be a valid cron expression (and hence only a RuntimeException will be thrown if it is not).
 * @param cronExpression the cron expression string to base the schedule on.
 * @return the new CronScheduleBuilder
 * @throws RuntimeException wrapping a ParseException if the expression is invalid
 * @see CronExpression
 */
public static CronScheduleBuilder cronSchedule(String cronExpression){
  try {
    return cronSchedule(new CronExpression(cronExpression));
  }
 catch (  ParseException e) {
    throw new RuntimeException("CronExpression '" + cronExpression + "' is invalid.",e);
  }
}
