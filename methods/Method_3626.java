/** 
 * ??
 * @param text ??
 * @return ????
 */
public static List<Term> segment(char[] text){
  List<Term> resultList=SEGMENT.seg(text);
  ListIterator<Term> listIterator=resultList.listIterator();
  while (listIterator.hasNext()) {
    if (!CoreStopWordDictionary.shouldInclude(listIterator.next())) {
      listIterator.remove();
    }
  }
  return resultList;
}
