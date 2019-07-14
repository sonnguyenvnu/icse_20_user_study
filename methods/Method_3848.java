/** 
 * Convenience method to find the child closes to start. Caller should check it has enough children.
 * @return The child closes to start of the layout from user's perspective.
 */
private View getChildClosestToStart(){
  return getChildAt(mShouldReverseLayout ? getChildCount() - 1 : 0);
}
