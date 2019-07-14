private static float computeDistancePerChild(RecyclerView.LayoutManager layoutManager,OrientationHelper helper){
  View minPosView=null;
  View maxPosView=null;
  int minPos=Integer.MAX_VALUE;
  int maxPos=Integer.MIN_VALUE;
  int childCount=layoutManager.getChildCount();
  if (childCount == 0) {
    return INVALID_DISTANCE;
  }
  for (int i=0; i < childCount; i++) {
    View child=layoutManager.getChildAt(i);
    final int pos=layoutManager.getPosition(child);
    if (pos == RecyclerView.NO_POSITION) {
      continue;
    }
    if (pos < minPos) {
      minPos=pos;
      minPosView=child;
    }
    if (pos > maxPos) {
      maxPos=pos;
      maxPosView=child;
    }
  }
  if (minPosView == null || maxPosView == null) {
    return INVALID_DISTANCE;
  }
  int start=Math.min(helper.getDecoratedStart(minPosView),helper.getDecoratedStart(maxPosView));
  int end=Math.max(helper.getDecoratedEnd(minPosView),helper.getDecoratedEnd(maxPosView));
  int distance=end - start;
  if (distance == 0) {
    return INVALID_DISTANCE;
  }
  return 1f * distance / ((maxPos - minPos) + 1);
}
