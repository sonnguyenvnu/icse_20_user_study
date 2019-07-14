/** 
 * Specify a the number of time the trigger will repeat - total number of  firings will be this number + 1. 
 * @param triggerRepeatCount the number of seconds at which the trigger should repeat.
 * @return the updated SimpleScheduleBuilder
 * @see SimpleTrigger#getRepeatCount()
 * @see #repeatForever()
 */
public SimpleScheduleBuilder withRepeatCount(int triggerRepeatCount){
  this.repeatCount=triggerRepeatCount;
  return this;
}
