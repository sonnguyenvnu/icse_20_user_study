private void updateLayoutState(int anchorPosition,RecyclerView.State state){
  mLayoutState.mAvailable=0;
  mLayoutState.mCurrentPosition=anchorPosition;
  int startExtra=0;
  int endExtra=0;
  if (isSmoothScrolling()) {
    final int targetPos=state.getTargetScrollPosition();
    if (targetPos != RecyclerView.NO_POSITION) {
      if (mShouldReverseLayout == targetPos < anchorPosition) {
        endExtra=mPrimaryOrientation.getTotalSpace();
      }
 else {
        startExtra=mPrimaryOrientation.getTotalSpace();
      }
    }
  }
  final boolean clipToPadding=getClipToPadding();
  if (clipToPadding) {
    mLayoutState.mStartLine=mPrimaryOrientation.getStartAfterPadding() - startExtra;
    mLayoutState.mEndLine=mPrimaryOrientation.getEndAfterPadding() + endExtra;
  }
 else {
    mLayoutState.mEndLine=mPrimaryOrientation.getEnd() + endExtra;
    mLayoutState.mStartLine=-startExtra;
  }
  mLayoutState.mStopInFocusable=false;
  mLayoutState.mRecycle=true;
  mLayoutState.mInfinite=mPrimaryOrientation.getMode() == View.MeasureSpec.UNSPECIFIED && mPrimaryOrientation.getEnd() == 0;
}
