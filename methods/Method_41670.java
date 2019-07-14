/** 
 * Use a TriggerKey with the given name and group to identify the Trigger. <p>If none of the 'withIdentity' methods are set on the TriggerBuilder, then a random, unique TriggerKey will be generated.</p>
 * @param name the name element for the Trigger's TriggerKey
 * @param group the group element for the Trigger's TriggerKey
 * @return the updated TriggerBuilder
 * @see TriggerKey
 * @see Trigger#getKey()
 */
public TriggerBuilder<T> withIdentity(String name,String group){
  key=new TriggerKey(name,group);
  return this;
}
