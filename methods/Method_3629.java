/** 
 * ???????
 * @param text
 * @param filterArrayChain ???????
 * @return
 */
public static List<List<Term>> seg2sentence(String text,Filter... filterArrayChain){
  List<List<Term>> sentenceList=SEGMENT.seg2sentence(text);
  for (  List<Term> sentence : sentenceList) {
    ListIterator<Term> listIterator=sentence.listIterator();
    while (listIterator.hasNext()) {
      if (filterArrayChain != null) {
        Term term=listIterator.next();
        for (        Filter filter : filterArrayChain) {
          if (!filter.shouldInclude(term)) {
            listIterator.remove();
            break;
          }
        }
      }
    }
  }
  return sentenceList;
}
