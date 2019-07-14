/** 
 * Sets parameter.
 * @param key   the key
 * @param value the value
 * @return the RegistryConfig
 */
public RegistryConfig setParameter(String key,String value){
  if (parameters == null) {
    parameters=new ConcurrentHashMap<String,String>();
  }
  parameters.put(key,value);
  return this;
}
