/** 
 * Determine if the JSONObject contains a specific key.
 * @param key   A key string.
 * @return      true if the key exists in the JSONObject.
 */
public boolean hasKey(String key){
  return map.containsKey(key);
}
