public static int findFirstVisibleItemPosition(StaggeredGridLayoutManager staggeredGridLayoutManager){
  if (mItemPositionsHolder == null) {
    mItemPositionsHolder=new int[staggeredGridLayoutManager.getSpanCount()];
  }
  return min(staggeredGridLayoutManager.findFirstVisibleItemPositions(mItemPositionsHolder));
}
