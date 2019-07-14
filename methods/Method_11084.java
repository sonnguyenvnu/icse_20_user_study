/** 
 * Scrolls the wheel
 * @param delta the scrolling value
 */
private void doScroll(int delta){
  scrollingOffset+=delta;
  int itemHeight=getItemHeight();
  int count=scrollingOffset / itemHeight;
  int pos=currentItem - count;
  int itemCount=viewAdapter.getItemsCount();
  int fixPos=scrollingOffset % itemHeight;
  if (Math.abs(fixPos) <= itemHeight / 2) {
    fixPos=0;
  }
  if (isCyclic && itemCount > 0) {
    if (fixPos > 0) {
      pos--;
      count++;
    }
 else     if (fixPos < 0) {
      pos++;
      count--;
    }
    while (pos < 0) {
      pos+=itemCount;
    }
    pos%=itemCount;
  }
 else {
    if (pos < 0) {
      count=currentItem;
      pos=0;
    }
 else     if (pos >= itemCount) {
      count=currentItem - itemCount + 1;
      pos=itemCount - 1;
    }
 else     if (pos > 0 && fixPos > 0) {
      pos--;
      count++;
    }
 else     if (pos < itemCount - 1 && fixPos < 0) {
      pos++;
      count--;
    }
  }
  int offset=scrollingOffset;
  if (pos != currentItem) {
    setCurrentItem(pos,false);
  }
 else {
    invalidate();
  }
  scrollingOffset=offset - count * itemHeight;
  if (scrollingOffset > getHeight()) {
    if (getHeight() <= 0) {
      scrollingOffset=0;
    }
 else {
      scrollingOffset=scrollingOffset % getHeight() + getHeight();
    }
  }
}
