/** 
 * Inserts the given key and value association into the tree map. If the given key is already mapped to a value, the old value is replaced with the given one.
 * @param k The key to insert.
 * @param v The value to insert.
 * @return A new tree map with the given value mapped to the given key.
 */
public TreeMap<K,V> set(final K k,final V v){
  return new TreeMap<>(tree.insert(p(k,Option.some(v))));
}
