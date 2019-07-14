/** 
 * HorizontalScrollView????tab,?????? 
 */
private void scrollToCurrentTab(){
  if (mTabCount <= 0) {
    return;
  }
  int offset=(int)(mCurrentPositionOffset * mTabsContainer.getChildAt(mCurrentTab).getWidth());
  int newScrollX=mTabsContainer.getChildAt(mCurrentTab).getLeft() + offset;
  if (mCurrentTab > 0 || offset > 0) {
    newScrollX-=getWidth() / 2 - getPaddingLeft();
    calcIndicatorRect();
    newScrollX+=((mTabRect.right - mTabRect.left) / 2);
  }
  if (newScrollX != mLastScrollX) {
    mLastScrollX=newScrollX;
    scrollTo(newScrollX,0);
  }
}
