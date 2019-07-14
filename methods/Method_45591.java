/** 
 * Remove request prop.
 * @param key the key
 */
public void removeRequestProp(String key){
  if (key == null) {
    return;
  }
  if (requestProps != null) {
    requestProps.remove(key);
  }
}
