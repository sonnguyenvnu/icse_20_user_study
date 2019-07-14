/** 
 * Determines whether divider was already drawn for the row the item is in, effectively only makes sense for a grid
 * @param position current view position to draw divider
 * @param parent   RecyclerView
 * @return true if the divider can be skipped as it is in the same row as the previous one.
 */
private boolean wasDividerAlreadyDrawn(int position,RecyclerView parent){
  if (parent.getLayoutManager() instanceof GridLayoutManager) {
    GridLayoutManager layoutManager=(GridLayoutManager)parent.getLayoutManager();
    GridLayoutManager.SpanSizeLookup spanSizeLookup=layoutManager.getSpanSizeLookup();
    int spanCount=layoutManager.getSpanCount();
    return spanSizeLookup.getSpanIndex(position,spanCount) > 0;
  }
  return false;
}
