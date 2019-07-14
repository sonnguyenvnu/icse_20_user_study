/** 
 * Check a key and its type match the predefined keys and corresponding types
 * @param key The key of the config
 * @param value The value of the config
 * @return True if key and type both matches and false otherwise
 */
public static boolean isValidValue(String key,Object value){
  if (!TextUtils.isEmpty(key) && DEFAULTS.containsKey(key)) {
    Object defVal=DEFAULTS.get(key);
    Class<?> valueType=defVal != null ? defVal.getClass() : String.class;
    return value.getClass().equals(valueType);
  }
  return false;
}
