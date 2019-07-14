/** 
 * Get a config value by its type
 * @param key The key of the config
 * @param type The type of the config value
 * @return The expected typed value or null if no match
 */
public Object getValueAsType(String key,String type){
  if (isValidKey(key,type)) {
    return mKeyValues.get(key);
  }
  return null;
}
