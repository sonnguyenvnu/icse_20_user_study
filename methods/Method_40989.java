/** 
 * The <code>TimeZone</code> in which to base the schedule.
 * @param timezone the time-zone for the schedule.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#getTimeZone()
 */
public CalendarIntervalScheduleBuilder inTimeZone(TimeZone timezone){
  this.timeZone=timezone;
  return this;
}
