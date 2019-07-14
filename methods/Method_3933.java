/** 
 * Remove an  {@link ItemDecoration} from this RecyclerView.<p>The given decoration will no longer impact the measurement and drawing of item views.</p>
 * @param decor Decoration to remove
 * @see #addItemDecoration(ItemDecoration)
 */
public void removeItemDecoration(@NonNull ItemDecoration decor){
  if (mLayout != null) {
    mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or" + " layout");
  }
  mItemDecorations.remove(decor);
  if (mItemDecorations.isEmpty()) {
    setWillNotDraw(getOverScrollMode() == View.OVER_SCROLL_NEVER);
  }
  markItemDecorInsetsDirty();
  requestLayout();
}
