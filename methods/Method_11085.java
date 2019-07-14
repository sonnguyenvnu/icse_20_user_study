/** 
 * Rebuilds wheel items if necessary. Caches all unused items.
 * @return true if items are rebuilt
 */
private boolean rebuildItems(){
  boolean updated=false;
  ItemsRange range=getItemsRange();
  if (itemsLayout != null) {
    int first=recycle.recycleItems(itemsLayout,firstItem,range);
    updated=firstItem != first;
    firstItem=first;
  }
 else {
    createItemsLayout();
    updated=true;
  }
  if (!updated) {
    updated=firstItem != range.getFirst() || itemsLayout.getChildCount() != range.getCount();
  }
  if (firstItem > range.getFirst() && firstItem <= range.getLast()) {
    for (int i=firstItem - 1; i >= range.getFirst(); i--) {
      if (!addViewItem(i,true)) {
        break;
      }
      firstItem=i;
    }
  }
 else {
    firstItem=range.getFirst();
  }
  int first=firstItem;
  for (int i=itemsLayout.getChildCount(); i < range.getCount(); i++) {
    if (!addViewItem(firstItem + i,false) && itemsLayout.getChildCount() == 0) {
      first++;
    }
  }
  firstItem=first;
  return updated;
}
