/** 
 * Adds a key/value string pair to the request.
 * @param key   the key name for the new param.
 * @param value the value string for the new param.
 */
public void put(String key,String value){
  if (key != null && value != null) {
    urlParams.put(key,value);
  }
}
