private void calculatePositionDetails(RecyclerView parent,int position,LayoutManager layout){
  int itemCount=parent.getAdapter().getItemCount();
  firstItem=position == 0;
  lastItem=position == itemCount - 1;
  horizontallyScrolling=layout.canScrollHorizontally();
  verticallyScrolling=layout.canScrollVertically();
  grid=layout instanceof GridLayoutManager;
  if (grid) {
    GridLayoutManager grid=(GridLayoutManager)layout;
    final SpanSizeLookup spanSizeLookup=grid.getSpanSizeLookup();
    int spanSize=spanSizeLookup.getSpanSize(position);
    int spanCount=grid.getSpanCount();
    int spanIndex=spanSizeLookup.getSpanIndex(position,spanCount);
    isFirstItemInRow=spanIndex == 0;
    fillsLastSpan=spanIndex + spanSize == spanCount;
    isInFirstRow=isInFirstRow(position,spanSizeLookup,spanCount);
    isInLastRow=!isInFirstRow && isInLastRow(position,itemCount,spanSizeLookup,spanCount);
  }
}
