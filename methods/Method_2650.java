/** 
 * ???
 * @param key
 * @return ?
 */
public int delete(String key){
  if (key == null) {
    return -1;
  }
  int curState=1;
  int[] ids=this.charMap.toIdList(key);
  int[] path=new int[ids.length + 1];
  int i=0;
  for (; i < ids.length; i++) {
    int c=ids[i];
    if ((getBase(curState) + c >= getBaseArraySize()) || (getCheck(getBase(curState) + c) != curState)) {
      break;
    }
    curState=getBase(curState) + c;
    path[i]=curState;
  }
  int ret=-1;
  if (i == ids.length) {
    if (getCheck(getBase(curState) + UNUSED_CHAR_VALUE) == curState) {
      --this.size;
      ret=getLeafValue(getBase(getBase(curState) + UNUSED_CHAR_VALUE));
      path[(path.length - 1)]=(getBase(curState) + UNUSED_CHAR_VALUE);
      for (int j=path.length - 1; j >= 0; --j) {
        boolean isLeaf=true;
        int state=path[j];
        for (int k=0; k < this.charMap.getCharsetSize(); k++) {
          if (isLeafValue(getBase(state))) {
            break;
          }
          if ((getBase(state) + k < getBaseArraySize()) && (getCheck(getBase(state) + k) == state)) {
            isLeaf=false;
            break;
          }
        }
        if (!isLeaf) {
          break;
        }
        addFreeLink(state);
      }
    }
  }
  return ret;
}
