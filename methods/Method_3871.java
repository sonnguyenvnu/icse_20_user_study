/** 
 * When scrolling towards a child view, this method defines whether we should align the left or the right edge of the child with the parent RecyclerView.
 * @return SNAP_TO_START, SNAP_TO_END or SNAP_TO_ANY; depending on the current target vector
 * @see #SNAP_TO_START
 * @see #SNAP_TO_END
 * @see #SNAP_TO_ANY
 */
protected int getHorizontalSnapPreference(){
  return mTargetVector == null || mTargetVector.x == 0 ? SNAP_TO_ANY : mTargetVector.x > 0 ? SNAP_TO_END : SNAP_TO_START;
}
