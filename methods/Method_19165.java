private static int getFirstCompletelyVisibleItemPosition(RecyclerView.LayoutManager layoutManager){
  if (layoutManager instanceof StaggeredGridLayoutManager) {
    return StaggeredGridLayoutHelper.findFirstFullyVisibleItemPosition((StaggeredGridLayoutManager)layoutManager);
  }
 else {
    return ((LinearLayoutManager)layoutManager).findFirstCompletelyVisibleItemPosition();
  }
}
