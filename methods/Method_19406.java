@Override public int findTargetSnapPosition(LayoutManager layoutManager,int velocityX,int velocityY){
  final int itemCount=layoutManager.getItemCount();
  if (itemCount == 0) {
    return RecyclerView.NO_POSITION;
  }
  final boolean isHorizontal=layoutManager.canScrollHorizontally();
  final OrientationHelper orientationHelper=isHorizontal ? getHorizontalHelper(layoutManager) : getVerticalHelper(layoutManager);
  final View firstBeforeStartChild=findFirstViewBeforeStart(layoutManager,orientationHelper);
  if (firstBeforeStartChild == null) {
    return RecyclerView.NO_POSITION;
  }
  final int firstBeforeStartPosition=layoutManager.getPosition(firstBeforeStartChild);
  if (firstBeforeStartPosition == RecyclerView.NO_POSITION) {
    return RecyclerView.NO_POSITION;
  }
  final boolean forwardDirection=isHorizontal ? velocityX > 0 : velocityY > 0;
  boolean reverseLayout=false;
  if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
    RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider=(RecyclerView.SmoothScroller.ScrollVectorProvider)layoutManager;
    PointF vectorForEnd=vectorProvider.computeScrollVectorForPosition(itemCount - 1);
    if (vectorForEnd != null) {
      reverseLayout=vectorForEnd.x < 0 || vectorForEnd.y < 0;
    }
  }
  return reverseLayout ? (forwardDirection ? firstBeforeStartPosition - 1 : firstBeforeStartPosition) : (forwardDirection ? firstBeforeStartPosition + 1 : firstBeforeStartPosition);
}
