/** 
 * Returns the corresponding value if the key is found. Otherwise returns -1. This method converts the key into UTF-8.
 * @param key search key
 * @return found value
 */
public int exactMatchSearch(String key){
  return exactMatchSearch(key.getBytes(utf8));
}
