/** 
 * ????????????
 * @param vertexList ????
 * @param dat ???????
 * @return ??????
 */
protected static List<Vertex> combineByCustomDictionary(List<Vertex> vertexList,DoubleArrayTrie<CoreDictionary.Attribute> dat){
  assert vertexList.size() >= 2 : "vertexList???? ?##? ? ?##?";
  Vertex[] wordNet=new Vertex[vertexList.size()];
  vertexList.toArray(wordNet);
  int length=wordNet.length - 1;
  for (int i=1; i < length; ++i) {
    int state=1;
    state=dat.transition(wordNet[i].realWord,state);
    if (state > 0) {
      int to=i + 1;
      int end=to;
      CoreDictionary.Attribute value=dat.output(state);
      for (; to < length; ++to) {
        state=dat.transition(wordNet[to].realWord,state);
        if (state < 0)         break;
        CoreDictionary.Attribute output=dat.output(state);
        if (output != null) {
          value=output;
          end=to + 1;
        }
      }
      if (value != null) {
        combineWords(wordNet,i,end,value);
        i=end - 1;
      }
    }
  }
  if (CustomDictionary.trie != null) {
    for (int i=1; i < length; ++i) {
      if (wordNet[i] == null)       continue;
      BaseNode<CoreDictionary.Attribute> state=CustomDictionary.trie.transition(wordNet[i].realWord.toCharArray(),0);
      if (state != null) {
        int to=i + 1;
        int end=to;
        CoreDictionary.Attribute value=state.getValue();
        for (; to < length; ++to) {
          if (wordNet[to] == null)           continue;
          state=state.transition(wordNet[to].realWord.toCharArray(),0);
          if (state == null)           break;
          if (state.getValue() != null) {
            value=state.getValue();
            end=to + 1;
          }
        }
        if (value != null) {
          combineWords(wordNet,i,end,value);
          i=end - 1;
        }
      }
    }
  }
  vertexList.clear();
  for (  Vertex vertex : wordNet) {
    if (vertex != null)     vertexList.add(vertex);
  }
  return vertexList;
}
