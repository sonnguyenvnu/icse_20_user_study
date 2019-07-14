/** 
 * Returns a group index for GridLayoutManager. for LinearLayoutManager, always returns position.
 * @param position current view position to draw divider
 * @param parent   RecyclerView
 * @return group index of items
 */
private int getGroupIndex(int position,RecyclerView parent){
  if (parent.getLayoutManager() instanceof GridLayoutManager) {
    GridLayoutManager layoutManager=(GridLayoutManager)parent.getLayoutManager();
    GridLayoutManager.SpanSizeLookup spanSizeLookup=layoutManager.getSpanSizeLookup();
    int spanCount=layoutManager.getSpanCount();
    return spanSizeLookup.getSpanGroupIndex(position,spanCount);
  }
  return position;
}
