/** 
 * Returns whether the specified view lies within the boundary condition of its parent view.
 * @param child The child view to be checked.
 * @param boundsFlags The flag against which the child view and parent view are matched.
 * @return True if the view meets the boundsFlag, false otherwise.
 */
boolean isViewWithinBoundFlags(View child,@ViewBounds int boundsFlags){
  mBoundFlags.setBounds(mCallback.getParentStart(),mCallback.getParentEnd(),mCallback.getChildStart(child),mCallback.getChildEnd(child));
  if (boundsFlags != 0) {
    mBoundFlags.resetFlags();
    mBoundFlags.addFlags(boundsFlags);
    return mBoundFlags.boundsMatch();
  }
  return false;
}
