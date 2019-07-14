/** 
 * ???????? name => name; name:alias => alias
 * @param key name ? name:alias
 * @return {@link #formatKey(String,boolean,boolean,boolean,boolean)} formatColon = false, formatAt = true, formatHyphen = false, firstCase = true
 */
public static String formatObjectKey(String key){
  int index=key == null ? -1 : key.indexOf(":");
  if (index >= 0) {
    return key.substring(index + 1);
  }
  return formatKey(key,false,true,false,true);
}
