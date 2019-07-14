/** 
 * Convenience method to find the visible child closes to start. Caller should check if it has enough children.
 * @param completelyVisible Whether child should be completely visible or not
 * @return The first visible child closest to start of the layout from user's perspective.
 */
View findFirstVisibleChildClosestToStart(boolean completelyVisible,boolean acceptPartiallyVisible){
  if (mShouldReverseLayout) {
    return findOneVisibleChild(getChildCount() - 1,-1,completelyVisible,acceptPartiallyVisible);
  }
 else {
    return findOneVisibleChild(0,getChildCount(),completelyVisible,acceptPartiallyVisible);
  }
}
