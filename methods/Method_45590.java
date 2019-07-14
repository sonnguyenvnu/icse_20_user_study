/** 
 * Add request prop.
 * @param key   the key
 * @param value the value
 */
public void addRequestProp(String key,Object value){
  if (key == null || value == null) {
    return;
  }
  if (requestProps == null) {
    requestProps=new HashMap<String,Object>(16);
  }
  requestProps.put(key,value);
}
