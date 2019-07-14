/** 
 * ?????????
 * @param key key
 * @return hasKey
 */
public synchronized boolean hasKey(String key){
  return map.containsKey(key);
}
