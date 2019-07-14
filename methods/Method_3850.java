/** 
 * Convenience method to find the visible child closes to end. Caller should check if it has enough children.
 * @param completelyVisible Whether child should be completely visible or not
 * @return The first visible child closest to end of the layout from user's perspective.
 */
View findFirstVisibleChildClosestToEnd(boolean completelyVisible,boolean acceptPartiallyVisible){
  if (mShouldReverseLayout) {
    return findOneVisibleChild(0,getChildCount(),completelyVisible,acceptPartiallyVisible);
  }
 else {
    return findOneVisibleChild(getChildCount() - 1,-1,completelyVisible,acceptPartiallyVisible);
  }
}
