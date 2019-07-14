/** 
 * ???????????
 * @param charArray ??
 * @param trie ???
 * @param < V > ??
 * @return ????
 */
public static <V>LinkedList<ResultTerm<V>> segment(final char[] charArray,AhoCorasickDoubleArrayTrie<V> trie){
  LinkedList<ResultTerm<V>> termList=new LinkedList<ResultTerm<V>>();
  final ResultTerm<V>[] wordNet=new ResultTerm[charArray.length];
  trie.parseText(charArray,new AhoCorasickDoubleArrayTrie.IHit<V>(){
    @Override public void hit(    int begin,    int end,    V value){
      if (wordNet[begin] == null || wordNet[begin].word.length() < end - begin) {
        wordNet[begin]=new ResultTerm<V>(new String(charArray,begin,end - begin),value,begin);
      }
    }
  }
);
  for (int i=0; i < charArray.length; ) {
    if (wordNet[i] == null) {
      StringBuilder sbTerm=new StringBuilder();
      int offset=i;
      while (i < charArray.length && wordNet[i] == null) {
        sbTerm.append(charArray[i]);
        ++i;
      }
      termList.add(new ResultTerm<V>(sbTerm.toString(),null,offset));
    }
 else {
      termList.add(wordNet[i]);
      i+=wordNet[i].word.length();
    }
  }
  return termList;
}
