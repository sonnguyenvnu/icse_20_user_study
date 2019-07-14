public static int findLastVisibleItemPosition(StaggeredGridLayoutManager staggeredGridLayoutManager){
  if (mItemPositionsHolder == null) {
    mItemPositionsHolder=new int[staggeredGridLayoutManager.getSpanCount()];
  }
  return max(staggeredGridLayoutManager.findLastVisibleItemPositions(mItemPositionsHolder));
}
