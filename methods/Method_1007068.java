/** 
 * Creates an empty trie.
 */
public static <K,V>HashArrayMappedTrie<K,V> empty(final Equal<K> e,final Hash<K> h){
  return new HashArrayMappedTrie<>(BitSet.empty(),Seq.empty(),e,h);
}
