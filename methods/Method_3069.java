/** 
 * ????????????BinTrie+DAT??????????????????????
 * @param text         ??
 * @param processor    ???
 */
public static void parseText(String text,AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor){
  if (trie != null) {
    BaseSearcher searcher=CustomDictionary.getSearcher(text);
    int offset;
    Map.Entry<String,CoreDictionary.Attribute> entry;
    while ((entry=searcher.next()) != null) {
      offset=searcher.getOffset();
      processor.hit(offset,offset + entry.getKey().length(),entry.getValue());
    }
  }
  DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher=dat.getSearcher(text,0);
  while (searcher.next()) {
    processor.hit(searcher.begin,searcher.begin + searcher.length,searcher.value);
  }
}
