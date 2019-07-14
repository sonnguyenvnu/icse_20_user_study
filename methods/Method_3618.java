/** 
 * ????????????
 * @param vertexList ????
 * @return ??????
 */
protected static List<CoreDictionary.Attribute> combineWithCustomDictionary(List<String> vertexList){
  String[] wordNet=new String[vertexList.size()];
  vertexList.toArray(wordNet);
  CoreDictionary.Attribute[] attributeArray=new CoreDictionary.Attribute[wordNet.length];
  DoubleArrayTrie<CoreDictionary.Attribute> dat=CustomDictionary.dat;
  int length=wordNet.length;
  for (int i=0; i < length; ++i) {
    int state=1;
    state=dat.transition(wordNet[i],state);
    if (state > 0) {
      int to=i + 1;
      int end=to;
      CoreDictionary.Attribute value=dat.output(state);
      for (; to < length; ++to) {
        state=dat.transition(wordNet[to],state);
        if (state < 0)         break;
        CoreDictionary.Attribute output=dat.output(state);
        if (output != null) {
          value=output;
          end=to + 1;
        }
      }
      if (value != null) {
        combineWords(wordNet,i,end,attributeArray,value);
        i=end - 1;
      }
    }
  }
  if (CustomDictionary.trie != null) {
    for (int i=0; i < length; ++i) {
      if (wordNet[i] == null)       continue;
      BaseNode<CoreDictionary.Attribute> state=CustomDictionary.trie.transition(wordNet[i],0);
      if (state != null) {
        int to=i + 1;
        int end=to;
        CoreDictionary.Attribute value=state.getValue();
        for (; to < length; ++to) {
          if (wordNet[to] == null)           continue;
          state=state.transition(wordNet[to],0);
          if (state == null)           break;
          if (state.getValue() != null) {
            value=state.getValue();
            end=to + 1;
          }
        }
        if (value != null) {
          combineWords(wordNet,i,end,attributeArray,value);
          i=end - 1;
        }
      }
    }
  }
  vertexList.clear();
  List<CoreDictionary.Attribute> attributeList=new LinkedList<CoreDictionary.Attribute>();
  for (int i=0; i < wordNet.length; i++) {
    if (wordNet[i] != null) {
      vertexList.add(wordNet[i]);
      attributeList.add(attributeArray[i]);
    }
  }
  return attributeList;
}
