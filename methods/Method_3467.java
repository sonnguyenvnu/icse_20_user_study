/** 
 * ????(?????????????????,??????????)
 * @param wordSegResult ????
 * @return
 */
public static List<EnumItem<NR>> roleObserve(List<Vertex> wordSegResult){
  List<EnumItem<NR>> tagList=new LinkedList<EnumItem<NR>>();
  Iterator<Vertex> iterator=wordSegResult.iterator();
  iterator.next();
  tagList.add(new EnumItem<NR>(NR.A,NR.K));
  while (iterator.hasNext()) {
    Vertex vertex=iterator.next();
    EnumItem<NR> nrEnumItem=PersonDictionary.dictionary.get(vertex.realWord);
    if (nrEnumItem == null) {
      Nature nature=vertex.guessNature();
      if (nature == nr) {
        if (vertex.getAttribute().totalFrequency <= 1000 && vertex.realWord.length() == 2) {
          nrEnumItem=new EnumItem<NR>();
          nrEnumItem.labelMap.put(NR.X,2);
          nrEnumItem.labelMap.put(NR.G,1);
        }
 else         nrEnumItem=new EnumItem<NR>(NR.A,PersonDictionary.transformMatrixDictionary.getTotalFrequency(NR.A));
      }
 else       if (nature == nnt) {
        nrEnumItem=new EnumItem<NR>(NR.G,NR.K);
      }
 else {
        nrEnumItem=new EnumItem<NR>(NR.A,PersonDictionary.transformMatrixDictionary.getTotalFrequency(NR.A));
      }
    }
    tagList.add(nrEnumItem);
  }
  return tagList;
}
