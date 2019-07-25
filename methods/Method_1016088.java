/** 
 * Returns whether specified language key exists or not.
 * @param key language key to check
 * @return whether specified language key exists or not
 */
public static boolean contains(final String key){
  return globalCache.containsKey(key);
}
