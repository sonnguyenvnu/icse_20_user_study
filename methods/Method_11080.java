/** 
 * Invalidates wheel
 * @param clearCaches if true then cached views will be clear
 */
public void invalidateWheel(boolean clearCaches){
  if (clearCaches) {
    recycle.clearAll();
    if (itemsLayout != null) {
      itemsLayout.removeAllViews();
    }
    scrollingOffset=0;
  }
 else   if (itemsLayout != null) {
    recycle.recycleItems(itemsLayout,firstItem,new ItemsRange());
  }
  invalidate();
}
