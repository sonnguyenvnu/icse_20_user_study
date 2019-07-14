/** 
 * ????
 * @param text      ??
 * @param processor ???
 */
public static void parseLongestText(String text,AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor){
  if (trie != null) {
    final int[] lengthArray=new int[text.length()];
    final CoreDictionary.Attribute[] attributeArray=new CoreDictionary.Attribute[text.length()];
    char[] charArray=text.toCharArray();
    DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher=dat.getSearcher(charArray,0);
    while (searcher.next()) {
      lengthArray[searcher.begin]=searcher.length;
      attributeArray[searcher.begin]=searcher.value;
    }
    trie.parseText(charArray,new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>(){
      @Override public void hit(      int begin,      int end,      CoreDictionary.Attribute value){
        int length=end - begin;
        if (length > lengthArray[begin]) {
          lengthArray[begin]=length;
          attributeArray[begin]=value;
        }
      }
    }
);
    for (int i=0; i < charArray.length; ) {
      if (lengthArray[i] == 0) {
        ++i;
      }
 else {
        processor.hit(i,i + lengthArray[i],attributeArray[i]);
        i+=lengthArray[i];
      }
    }
  }
 else   dat.parseLongestText(text,processor);
}
