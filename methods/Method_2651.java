/** 
 * ???????????
 * @return
 */
public int getEmptySize(){
  int size=0;
  for (int i=0; i < getBaseArraySize(); i++) {
    if (isEmpty(i)) {
      ++size;
    }
  }
  return size;
}
