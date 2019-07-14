/** 
 * This is for internal use. Not necessarily the child closest to start but the first child we find that matches the criteria. This method does not do any sorting based on child's start coordinate, instead, it uses children order.
 */
View findFirstVisibleItemClosestToStart(boolean fullyVisible){
  final int boundsStart=mPrimaryOrientation.getStartAfterPadding();
  final int boundsEnd=mPrimaryOrientation.getEndAfterPadding();
  final int limit=getChildCount();
  View partiallyVisible=null;
  for (int i=0; i < limit; i++) {
    final View child=getChildAt(i);
    final int childStart=mPrimaryOrientation.getDecoratedStart(child);
    final int childEnd=mPrimaryOrientation.getDecoratedEnd(child);
    if (childEnd <= boundsStart || childStart >= boundsEnd) {
      continue;
    }
    if (childStart >= boundsStart || !fullyVisible) {
      return child;
    }
    if (partiallyVisible == null) {
      partiallyVisible=child;
    }
  }
  return partiallyVisible;
}
