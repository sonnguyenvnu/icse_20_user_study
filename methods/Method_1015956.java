/** 
 * Determine if the JSONObject contains a specific key.
 * @param key A key string.
 * @return true if the key exists in the JSONObject.
 */
public boolean has(String key){
  return this.map.containsKey(key);
}
