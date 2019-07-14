public static BaseSearcher getSearcher(char[] charArray,DoubleArrayTrie<Pinyin[]> trie){
  return new Searcher(charArray,trie);
}
