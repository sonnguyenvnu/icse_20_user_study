/** 
 * Init or add list.
 * @param < K > the key parameter
 * @param < V > the value parameter
 * @param orginMap the orgin map
 * @param key the key
 * @param needAdd the need add
 */
public static <K,V>void initOrAddList(Map<K,List<V>> orginMap,K key,V needAdd){
  List<V> listeners=orginMap.get(key);
  if (listeners == null) {
    listeners=new CopyOnWriteArrayList<V>();
    listeners.add(needAdd);
    orginMap.put(key,listeners);
  }
 else {
    listeners.add(needAdd);
  }
}
