/** 
 * Static constructor for a HAMT instance.
 */
private static <K,V>HashArrayMappedTrie<K,V> hamt(final BitSet bs,final Seq<Node<K,V>> s,final Equal<K> e,final Hash<K> h){
  return new HashArrayMappedTrie<>(bs,s,e,h);
}
