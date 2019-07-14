/** 
 * @return the first View whose start is before the start of this recycler view 
 */
@Nullable private static View findFirstViewBeforeStart(LayoutManager layoutManager,OrientationHelper helper){
  int childCount=layoutManager.getChildCount();
  if (childCount == 0) {
    return null;
  }
  View closestChild=null;
  final int start=helper.getStartAfterPadding();
  int absClosest=Integer.MAX_VALUE;
  for (int i=0; i < childCount; i++) {
    final View child=layoutManager.getChildAt(i);
    int childStart=helper.getDecoratedStart(child);
    int absDistance=Math.abs(childStart - start);
    if (childStart < start && absDistance < absClosest) {
      absClosest=absDistance;
      closestChild=child;
    }
  }
  return closestChild;
}
