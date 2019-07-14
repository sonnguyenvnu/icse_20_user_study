/** 
 * Set the time the Trigger should start at - the trigger may or may not fire at this time - depending upon the schedule configured for the Trigger.  However the Trigger will NOT fire before this time, regardless of the Trigger's schedule.
 * @param triggerStartTime the start time for the Trigger.
 * @return the updated TriggerBuilder
 * @see Trigger#getStartTime()
 * @see DateBuilder
 */
public TriggerBuilder<T> startAt(Date triggerStartTime){
  this.startTime=triggerStartTime;
  return this;
}
