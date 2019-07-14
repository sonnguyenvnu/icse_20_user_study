/** 
 * @webref jsonobject:method
 * @brief Gets the float value associated with a key
 * @param key a key string
 * @see JSONObject#getInt(String)
 * @see JSONObject#getString(String)
 * @see JSONObject#getBoolean(String)
 */
public float getFloat(String key){
  return (float)getDouble(key);
}
