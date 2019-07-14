protected void updateActionForInterimTarget(Action action){
  PointF scrollVector=computeScrollVectorForPosition(getTargetPosition());
  if (scrollVector == null || (scrollVector.x == 0 && scrollVector.y == 0)) {
    final int target=getTargetPosition();
    action.jumpTo(target);
    stop();
    return;
  }
  normalize(scrollVector);
  mTargetVector=scrollVector;
  mInterimTargetDx=(int)(TARGET_SEEK_SCROLL_DISTANCE_PX * scrollVector.x);
  mInterimTargetDy=(int)(TARGET_SEEK_SCROLL_DISTANCE_PX * scrollVector.y);
  final int time=calculateTimeForScrolling(TARGET_SEEK_SCROLL_DISTANCE_PX);
  action.update((int)(mInterimTargetDx * TARGET_SEEK_EXTRA_SCROLL_RATIO),(int)(mInterimTargetDy * TARGET_SEEK_EXTRA_SCROLL_RATIO),(int)(time * TARGET_SEEK_EXTRA_SCROLL_RATIO),mLinearInterpolator);
}
