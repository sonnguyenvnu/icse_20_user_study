private void saveFocusInfo(){
  View child=null;
  if (mPreserveFocusAfterLayout && hasFocus() && mAdapter != null) {
    child=getFocusedChild();
  }
  final ViewHolder focusedVh=child == null ? null : findContainingViewHolder(child);
  if (focusedVh == null) {
    resetFocusInfo();
  }
 else {
    mState.mFocusedItemId=mAdapter.hasStableIds() ? focusedVh.getItemId() : NO_ID;
    mState.mFocusedItemPosition=mDataSetHasChangedAfterLayout ? NO_POSITION : (focusedVh.isRemoved() ? focusedVh.mOldPosition : focusedVh.getAdapterPosition());
    mState.mFocusedSubChildId=getDeepestFocusedViewWithId(focusedVh.itemView);
  }
}
