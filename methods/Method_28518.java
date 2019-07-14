private void initLayoutManager(RecyclerView.LayoutManager layoutManager){
  this.layoutManager=layoutManager;
  if (layoutManager instanceof GridLayoutManager) {
    visibleThreshold=visibleThreshold * ((GridLayoutManager)layoutManager).getSpanCount();
  }
 else   if (layoutManager instanceof StaggeredGridLayoutManager) {
    visibleThreshold=visibleThreshold * ((StaggeredGridLayoutManager)layoutManager).getSpanCount();
  }
}
