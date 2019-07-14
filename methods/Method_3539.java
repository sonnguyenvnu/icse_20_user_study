/** 
 * ????????????????????????
 * @param charArray
 * @param start
 * @param end
 * @return
 */
protected static List<AtomNode> quickAtomSegment(char[] charArray,int start,int end){
  List<AtomNode> atomNodeList=new LinkedList<AtomNode>();
  int offsetAtom=start;
  int preType=CharType.get(charArray[offsetAtom]);
  int curType;
  while (++offsetAtom < end) {
    curType=CharType.get(charArray[offsetAtom]);
    if (curType != preType) {
      if (preType == CharType.CT_NUM && "?,?.".indexOf(charArray[offsetAtom]) != -1) {
        if (offsetAtom + 1 < end) {
          int nextType=CharType.get(charArray[offsetAtom + 1]);
          if (nextType == CharType.CT_NUM) {
            continue;
          }
        }
      }
      atomNodeList.add(new AtomNode(new String(charArray,start,offsetAtom - start),preType));
      start=offsetAtom;
    }
    preType=curType;
  }
  if (offsetAtom == end)   atomNodeList.add(new AtomNode(new String(charArray,start,offsetAtom - start),preType));
  return atomNodeList;
}
