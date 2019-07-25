/** 
 * @param keys a set of keys
 * @return a new map representing the current map, but only with the keys in {@code keys}
 */
default IMap<K,V> intersection(ISet<K> keys){
  IMap<K,V> result=Maps.intersection(new Map<K,V>(keyHash(),keyEquality()).linear(),this,keys);
  return isLinear() ? result : result.forked();
}
