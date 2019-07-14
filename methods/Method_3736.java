/** 
 * Marks a child view as hidden
 * @param child  View to hide.
 */
private void hideViewInternal(View child){
  mHiddenViews.add(child);
  mCallback.onEnteredHiddenState(child);
}
