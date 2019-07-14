/** 
 * In the case mShowLastDivider = false, Returns offset for how many views we don't have to draw a divider for, for LinearLayoutManager it is as simple as not drawing the last child divider, but for a GridLayoutManager it needs to take the span count for the last items into account until we use the span count configured for the grid.
 * @param parent RecyclerView
 * @return offset for how many views we don't have to draw a divider or 1 if its aLinearLayoutManager
 */
private int getLastDividerOffset(RecyclerView parent){
  if (parent.getLayoutManager() instanceof GridLayoutManager) {
    GridLayoutManager layoutManager=(GridLayoutManager)parent.getLayoutManager();
    GridLayoutManager.SpanSizeLookup spanSizeLookup=layoutManager.getSpanSizeLookup();
    int spanCount=layoutManager.getSpanCount();
    int itemCount=parent.getAdapter().getItemCount();
    for (int i=itemCount - 1; i >= 0; i--) {
      if (spanSizeLookup.getSpanIndex(i,spanCount) == 0) {
        return itemCount - i;
      }
    }
  }
  return 1;
}
