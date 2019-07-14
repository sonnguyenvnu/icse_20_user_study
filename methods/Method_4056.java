private void applyPendingSavedState(AnchorInfo anchorInfo){
  if (DEBUG) {
    Log.d(TAG,"found saved state: " + mPendingSavedState);
  }
  if (mPendingSavedState.mSpanOffsetsSize > 0) {
    if (mPendingSavedState.mSpanOffsetsSize == mSpanCount) {
      for (int i=0; i < mSpanCount; i++) {
        mSpans[i].clear();
        int line=mPendingSavedState.mSpanOffsets[i];
        if (line != Span.INVALID_LINE) {
          if (mPendingSavedState.mAnchorLayoutFromEnd) {
            line+=mPrimaryOrientation.getEndAfterPadding();
          }
 else {
            line+=mPrimaryOrientation.getStartAfterPadding();
          }
        }
        mSpans[i].setLine(line);
      }
    }
 else {
      mPendingSavedState.invalidateSpanInfo();
      mPendingSavedState.mAnchorPosition=mPendingSavedState.mVisibleAnchorPosition;
    }
  }
  mLastLayoutRTL=mPendingSavedState.mLastLayoutRTL;
  setReverseLayout(mPendingSavedState.mReverseLayout);
  resolveShouldLayoutReverse();
  if (mPendingSavedState.mAnchorPosition != RecyclerView.NO_POSITION) {
    mPendingScrollPosition=mPendingSavedState.mAnchorPosition;
    anchorInfo.mLayoutFromEnd=mPendingSavedState.mAnchorLayoutFromEnd;
  }
 else {
    anchorInfo.mLayoutFromEnd=mShouldReverseLayout;
  }
  if (mPendingSavedState.mSpanLookupSize > 1) {
    mLazySpanLookup.mData=mPendingSavedState.mSpanLookup;
    mLazySpanLookup.mFullSpanItems=mPendingSavedState.mFullSpanItems;
  }
}
