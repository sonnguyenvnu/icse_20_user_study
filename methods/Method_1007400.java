/** 
 * Create a regular copy of this Map.
 * @return a shallow copy of this Map, reusing this Map's value-holding Listentries
 * @since 4.2
 * @see LinkedMultiValueMap#LinkedMultiValueMap(Map)
 * @see #deepCopy()
 */
@Override public LinkedMultiValueMap<K,V> clone(){
  return new LinkedMultiValueMap<K,V>(this);
}
