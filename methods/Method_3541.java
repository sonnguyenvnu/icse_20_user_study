/** 
 * ??????????????????????????
 * @param vertexList ????
 * @param dat ???????
 * @param wordNetAll ???????????
 * @return ??????
 */
protected static List<Vertex> combineByCustomDictionary(List<Vertex> vertexList,DoubleArrayTrie<CoreDictionary.Attribute> dat,final WordNet wordNetAll){
  List<Vertex> outputList=combineByCustomDictionary(vertexList,dat);
  int line=0;
  for (  final Vertex vertex : outputList) {
    final int parentLength=vertex.realWord.length();
    final int currentLine=line;
    if (parentLength >= 3) {
      CustomDictionary.parseText(vertex.realWord,new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>(){
        @Override public void hit(        int begin,        int end,        CoreDictionary.Attribute value){
          if (end - begin == parentLength)           return;
          wordNetAll.add(currentLine + begin,new Vertex(vertex.realWord.substring(begin,end),value));
        }
      }
);
    }
    line+=parentLength;
  }
  return outputList;
}
