/** 
 * ?????????????
 * @param linkedArray
 */
private static void mergeContinueNumIntoOne(List<Vertex> linkedArray){
  if (linkedArray.size() < 2)   return;
  ListIterator<Vertex> listIterator=linkedArray.listIterator();
  Vertex next=listIterator.next();
  Vertex current=next;
  while (listIterator.hasNext()) {
    next=listIterator.next();
    if ((TextUtility.isAllNum(current.realWord) || TextUtility.isAllChineseNum(current.realWord)) && (TextUtility.isAllNum(next.realWord) || TextUtility.isAllChineseNum(next.realWord))) {
      current=Vertex.newNumberInstance(current.realWord + next.realWord);
      listIterator.previous();
      listIterator.previous();
      listIterator.set(current);
      listIterator.next();
      listIterator.next();
      listIterator.remove();
    }
 else {
      current=next;
    }
  }
}
