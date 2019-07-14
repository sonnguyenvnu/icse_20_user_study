/** 
 * Add response prop.
 * @param key   the key
 * @param value the value
 */
public void addResponseProp(String key,String value){
  if (responseProps == null) {
    responseProps=new HashMap<String,String>(16);
  }
  if (key != null && value != null) {
    responseProps.put(key,value);
  }
}
