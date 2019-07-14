/** 
 * ???prefix??????
 * @param prefix
 * @return
 */
public List<String> prefixMatch(String prefix){
  int curState=1;
  IntArrayList bytes=new IntArrayList(prefix.length() * 4);
  for (int i=0; i < prefix.length(); i++) {
    int codePoint=prefix.charAt(i);
    if (curState < 1) {
      return Collections.emptyList();
    }
    if ((curState != 1) && (isEmpty(curState))) {
      return Collections.emptyList();
    }
    int[] ids=this.charMap.toIdList(codePoint);
    if (ids.length == 0) {
      return Collections.emptyList();
    }
    for (int j=0; j < ids.length; j++) {
      int c=ids[j];
      if ((getBase(curState) + c < getBaseArraySize()) && (getCheck(getBase(curState) + c) == curState)) {
        bytes.append(c);
        curState=getBase(curState) + c;
      }
 else {
        return Collections.emptyList();
      }
    }
  }
  List<String> result=new ArrayList<String>();
  recursiveAddSubTree(curState,result,bytes);
  return result;
}
