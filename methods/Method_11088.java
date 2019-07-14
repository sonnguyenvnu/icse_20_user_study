/** 
 * Builds view for measuring
 */
private void buildViewForMeasuring(){
  if (itemsLayout != null) {
    recycle.recycleItems(itemsLayout,firstItem,new ItemsRange());
  }
 else {
    createItemsLayout();
  }
  for (int i=viewAdapter.getItemsCount() - 1; i >= 0; i--) {
    if (addViewItem(i,true)) {
      firstItem=i;
    }
  }
}
