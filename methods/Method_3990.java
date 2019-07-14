/** 
 * Rebind existing views for the given range, or create as needed.
 * @param positionStart Adapter position to start at
 * @param itemCount Number of views that must explicitly be rebound
 */
void viewRangeUpdate(int positionStart,int itemCount,Object payload){
  final int childCount=mChildHelper.getUnfilteredChildCount();
  final int positionEnd=positionStart + itemCount;
  for (int i=0; i < childCount; i++) {
    final View child=mChildHelper.getUnfilteredChildAt(i);
    final ViewHolder holder=getChildViewHolderInt(child);
    if (holder == null || holder.shouldIgnore()) {
      continue;
    }
    if (holder.mPosition >= positionStart && holder.mPosition < positionEnd) {
      holder.addFlags(ViewHolder.FLAG_UPDATE);
      holder.addChangePayload(payload);
      ((LayoutParams)child.getLayoutParams()).mInsetsDirty=true;
    }
  }
  mRecycler.viewRangeUpdate(positionStart,itemCount);
}
