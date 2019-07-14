boolean updateAnchorFromPendingData(RecyclerView.State state,AnchorInfo anchorInfo){
  if (state.isPreLayout() || mPendingScrollPosition == RecyclerView.NO_POSITION) {
    return false;
  }
  if (mPendingScrollPosition < 0 || mPendingScrollPosition >= state.getItemCount()) {
    mPendingScrollPosition=RecyclerView.NO_POSITION;
    mPendingScrollPositionOffset=INVALID_OFFSET;
    return false;
  }
  if (mPendingSavedState == null || mPendingSavedState.mAnchorPosition == RecyclerView.NO_POSITION || mPendingSavedState.mSpanOffsetsSize < 1) {
    final View child=findViewByPosition(mPendingScrollPosition);
    if (child != null) {
      anchorInfo.mPosition=mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
      if (mPendingScrollPositionOffset != INVALID_OFFSET) {
        if (anchorInfo.mLayoutFromEnd) {
          final int target=mPrimaryOrientation.getEndAfterPadding() - mPendingScrollPositionOffset;
          anchorInfo.mOffset=target - mPrimaryOrientation.getDecoratedEnd(child);
        }
 else {
          final int target=mPrimaryOrientation.getStartAfterPadding() + mPendingScrollPositionOffset;
          anchorInfo.mOffset=target - mPrimaryOrientation.getDecoratedStart(child);
        }
        return true;
      }
      final int childSize=mPrimaryOrientation.getDecoratedMeasurement(child);
      if (childSize > mPrimaryOrientation.getTotalSpace()) {
        anchorInfo.mOffset=anchorInfo.mLayoutFromEnd ? mPrimaryOrientation.getEndAfterPadding() : mPrimaryOrientation.getStartAfterPadding();
        return true;
      }
      final int startGap=mPrimaryOrientation.getDecoratedStart(child) - mPrimaryOrientation.getStartAfterPadding();
      if (startGap < 0) {
        anchorInfo.mOffset=-startGap;
        return true;
      }
      final int endGap=mPrimaryOrientation.getEndAfterPadding() - mPrimaryOrientation.getDecoratedEnd(child);
      if (endGap < 0) {
        anchorInfo.mOffset=endGap;
        return true;
      }
      anchorInfo.mOffset=INVALID_OFFSET;
    }
 else {
      anchorInfo.mPosition=mPendingScrollPosition;
      if (mPendingScrollPositionOffset == INVALID_OFFSET) {
        final int position=calculateScrollDirectionForPosition(anchorInfo.mPosition);
        anchorInfo.mLayoutFromEnd=position == LayoutState.LAYOUT_END;
        anchorInfo.assignCoordinateFromPadding();
      }
 else {
        anchorInfo.assignCoordinateFromPadding(mPendingScrollPositionOffset);
      }
      anchorInfo.mInvalidateOffsets=true;
    }
  }
 else {
    anchorInfo.mOffset=INVALID_OFFSET;
    anchorInfo.mPosition=mPendingScrollPosition;
  }
  return true;
}
