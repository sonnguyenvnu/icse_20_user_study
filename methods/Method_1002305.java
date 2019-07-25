/** 
 * Removes the key (and its corresponding value) from this map. This method does nothing if the key is not in the map.
 * @return the previous value associated with <tt>key</tt>, or<tt>null</tt> if there was no mapping for <tt>key</tt>
 * @throws NullPointerException if the specified key is null 
 */
@Override public TypeV remove(Object key){
  return putIfMatch(key,TOMBSTONE,NO_MATCH_OLD);
}
