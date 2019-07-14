/** 
 * Checks if this ViewNodeInfo is equal to the  {@param other}
 * @param other the other ViewNodeInfo
 * @return {@code true} iff this NodeInfo is equal to the {@param other}.
 */
public boolean isEquivalentTo(ViewNodeInfo other){
  if (this == other) {
    return true;
  }
  if (other == null) {
    return false;
  }
  if (!ComparableDrawable.isEquivalentTo(mBackground,other.mBackground)) {
    return false;
  }
  if (!ComparableDrawable.isEquivalentTo(mForeground,other.mForeground)) {
    return false;
  }
  if (!CommonUtils.equals(mPadding,other.mPadding)) {
    return false;
  }
  if (!CommonUtils.equals(mExpandedTouchBounds,other.mExpandedTouchBounds)) {
    return false;
  }
  if (!CommonUtils.equals(mLayoutDirection,other.mLayoutDirection)) {
    return false;
  }
  if (mStateListAnimatorRes != other.mStateListAnimatorRes) {
    return false;
  }
  if (!CommonUtils.equals(mStateListAnimator,other.mStateListAnimator)) {
    return false;
  }
  return true;
}
