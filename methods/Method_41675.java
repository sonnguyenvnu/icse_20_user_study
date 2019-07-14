/** 
 * Set the time at which the Trigger will no longer fire - even if it's schedule has remaining repeats.    
 * @param triggerEndTime the end time for the Trigger.  If null, the end time is indefinite.
 * @return the updated TriggerBuilder
 * @see Trigger#getEndTime()
 * @see DateBuilder
 */
public TriggerBuilder<T> endAt(Date triggerEndTime){
  this.endTime=triggerEndTime;
  return this;
}
