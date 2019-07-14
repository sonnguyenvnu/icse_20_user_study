/** 
 * Gets request prop.
 * @param key the key
 * @return request prop
 */
public Object getRequestProp(String key){
  return requestProps != null ? requestProps.get(key) : null;
}
