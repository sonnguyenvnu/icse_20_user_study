/** 
 * Mark all known views as invalid. Used in response to a, "the whole world might have changed" data change event.
 */
void markKnownViewsInvalid(){
  final int childCount=mChildHelper.getUnfilteredChildCount();
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (holder != null && !holder.shouldIgnore()) {
      holder.addFlags(ViewHolder.FLAG_UPDATE | ViewHolder.FLAG_INVALID);
    }
  }
  markItemDecorInsetsDirty();
  mRecycler.markKnownViewsInvalid();
}
