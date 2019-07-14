/** 
 * @return True if an existing view holder needs to be updated
 */
private boolean hasUpdatedView(){
  final int childCount=mChildHelper.getChildCount();
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getChildAt(i));
    if (holder == null || holder.shouldIgnore()) {
      continue;
    }
    if (holder.isUpdated()) {
      return true;
    }
  }
  return false;
}
