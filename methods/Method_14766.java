/** 
 * key:alias => alias
 * @param key
 * @return
 */
public static String formatColon(@NotNull String key){
  int index=key.indexOf(":");
  return index < 0 ? key : key.substring(index + 1);
}
