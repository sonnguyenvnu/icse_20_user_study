/** 
 * Use a <code>TriggerKey</code> with the given name and default group to identify the Trigger. <p>If none of the 'withIdentity' methods are set on the TriggerBuilder, then a random, unique TriggerKey will be generated.</p>
 * @param name the name element for the Trigger's TriggerKey
 * @return the updated TriggerBuilder
 * @see TriggerKey
 * @see Trigger#getKey()
 */
public TriggerBuilder<T> withIdentity(String name){
  key=new TriggerKey(name,null);
  return this;
}
