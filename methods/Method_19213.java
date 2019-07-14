@Override public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager,int velocityX,int velocityY){
  if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
    return RecyclerView.NO_POSITION;
  }
  final int itemCount=layoutManager.getItemCount();
  if (itemCount == 0) {
    return RecyclerView.NO_POSITION;
  }
  final View currentView=findSnapView(layoutManager);
  if (currentView == null) {
    return RecyclerView.NO_POSITION;
  }
  final int currentPosition=layoutManager.getPosition(currentView);
  if (currentPosition == RecyclerView.NO_POSITION) {
    return RecyclerView.NO_POSITION;
  }
  RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider=(RecyclerView.SmoothScroller.ScrollVectorProvider)layoutManager;
  PointF vectorForEnd=vectorProvider.computeScrollVectorForPosition(itemCount - 1);
  if (vectorForEnd == null) {
    return RecyclerView.NO_POSITION;
  }
  int vDeltaJump, hDeltaJump;
  if (layoutManager.canScrollHorizontally()) {
    hDeltaJump=estimateNextPositionDiffForFling(layoutManager,OrientationHelper.createHorizontalHelper(layoutManager),velocityX,0);
    if (hDeltaJump > mDeltaJumpThreshold) {
      hDeltaJump=mDeltaJumpThreshold;
    }
    if (hDeltaJump < -mDeltaJumpThreshold) {
      hDeltaJump=-mDeltaJumpThreshold;
    }
    if (vectorForEnd.x < 0) {
      hDeltaJump=-hDeltaJump;
    }
  }
 else {
    hDeltaJump=0;
  }
  if (layoutManager.canScrollVertically()) {
    vDeltaJump=estimateNextPositionDiffForFling(layoutManager,OrientationHelper.createVerticalHelper(layoutManager),0,velocityY);
    if (vectorForEnd.y < 0) {
      vDeltaJump=-vDeltaJump;
    }
  }
 else {
    vDeltaJump=0;
  }
  int deltaJump=layoutManager.canScrollVertically() ? vDeltaJump : hDeltaJump;
  if (deltaJump == 0) {
    return RecyclerView.NO_POSITION;
  }
  int targetPos=currentPosition + deltaJump;
  if (targetPos < 0) {
    targetPos=0;
  }
  if (targetPos >= itemCount) {
    targetPos=itemCount - 1;
  }
  return targetPos;
}
