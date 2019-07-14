/** 
 * If there is a pending scroll position or saved states, updates the anchor info from that data and returns true
 */
private boolean updateAnchorFromPendingData(RecyclerView.State state,AnchorInfo anchorInfo){
  if (state.isPreLayout() || mPendingScrollPosition == RecyclerView.NO_POSITION) {
    return false;
  }
  if (mPendingScrollPosition < 0 || mPendingScrollPosition >= state.getItemCount()) {
    mPendingScrollPosition=RecyclerView.NO_POSITION;
    mPendingScrollPositionOffset=INVALID_OFFSET;
    if (DEBUG) {
      Log.e(TAG,"ignoring invalid scroll position " + mPendingScrollPosition);
    }
    return false;
  }
  anchorInfo.mPosition=mPendingScrollPosition;
  if (mPendingSavedState != null && mPendingSavedState.hasValidAnchor()) {
    anchorInfo.mLayoutFromEnd=mPendingSavedState.mAnchorLayoutFromEnd;
    if (anchorInfo.mLayoutFromEnd) {
      anchorInfo.mCoordinate=mOrientationHelper.getEndAfterPadding() - mPendingSavedState.mAnchorOffset;
    }
 else {
      anchorInfo.mCoordinate=mOrientationHelper.getStartAfterPadding() + mPendingSavedState.mAnchorOffset;
    }
    return true;
  }
  if (mPendingScrollPositionOffset == INVALID_OFFSET) {
    View child=findViewByPosition(mPendingScrollPosition);
    if (child != null) {
      final int childSize=mOrientationHelper.getDecoratedMeasurement(child);
      if (childSize > mOrientationHelper.getTotalSpace()) {
        anchorInfo.assignCoordinateFromPadding();
        return true;
      }
      final int startGap=mOrientationHelper.getDecoratedStart(child) - mOrientationHelper.getStartAfterPadding();
      if (startGap < 0) {
        anchorInfo.mCoordinate=mOrientationHelper.getStartAfterPadding();
        anchorInfo.mLayoutFromEnd=false;
        return true;
      }
      final int endGap=mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(child);
      if (endGap < 0) {
        anchorInfo.mCoordinate=mOrientationHelper.getEndAfterPadding();
        anchorInfo.mLayoutFromEnd=true;
        return true;
      }
      anchorInfo.mCoordinate=anchorInfo.mLayoutFromEnd ? (mOrientationHelper.getDecoratedEnd(child) + mOrientationHelper.getTotalSpaceChange()) : mOrientationHelper.getDecoratedStart(child);
    }
 else {
      if (getChildCount() > 0) {
        int pos=getPosition(getChildAt(0));
        anchorInfo.mLayoutFromEnd=mPendingScrollPosition < pos == mPendingScrollPositionBottom;
      }
      anchorInfo.assignCoordinateFromPadding();
    }
    return true;
  }
  anchorInfo.mLayoutFromEnd=mPendingScrollPositionBottom;
  if (mPendingScrollPositionBottom) {
    anchorInfo.mCoordinate=mOrientationHelper.getEndAfterPadding() - mPendingScrollPositionOffset;
  }
 else {
    anchorInfo.mCoordinate=mOrientationHelper.getStartAfterPadding() + mPendingScrollPositionOffset;
  }
  return true;
}
