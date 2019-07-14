public static int findFirstFullyVisibleItemPosition(StaggeredGridLayoutManager staggeredGridLayoutManager){
  if (mItemPositionsHolder == null) {
    mItemPositionsHolder=new int[staggeredGridLayoutManager.getSpanCount()];
  }
  return min(staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(mItemPositionsHolder));
}
