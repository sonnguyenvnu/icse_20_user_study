/** 
 * Rebuilds spinnerwheel items if necessary. Caches all unused items.
 * @return true if items are rebuilt
 */
protected boolean rebuildItems(){
  boolean updated;
  ItemsRange range=getItemsRange();
  if (mItemsLayout != null) {
    int first=mRecycler.recycleItems(mItemsLayout,mFirstItemIdx,range);
    updated=mFirstItemIdx != first;
    mFirstItemIdx=first;
  }
 else {
    createItemsLayout();
    updated=true;
  }
  if (!updated) {
    updated=mFirstItemIdx != range.getFirst() || mItemsLayout.getChildCount() != range.getCount();
  }
  if (mFirstItemIdx > range.getFirst() && mFirstItemIdx <= range.getLast()) {
    for (int i=mFirstItemIdx - 1; i >= range.getFirst(); i--) {
      if (!addItemView(i,true)) {
        break;
      }
      mFirstItemIdx=i;
    }
  }
 else {
    mFirstItemIdx=range.getFirst();
  }
  int first=mFirstItemIdx;
  for (int i=mItemsLayout.getChildCount(); i < range.getCount(); i++) {
    if (!addItemView(mFirstItemIdx + i,false) && mItemsLayout.getChildCount() == 0) {
      first++;
    }
  }
  mFirstItemIdx=first;
  return updated;
}
