/** 
 * Use the given TriggerKey to identify the Trigger.   <p>If none of the 'withIdentity' methods are set on the TriggerBuilder, then a random, unique TriggerKey will be generated.</p>
 * @param triggerKey the TriggerKey for the Trigger to be built
 * @return the updated TriggerBuilder
 * @see TriggerKey
 * @see Trigger#getKey()
 */
public TriggerBuilder<T> withIdentity(TriggerKey triggerKey){
  this.key=triggerKey;
  return this;
}
