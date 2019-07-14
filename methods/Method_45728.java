/** 
 * ????ConcurrentMap????????????
 * @param map   ConcurrentMap
 * @param key   ???
 * @param value ?
 * @param < K >   ?????
 * @param < V >   ???
 * @return ??
 */
public static <K,V>V putToConcurrentMap(ConcurrentMap<K,V> map,K key,V value){
  V old=map.putIfAbsent(key,value);
  return old != null ? old : value;
}
