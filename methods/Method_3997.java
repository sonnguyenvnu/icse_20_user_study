@Nullable ViewHolder findViewHolderForPosition(int position,boolean checkNewPosition){
  final int childCount=mChildHelper.getUnfilteredChildCount();
  ViewHolder hidden=null;
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (holder != null && !holder.isRemoved()) {
      if (checkNewPosition) {
        if (holder.mPosition != position) {
          continue;
        }
      }
 else       if (holder.getLayoutPosition() != position) {
        continue;
      }
      if (mChildHelper.isHidden(holder.itemView)) {
        hidden=holder;
      }
 else {
        return holder;
      }
    }
  }
  return hidden;
}
