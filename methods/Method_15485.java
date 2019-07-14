/** 
 * "@key" => "key"
 * @param key
 * @return
 */
public static String formatAt(@NotNull String key){
  return key.startsWith("@") ? key.substring(1) : key;
}
