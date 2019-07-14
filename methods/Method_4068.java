/** 
 * This is for internal use. Not necessarily the child closest to bottom but the first child we find that matches the criteria. This method does not do any sorting based on child's end coordinate, instead, it uses children order.
 */
View findFirstVisibleItemClosestToEnd(boolean fullyVisible){
  final int boundsStart=mPrimaryOrientation.getStartAfterPadding();
  final int boundsEnd=mPrimaryOrientation.getEndAfterPadding();
  View partiallyVisible=null;
  for (int i=getChildCount() - 1; i >= 0; i--) {
    final View child=getChildAt(i);
    final int childStart=mPrimaryOrientation.getDecoratedStart(child);
    final int childEnd=mPrimaryOrientation.getDecoratedEnd(child);
    if (childEnd <= boundsStart || childStart >= boundsEnd) {
      continue;
    }
    if (childEnd <= boundsEnd || !fullyVisible) {
      return child;
    }
    if (partiallyVisible == null) {
      partiallyVisible=child;
    }
  }
  return partiallyVisible;
}
