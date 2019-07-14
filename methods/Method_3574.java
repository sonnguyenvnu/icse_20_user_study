/** 
 * ??????????
 * @param vertexList
 * @param wordNetAll
 */
protected List<Term> decorateResultForIndexMode(List<Vertex> vertexList,WordNet wordNetAll){
  List<Term> termList=new LinkedList<Term>();
  int line=1;
  ListIterator<Vertex> listIterator=vertexList.listIterator();
  listIterator.next();
  int length=vertexList.size() - 2;
  for (int i=0; i < length; ++i) {
    Vertex vertex=listIterator.next();
    Term termMain=convert(vertex);
    termList.add(termMain);
    termMain.offset=line - 1;
    if (vertex.realWord.length() > 2) {
      int currentLine=line;
      while (currentLine < line + vertex.realWord.length()) {
        Iterator<Vertex> iterator=wordNetAll.descendingIterator(currentLine);
        while (iterator.hasNext()) {
          Vertex smallVertex=iterator.next();
          if (((termMain.nature == Nature.mq && smallVertex.hasNature(Nature.q)) || smallVertex.realWord.length() >= config.indexMode) && smallVertex != vertex && currentLine + smallVertex.realWord.length() <= line + vertex.realWord.length()) {
            listIterator.add(smallVertex);
            Term termSub=convert(smallVertex);
            termSub.offset=currentLine - 1;
            termList.add(termSub);
          }
        }
        ++currentLine;
      }
    }
    line+=vertex.realWord.length();
  }
  return termList;
}
