/** 
 * Converts the Iterable to a HashMap
 * @deprecated As of release 4.5, use {@link #iterableHashMap(Iterable)}
 */
@Deprecated public static <K,V>HashMap<K,V> from(final Iterable<P2<K,V>> entries){
  return iterableHashMap(entries);
}
