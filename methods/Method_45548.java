/** 
 * Sets parameter.
 * @param key   the key
 * @param value the value
 */
public MethodConfig setParameter(String key,String value){
  if (parameters == null) {
    parameters=new ConcurrentHashMap<String,String>();
  }
  parameters.put(key,value);
  return this;
}
