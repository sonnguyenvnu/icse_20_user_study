/** 
 * ????????????BinTrie+DAT??????????????????????
 * @param text         ??
 * @param processor    ???
 */
public static void parseText(char[] text,AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor){
  if (trie != null) {
    trie.parseText(text,processor);
  }
  DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher=dat.getSearcher(text,0);
  while (searcher.next()) {
    processor.hit(searcher.begin,searcher.begin + searcher.length,searcher.value);
  }
}
