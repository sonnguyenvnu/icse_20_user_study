/** 
 * Converts the Iterable to a HashMap
 * @deprecated As of release 4.5, use {@link #iterableHashMap}
 */
@Deprecated public static <K,V>HashMap<K,V> from(final Iterable<P2<K,V>> entries,final Equal<K> equal,final Hash<K> hash){
  return iterableHashMap(equal,hash,entries);
}
