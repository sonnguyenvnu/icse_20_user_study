/** 
 * Gets response prop.
 * @param key the key
 * @return the response prop
 */
public Object getResponseProp(String key){
  return responseProps == null ? null : responseProps.get(key);
}
