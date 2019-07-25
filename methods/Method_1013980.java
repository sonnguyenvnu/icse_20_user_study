/** 
 * Returns a set of all keys.
 * @return the set of all keys
 */
public synchronized Set<K> keys(){
  final Set<K> keys=new LinkedHashSet<>();
  for (  final K key : items.keySet()) {
    keys.add(key);
  }
  return keys;
}
