/** 
 * @param key
 * @param suffix
 * @return key + suffix????????
 */
public static String addSuffix(String key,String suffix){
  key=getNoBlankString(key);
  if (key.isEmpty()) {
    return firstCase(suffix);
  }
  return firstCase(key) + firstCase(suffix,true);
}
