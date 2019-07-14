/** 
 * Return the child view that is currently closest to the center of this parent.
 * @param layoutManager The {@link RecyclerView.LayoutManager} associated with the attached{@link RecyclerView}.
 * @param helper The relevant {@link OrientationHelper} for the attached {@link RecyclerView}.
 * @return the child view that is currently closest to the center of this parent.
 */
@Nullable private View findCenterView(RecyclerView.LayoutManager layoutManager,OrientationHelper helper){
  int childCount=layoutManager.getChildCount();
  if (childCount == 0) {
    return null;
  }
  View closestChild=null;
  final int center;
  if (layoutManager.getClipToPadding()) {
    center=helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
  }
 else {
    center=helper.getEnd() / 2;
  }
  int absClosest=Integer.MAX_VALUE;
  for (int i=0; i < childCount; i++) {
    final View child=layoutManager.getChildAt(i);
    int childCenter=helper.getDecoratedStart(child) + (helper.getDecoratedMeasurement(child) / 2);
    int absDistance=Math.abs(childCenter - center);
    if (absDistance < absClosest) {
      absClosest=absDistance;
      closestChild=child;
    }
  }
  return closestChild;
}
