/** 
 * Finds the best view candidate to request focus on using mFocusedItemPosition index of the previously focused item. It first traverses the adapter forward to find a focusable candidate and if no such candidate is found, it reverses the focus search direction for the items before the mFocusedItemPosition'th index;
 * @return The best candidate to request focus on, or null if no such candidate exists. Nullindicates all the existing adapter items are unfocusable.
 */
@Nullable private View findNextViewToFocus(){
  int startFocusSearchIndex=mState.mFocusedItemPosition != -1 ? mState.mFocusedItemPosition : 0;
  ViewHolder nextFocus;
  final int itemCount=mState.getItemCount();
  for (int i=startFocusSearchIndex; i < itemCount; i++) {
    nextFocus=findViewHolderForAdapterPosition(i);
    if (nextFocus == null) {
      break;
    }
    if (nextFocus.itemView.hasFocusable()) {
      return nextFocus.itemView;
    }
  }
  final int limit=Math.min(itemCount,startFocusSearchIndex);
  for (int i=limit - 1; i >= 0; i--) {
    nextFocus=findViewHolderForAdapterPosition(i);
    if (nextFocus == null) {
      return null;
    }
    if (nextFocus.itemView.hasFocusable()) {
      return nextFocus.itemView;
    }
  }
  return null;
}
