/** 
 * When scrolling towards a child view, this method defines whether we should align the top or the bottom edge of the child with the parent RecyclerView.
 * @return SNAP_TO_START, SNAP_TO_END or SNAP_TO_ANY; depending on the current target vector
 * @see #SNAP_TO_START
 * @see #SNAP_TO_END
 * @see #SNAP_TO_ANY
 */
protected int getVerticalSnapPreference(){
  return mTargetVector == null || mTargetVector.y == 0 ? SNAP_TO_ANY : mTargetVector.y > 0 ? SNAP_TO_END : SNAP_TO_START;
}
