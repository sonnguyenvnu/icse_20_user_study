public static BaseSearcher getSearcher(char[] charArray,DoubleArrayTrie<String> trie){
  return new Searcher(charArray,trie);
}
