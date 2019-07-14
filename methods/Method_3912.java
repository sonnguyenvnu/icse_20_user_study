private boolean isReverseLayout(RecyclerView.LayoutManager layoutManager){
  final int itemCount=layoutManager.getItemCount();
  if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
    RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider=(RecyclerView.SmoothScroller.ScrollVectorProvider)layoutManager;
    PointF vectorForEnd=vectorProvider.computeScrollVectorForPosition(itemCount - 1);
    if (vectorForEnd != null) {
      return vectorForEnd.x < 0 || vectorForEnd.y < 0;
    }
  }
  return false;
}
