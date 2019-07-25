/** 
 * Allocate a page from the free list.
 * @param exclude the exclude list or null
 * @param first the first page to look for
 * @return the page, or -1 if all pages are used
 */
int allocate(BitSet exclude,int first){
  if (full) {
    return -1;
  }
  int start=Math.max(0,first - getPos());
  while (true) {
    int free=used.nextClearBit(start);
    if (free >= pageCount) {
      if (start == 0) {
        full=true;
      }
      return -1;
    }
    if (exclude != null && exclude.get(free + getPos())) {
      start=exclude.nextClearBit(free + getPos()) - getPos();
      if (start >= pageCount) {
        return -1;
      }
    }
 else {
      used.set(free);
      store.logUndo(this,data);
      store.update(this);
      return free + getPos();
    }
  }
}
