/** 
 * Scrolls the spinnerwheel
 * @param delta the scrolling value
 */
private void doScroll(int delta){
  mScrollingOffset+=delta;
  int itemDimension=getItemDimension();
  int count=mScrollingOffset / itemDimension;
  int pos=mCurrentItemIdx - count;
  int itemCount=mViewAdapter.getItemsCount();
  int fixPos=mScrollingOffset % itemDimension;
  if (Math.abs(fixPos) <= itemDimension / 2) {
    fixPos=0;
  }
  if (mIsCyclic && itemCount > 0) {
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
      count=mCurrentItemIdx;
      pos=0;
    }
 else     if (pos >= itemCount) {
      count=mCurrentItemIdx - itemCount + 1;
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
  int offset=mScrollingOffset;
  if (pos != mCurrentItemIdx) {
    setCurrentItem(pos,false);
  }
 else {
    invalidate();
  }
  int baseDimension=getBaseDimension();
  mScrollingOffset=offset - count * itemDimension;
  if (mScrollingOffset > baseDimension) {
    mScrollingOffset=mScrollingOffset % baseDimension + baseDimension;
  }
}
