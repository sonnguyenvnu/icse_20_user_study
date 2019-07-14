/** 
 * Checks whether item index is valid
 * @param index the item index
 * @return true if item index is not out of bounds or the spinnerwheel is cyclic
 */
protected boolean isValidItemIndex(int index){
  return (mViewAdapter != null) && (mViewAdapter.getItemsCount() > 0) && (mIsCyclic || (index >= 0 && index < mViewAdapter.getItemsCount()));
}
