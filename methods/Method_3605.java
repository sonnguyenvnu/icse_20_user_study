/** 
 * ??
 * @param sentence      ??
 * @param normalized    ???????
 * @param wordList      ??????
 * @param attributeList ?????????????null?????????
 */
protected void segment(final String sentence,final String normalized,final List<String> wordList,final List<CoreDictionary.Attribute> attributeList){
  if (attributeList != null) {
    final int[] offset=new int[]{0};
    CustomDictionary.parseLongestText(sentence,new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>(){
      @Override public void hit(      int begin,      int end,      CoreDictionary.Attribute value){
        if (begin != offset[0]) {
          segmentAfterRule(sentence.substring(offset[0],begin),normalized.substring(offset[0],begin),wordList);
        }
        while (attributeList.size() < wordList.size())         attributeList.add(null);
        wordList.add(sentence.substring(begin,end));
        attributeList.add(value);
        assert wordList.size() == attributeList.size() : "????????????";
        offset[0]=end;
      }
    }
);
    if (offset[0] != sentence.length()) {
      segmentAfterRule(sentence.substring(offset[0]),normalized.substring(offset[0]),wordList);
    }
  }
 else {
    segmentAfterRule(sentence,normalized,wordList);
  }
}
