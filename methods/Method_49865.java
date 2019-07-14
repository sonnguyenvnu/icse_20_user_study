/** 
 * Check a key and its type match the predefined keys and corresponding types
 * @param key
 * @param type Including "int" "bool" and "string"
 * @return True if key and type both matches and false otherwise
 */
public static boolean isValidKey(String key,String type){
  if (!TextUtils.isEmpty(key) && DEFAULTS.containsKey(key)) {
    Object defVal=DEFAULTS.get(key);
    Class<?> valueType=defVal != null ? defVal.getClass() : String.class;
    if (KEY_TYPE_INT.equals(type)) {
      return valueType == Integer.class;
    }
 else     if (KEY_TYPE_BOOL.equals(type)) {
      return valueType == Boolean.class;
    }
 else     if (KEY_TYPE_STRING.equals(type)) {
      return valueType == String.class;
    }
  }
  return false;
}
