/** 
 * Sets the gap handling strategy for StaggeredGridLayoutManager. If the gapStrategy parameter is different than the current strategy, calling this method will trigger a layout request.
 * @param gapStrategy The new gap handling strategy. Should be{@link #GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS} or {@link #GAP_HANDLING_NONE}.
 * @see #getGapStrategy()
 */
public void setGapStrategy(int gapStrategy){
  assertNotInLayoutOrScroll(null);
  if (gapStrategy == mGapStrategy) {
    return;
  }
  if (gapStrategy != GAP_HANDLING_NONE && gapStrategy != GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS) {
    throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE " + "or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
  }
  mGapStrategy=gapStrategy;
  requestLayout();
}
