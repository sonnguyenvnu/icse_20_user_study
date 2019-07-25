/** 
 * @param update a function which takes the existing value, or {@code null} if none exists, and returns an updatedvalue.
 * @return an updated map with {@code update(value)} under {@code key}.
 */
default IMap<K,V> update(K key,UnaryOperator<V> update){
  return this.put(key,update.apply(this.get(key,null)));
}
