/** 
 * ??????
 * @param wordNetStorage
 */
protected void generateWordNet(final WordNet wordNetStorage){
  final char[] charArray=wordNetStorage.charArray;
  DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher=CoreDictionary.trie.getSearcher(charArray,0);
  while (searcher.next()) {
    wordNetStorage.add(searcher.begin + 1,new Vertex(new String(charArray,searcher.begin,searcher.length),searcher.value,searcher.index));
  }
  if (config.forceCustomDictionary) {
    CustomDictionary.parseText(charArray,new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>(){
      @Override public void hit(      int begin,      int end,      CoreDictionary.Attribute value){
        wordNetStorage.add(begin + 1,new Vertex(new String(charArray,begin,end - begin),value));
      }
    }
);
  }
  LinkedList<Vertex>[] vertexes=wordNetStorage.getVertexes();
  for (int i=1; i < vertexes.length; ) {
    if (vertexes[i].isEmpty()) {
      int j=i + 1;
      for (; j < vertexes.length - 1; ++j) {
        if (!vertexes[j].isEmpty())         break;
      }
      wordNetStorage.add(i,quickAtomSegment(charArray,i - 1,j - 1));
      i=j;
    }
 else     i+=vertexes[i].getLast().realWord.length();
  }
}
