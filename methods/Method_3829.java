/** 
 * Finds an anchor child from existing Views. Most of the time, this is the view closest to start or end that has a valid position (e.g. not removed). <p> If a child has focus, it is given priority.
 */
private boolean updateAnchorFromChildren(RecyclerView.Recycler recycler,RecyclerView.State state,AnchorInfo anchorInfo){
  if (getChildCount() == 0) {
    return false;
  }
  final View focused=getFocusedChild();
  if (focused != null && anchorInfo.isViewValidAsAnchor(focused,state)) {
    anchorInfo.assignFromViewAndKeepVisibleRect(focused,getPosition(focused));
    return true;
  }
  if (mLastStackFromEnd != mStackFromEnd) {
    return false;
  }
  View referenceChild=anchorInfo.mLayoutFromEnd ? findReferenceChildClosestToEnd(recycler,state) : findReferenceChildClosestToStart(recycler,state);
  if (referenceChild != null) {
    anchorInfo.assignFromView(referenceChild,getPosition(referenceChild));
    if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
      final boolean notVisible=mOrientationHelper.getDecoratedStart(referenceChild) >= mOrientationHelper.getEndAfterPadding() || mOrientationHelper.getDecoratedEnd(referenceChild) < mOrientationHelper.getStartAfterPadding();
      if (notVisible) {
        anchorInfo.mCoordinate=anchorInfo.mLayoutFromEnd ? mOrientationHelper.getEndAfterPadding() : mOrientationHelper.getStartAfterPadding();
      }
    }
    return true;
  }
  return false;
}
