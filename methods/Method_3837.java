public void scrollToPositionWithOffset(int position,int offset,boolean bottom){
  mPendingScrollPosition=position;
  mPendingScrollPositionOffset=offset;
  mPendingScrollPositionBottom=bottom;
  if (mPendingSavedState != null) {
    mPendingSavedState.invalidateAnchor();
  }
  requestLayout();
}
