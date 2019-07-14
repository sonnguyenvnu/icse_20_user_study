/** 
 * The last field of key is actual property key
 */
public static String extractPropertyKey(String key){
  if (StringUtils.isBlank(key)) {
    return "";
  }
  return key.substring(key.lastIndexOf(KEY_SEPARATOR) + 1);
}
