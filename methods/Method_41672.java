/** 
 * Set the given (human-meaningful) description of the Trigger.
 * @param triggerDescription the description for the Trigger
 * @return the updated TriggerBuilder
 * @see Trigger#getDescription()
 */
public TriggerBuilder<T> withDescription(String triggerDescription){
  this.description=triggerDescription;
  return this;
}
