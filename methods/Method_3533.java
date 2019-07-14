/** 
 * ???????????
 * @param text ??
 * @param trie ???
 * @param < V > ??
 * @return ????
 */
public static <V>LinkedList<ResultTerm<V>> segment(String text,AhoCorasickDoubleArrayTrie<V> trie){
  return segment(text.toCharArray(),trie);
}
