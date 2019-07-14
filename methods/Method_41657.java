/** 
 * Specify a repeat interval in milliseconds. 
 * @param intervalInMillis the number of seconds at which the trigger should repeat.
 * @return the updated SimpleScheduleBuilder
 * @see SimpleTrigger#getRepeatInterval()
 * @see #withRepeatCount(int)
 */
public SimpleScheduleBuilder withIntervalInMilliseconds(long intervalInMillis){
  this.interval=intervalInMillis;
  return this;
}
