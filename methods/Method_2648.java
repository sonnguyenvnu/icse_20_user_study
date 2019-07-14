/** 
 * ????
 * @param state
 * @param codePoint
 * @return
 */
public int transfer(int state,int codePoint){
  if (state < 1) {
    return -1;
  }
  if ((state != 1) && (isEmpty(state))) {
    return -1;
  }
  int[] ids=this.charMap.toIdList(codePoint);
  if (ids.length == 0) {
    return -1;
  }
  return transfer(state,ids);
}
