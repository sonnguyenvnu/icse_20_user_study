/** 
 * ????????????“??”????
 * @param children ?????
 * @return base?
 */
private int searchFreeBase(SortedSet<Integer> children){
  int minChild=children.first();
  int maxChild=children.last();
  int current=0;
  while (getCheck(current) != 0) {
    if (current > minChild + 1) {
      int base=current - minChild;
      boolean ok=true;
      for (Iterator<Integer> it=children.iterator(); it.hasNext(); ) {
        int to=base + it.next();
        if (to >= getBaseArraySize()) {
          ok=false;
          break;
        }
        if (!isEmpty(to)) {
          ok=false;
          break;
        }
      }
      if (ok) {
        return base;
      }
    }
    current=-getCheck(current);
  }
  int oldSize=getBaseArraySize();
  expandArray(oldSize + maxChild);
  return oldSize;
}
