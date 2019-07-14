/** 
 * ??map????key
 * @param < K >
 * @param < V >
 * @param map
 * @param key
 * @return
 */
public static <K,V>boolean isContainKey(Map<K,V> map,K key){
  return map != null && map.containsKey(key);
}
