/** 
 * ?????????????
 * @param charArray ??
 * @param trie ???
 * @param < V > ??
 * @return ????
 */
public static <V>LinkedList<ResultTerm<V>> segmentReverseOrder(final char[] charArray,AhoCorasickDoubleArrayTrie<V> trie){
  LinkedList<ResultTerm<V>> termList=new LinkedList<ResultTerm<V>>();
  final ResultTerm<V>[] wordNet=new ResultTerm[charArray.length + 1];
  trie.parseText(charArray,new AhoCorasickDoubleArrayTrie.IHit<V>(){
    @Override public void hit(    int begin,    int end,    V value){
      if (wordNet[end] == null || wordNet[end].word.length() < end - begin) {
        wordNet[end]=new ResultTerm<V>(new String(charArray,begin,end - begin),value,begin);
      }
    }
  }
);
  for (int i=charArray.length; i > 0; ) {
    if (wordNet[i] == null) {
      StringBuilder sbTerm=new StringBuilder();
      int offset=i - 1;
      byte preCharType=CharType.get(charArray[offset]);
      while (i > 0 && wordNet[i] == null && CharType.get(charArray[i - 1]) == preCharType) {
        sbTerm.append(charArray[i - 1]);
        preCharType=CharType.get(charArray[i - 1]);
        --i;
      }
      termList.addFirst(new ResultTerm<V>(sbTerm.reverse().toString(),null,offset));
    }
 else {
      termList.addFirst(wordNet[i]);
      i-=wordNet[i].word.length();
    }
  }
  return termList;
}
