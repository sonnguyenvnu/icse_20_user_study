void dispatchChildAttached(View child){
  final ViewHolder viewHolder=getChildViewHolderInt(child);
  onChildAttachedToWindow(child);
  if (mAdapter != null && viewHolder != null) {
    mAdapter.onViewAttachedToWindow(viewHolder);
  }
  if (mOnChildAttachStateListeners != null) {
    final int cnt=mOnChildAttachStateListeners.size();
    for (int i=cnt - 1; i >= 0; i--) {
      mOnChildAttachStateListeners.get(i).onChildViewAttachedToWindow(child);
    }
  }
}
