/** 
 * @param key
 * @param upper
 * @return
 */
public static String firstCase(String key,boolean upper){
  key=getString(key);
  if (key.isEmpty()) {
    return "";
  }
  String first=key.substring(0,1);
  key=(upper ? first.toUpperCase() : first.toLowerCase()) + key.substring(1,key.length());
  return key;
}
