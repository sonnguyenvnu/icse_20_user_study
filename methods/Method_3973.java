private void recoverFocusFromState(){
  if (!mPreserveFocusAfterLayout || mAdapter == null || !hasFocus() || getDescendantFocusability() == FOCUS_BLOCK_DESCENDANTS || (getDescendantFocusability() == FOCUS_BEFORE_DESCENDANTS && isFocused())) {
    return;
  }
  if (!isFocused()) {
    final View focusedChild=getFocusedChild();
    if (IGNORE_DETACHED_FOCUSED_CHILD && (focusedChild.getParent() == null || !focusedChild.hasFocus())) {
      if (mChildHelper.getChildCount() == 0) {
        requestFocus();
        return;
      }
    }
 else     if (!mChildHelper.isHidden(focusedChild)) {
      return;
    }
  }
  ViewHolder focusTarget=null;
  if (mState.mFocusedItemId != NO_ID && mAdapter.hasStableIds()) {
    focusTarget=findViewHolderForItemId(mState.mFocusedItemId);
  }
  View viewToFocus=null;
  if (focusTarget == null || mChildHelper.isHidden(focusTarget.itemView) || !focusTarget.itemView.hasFocusable()) {
    if (mChildHelper.getChildCount() > 0) {
      viewToFocus=findNextViewToFocus();
    }
  }
 else {
    viewToFocus=focusTarget.itemView;
  }
  if (viewToFocus != null) {
    if (mState.mFocusedSubChildId != NO_ID) {
      View child=viewToFocus.findViewById(mState.mFocusedSubChildId);
      if (child != null && child.isFocusable()) {
        viewToFocus=child;
      }
    }
    viewToFocus.requestFocus();
  }
}
