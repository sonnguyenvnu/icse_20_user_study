/** 
 * Specify that the trigger will repeat indefinitely. 
 * @return the updated SimpleScheduleBuilder
 * @see SimpleTrigger#getRepeatCount()
 * @see SimpleTrigger#REPEAT_INDEFINITELY
 * @see #withIntervalInMilliseconds(long)
 * @see #withIntervalInSeconds(int)
 * @see #withIntervalInMinutes(int)
 * @see #withIntervalInHours(int)
 */
public SimpleScheduleBuilder repeatForever(){
  this.repeatCount=SimpleTrigger.REPEAT_INDEFINITELY;
  return this;
}
