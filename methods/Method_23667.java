/** 
 * Get an optional string associated with a key. It returns the defaultValue if there is no such key.
 * @param key   A key string.
 * @param defaultValue     The default.
 * @return      A string which is the value.
 */
public String getString(String key,String defaultValue){
  Object object=this.opt(key);
  return NULL.equals(object) ? defaultValue : object.toString();
}
