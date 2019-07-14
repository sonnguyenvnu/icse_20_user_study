private void setRecyclerViewPosition(float y){
  Logger.e(y);
  if (recyclerView != null) {
    int itemCount=recyclerView.getAdapter().getItemCount();
    float proportion;
    if (scrollerView.getY() == 0) {
      proportion=0f;
    }
 else     if (scrollerView.getY() + scrollerView.getHeight() >= height - TRACK_SNAP_RANGE) {
      proportion=1f;
    }
 else {
      proportion=y / (float)height;
    }
    int targetPos=getValueInRange(itemCount - 1,(int)(proportion * (float)itemCount));
    if (layoutManager instanceof StaggeredGridLayoutManager) {
      ((StaggeredGridLayoutManager)layoutManager).scrollToPositionWithOffset(targetPos,0);
    }
 else     if (layoutManager instanceof GridLayoutManager) {
      ((GridLayoutManager)layoutManager).scrollToPositionWithOffset(targetPos,0);
    }
 else {
      ((LinearLayoutManager)layoutManager).scrollToPositionWithOffset(targetPos,0);
    }
  }
}
