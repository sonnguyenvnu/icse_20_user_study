/** 
 * ???????
 * @param text
 * @return
 */
public static List<List<Term>> seg2sentence(String text){
  List<List<Term>> sentenceList=SEGMENT.seg2sentence(text);
  for (  List<Term> sentence : sentenceList) {
    ListIterator<Term> listIterator=sentence.listIterator();
    while (listIterator.hasNext()) {
      if (!CoreStopWordDictionary.shouldInclude(listIterator.next())) {
        listIterator.remove();
      }
    }
  }
  return sentenceList;
}
