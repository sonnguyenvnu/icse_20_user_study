private static int getLastCompletelyVisibleItemPosition(RecyclerView.LayoutManager layoutManager){
  if (layoutManager instanceof StaggeredGridLayoutManager) {
    return StaggeredGridLayoutHelper.findLastFullyVisibleItemPosition((StaggeredGridLayoutManager)layoutManager);
  }
 else {
    return ((LinearLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
  }
}
