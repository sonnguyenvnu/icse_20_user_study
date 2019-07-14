/** 
 * Specify a repeat interval in minutes - which will then be multiplied by 60 * 60 * 1000 to produce milliseconds. 
 * @param intervalInHours the number of seconds at which the trigger should repeat.
 * @return the updated SimpleScheduleBuilder
 * @see SimpleTrigger#getRepeatInterval()
 * @see #withRepeatCount(int)
 */
public SimpleScheduleBuilder withIntervalInHours(int intervalInHours){
  this.interval=intervalInHours * DateBuilder.MILLISECONDS_IN_HOUR;
  return this;
}
