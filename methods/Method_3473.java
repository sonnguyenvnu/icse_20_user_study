/** 
 * ??????????? ????????????????segSentence
 */
@Override protected List<Term> segSentence(char[] sentence){
  if (sentence.length == 0)   return Collections.emptyList();
  List<Term> termList=roughSegSentence(sentence);
  if (!(config.ner || config.useCustomDictionary || config.speechTagging))   return termList;
  List<Vertex> vertexList=toVertexList(termList,true);
  if (config.speechTagging) {
    Viterbi.compute(vertexList,CoreDictionaryTransformMatrixDictionary.transformMatrixDictionary);
    int i=0;
    for (    Term term : termList) {
      if (term.nature != null)       term.nature=vertexList.get(i + 1).guessNature();
      ++i;
    }
  }
  if (config.useCustomDictionary) {
    combineByCustomDictionary(vertexList);
    termList=convert(vertexList,config.offset);
  }
  return termList;
}
