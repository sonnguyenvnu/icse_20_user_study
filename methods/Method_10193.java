/** 
 * Gets a configuration integer property with the specified key.
 * @param key the specified key
 * @return integer property value corresponding to the specified key, returns {@code null} if not found
 */
private static Integer getInt(final String key){
  final String stringValue=get(key);
  if (null == stringValue) {
    return null;
  }
  return Integer.valueOf(stringValue);
}
