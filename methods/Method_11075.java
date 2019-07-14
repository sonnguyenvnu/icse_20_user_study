/** 
 * Adds view to cache. Determines view type (item view or empty one) by index.
 * @param view the view to be cached
 * @param index the index of view
 */
private void recycleView(View view,int index){
  int count=wheel.getViewAdapter().getItemsCount();
  if ((index < 0 || index >= count) && !wheel.isCyclic()) {
    emptyItems=addView(view,emptyItems);
  }
 else {
    while (index < 0) {
      index=count + index;
    }
    index%=count;
    items=addView(view,items);
  }
}
