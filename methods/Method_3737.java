/** 
 * Unmarks a child view as hidden.
 * @param child  View to hide.
 */
private boolean unhideViewInternal(View child){
  if (mHiddenViews.remove(child)) {
    mCallback.onLeftHiddenState(child);
    return true;
  }
 else {
    return false;
  }
}
