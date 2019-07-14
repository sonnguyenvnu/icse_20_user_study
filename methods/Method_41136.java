/** 
 * The <code>TimeZone</code> in which to base the schedule.
 * @param timezone the time-zone for the schedule.
 * @return the updated CronScheduleBuilder
 * @see CronExpression#getTimeZone()
 */
public CronScheduleBuilder inTimeZone(TimeZone timezone){
  cronExpression.setTimeZone(timezone);
  return this;
}
