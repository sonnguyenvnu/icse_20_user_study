/** 
 * Invalidates items layout
 * @param clearCaches if true then cached views will be cleared
 */
public void invalidateItemsLayout(boolean clearCaches){
  if (clearCaches) {
    mRecycler.clearAll();
    if (mItemsLayout != null) {
      mItemsLayout.removeAllViews();
    }
    mScrollingOffset=0;
  }
 else   if (mItemsLayout != null) {
    mRecycler.recycleItems(mItemsLayout,mFirstItemIdx,new ItemsRange());
  }
  invalidate();
}
