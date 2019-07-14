/** 
 * Return the child view that is currently closest to the start of this parent.
 * @param layoutManager The {@link LayoutManager} associated with the attached {@link RecyclerView}.
 * @param helper The relevant {@link OrientationHelper} for the attached {@link RecyclerView}.
 * @return the child view that is currently closest to the start of this parent.
 */
@Nullable private static View findViewClosestToStart(LayoutManager layoutManager,OrientationHelper helper){
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
    if (absDistance < absClosest) {
      absClosest=absDistance;
      closestChild=child;
    }
  }
  return closestChild;
}
