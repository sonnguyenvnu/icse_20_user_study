/** 
 * ?????????????
 * @param text ??
 * @param trie ???
 * @param < V > ??
 * @return ????
 */
public static <V>LinkedList<ResultTerm<V>> segmentReverseOrder(String text,AhoCorasickDoubleArrayTrie<V> trie){
  return segmentReverseOrder(text.toCharArray(),trie);
}
