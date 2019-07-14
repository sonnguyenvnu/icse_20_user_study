/** 
 * ????????????????????
 * @param charArray
 * @param start
 * @param end
 * @return
 */
protected static List<AtomNode> simpleAtomSegment(char[] charArray,int start,int end){
  List<AtomNode> atomNodeList=new LinkedList<AtomNode>();
  atomNodeList.add(new AtomNode(new String(charArray,start,end - start),CharType.CT_LETTER));
  return atomNodeList;
}
