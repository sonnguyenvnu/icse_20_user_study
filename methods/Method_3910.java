@Override public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager,int velocityX,int velocityY){
  final int itemCount=layoutManager.getItemCount();
  if (itemCount == 0) {
    return RecyclerView.NO_POSITION;
  }
  final OrientationHelper orientationHelper=getOrientationHelper(layoutManager);
  if (orientationHelper == null) {
    return RecyclerView.NO_POSITION;
  }
  View closestChildBeforeCenter=null;
  int distanceBefore=Integer.MIN_VALUE;
  View closestChildAfterCenter=null;
  int distanceAfter=Integer.MAX_VALUE;
  final int childCount=layoutManager.getChildCount();
  for (int i=0; i < childCount; i++) {
    final View child=layoutManager.getChildAt(i);
    if (child == null) {
      continue;
    }
    final int distance=distanceToCenter(layoutManager,child,orientationHelper);
    if (distance <= 0 && distance > distanceBefore) {
      distanceBefore=distance;
      closestChildBeforeCenter=child;
    }
    if (distance >= 0 && distance < distanceAfter) {
      distanceAfter=distance;
      closestChildAfterCenter=child;
    }
  }
  final boolean forwardDirection=isForwardFling(layoutManager,velocityX,velocityY);
  if (forwardDirection && closestChildAfterCenter != null) {
    return layoutManager.getPosition(closestChildAfterCenter);
  }
 else   if (!forwardDirection && closestChildBeforeCenter != null) {
    return layoutManager.getPosition(closestChildBeforeCenter);
  }
  View visibleView=forwardDirection ? closestChildBeforeCenter : closestChildAfterCenter;
  if (visibleView == null) {
    return RecyclerView.NO_POSITION;
  }
  int visiblePosition=layoutManager.getPosition(visibleView);
  int snapToPosition=visiblePosition + (isReverseLayout(layoutManager) == forwardDirection ? -1 : +1);
  if (snapToPosition < 0 || snapToPosition >= itemCount) {
    return RecyclerView.NO_POSITION;
  }
  return snapToPosition;
}
