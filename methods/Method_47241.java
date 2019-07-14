private float computeHandlePosition(){
  View firstVisibleView=recyclerView.getChildAt(0);
  handle.setVisibility(VISIBLE);
  float recyclerViewOversize;
  int recyclerViewAbsoluteScroll;
  if (firstVisibleView == null || recyclerView == null)   return -1;
  recyclerViewOversize=firstVisibleView.getHeight() / columns * recyclerView.getAdapter().getItemCount() - getHeightMinusPadding();
  recyclerViewAbsoluteScroll=recyclerView.getChildLayoutPosition(firstVisibleView) / columns * firstVisibleView.getHeight() - firstVisibleView.getTop();
  return recyclerViewAbsoluteScroll / recyclerViewOversize;
}
