/** 
 * Invalidates all ItemDecorations. If RecyclerView has item decorations, calling this method will trigger a  {@link #requestLayout()} call.
 */
public void invalidateItemDecorations(){
  if (mItemDecorations.size() == 0) {
    return;
  }
  if (mLayout != null) {
    mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll" + " or layout");
  }
  markItemDecorInsetsDirty();
  requestLayout();
}
