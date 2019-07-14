/** 
 * ????
 * @param segResult ????
 * @param wordNetOptimum ?????????
 * @param wordNetAll ???
 */
public static void recognition(List<Vertex> segResult,WordNet wordNetOptimum,WordNet wordNetAll){
  StringBuilder sbName=new StringBuilder();
  int appendTimes=0;
  ListIterator<Vertex> listIterator=segResult.listIterator();
  listIterator.next();
  int line=1;
  int activeLine=1;
  while (listIterator.hasNext()) {
    Vertex vertex=listIterator.next();
    if (appendTimes > 0) {
      if (vertex.guessNature() == Nature.nrf || TranslatedPersonDictionary.containsKey(vertex.realWord)) {
        sbName.append(vertex.realWord);
        ++appendTimes;
      }
 else {
        if (appendTimes > 1) {
          if (HanLP.Config.DEBUG) {
            System.out.println("????????" + sbName.toString());
          }
          wordNetOptimum.insert(activeLine,new Vertex(Predefine.TAG_PEOPLE,sbName.toString(),new CoreDictionary.Attribute(Nature.nrf),WORD_ID),wordNetAll);
        }
        sbName.setLength(0);
        appendTimes=0;
      }
    }
 else {
      if (vertex.guessNature() == Nature.nrf) {
        sbName.append(vertex.realWord);
        ++appendTimes;
        activeLine=line;
      }
    }
    line+=vertex.realWord.length();
  }
}
