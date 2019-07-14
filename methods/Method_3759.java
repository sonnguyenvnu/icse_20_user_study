static boolean isPrefetchPositionAttached(RecyclerView view,int position){
  final int childCount=view.mChildHelper.getUnfilteredChildCount();
  for (int i=0; i < childCount; i++) {
    View attachedView=view.mChildHelper.getUnfilteredChildAt(i);
    RecyclerView.ViewHolder holder=RecyclerView.getChildViewHolderInt(attachedView);
    if (holder.mPosition == position && !holder.isInvalid()) {
      return true;
    }
  }
  return false;
}
