/** 
 * Specify a repeat interval in minutes - which will then be multiplied by 60 * 1000 to produce milliseconds. 
 * @param intervalInMinutes the number of seconds at which the trigger should repeat.
 * @return the updated SimpleScheduleBuilder
 * @see SimpleTrigger#getRepeatInterval()
 * @see #withRepeatCount(int)
 */
public SimpleScheduleBuilder withIntervalInMinutes(int intervalInMinutes){
  this.interval=intervalInMinutes * DateBuilder.MILLISECONDS_IN_MINUTE;
  return this;
}
