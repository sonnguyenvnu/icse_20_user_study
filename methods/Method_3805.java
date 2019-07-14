@Override public void onChildViewDetachedFromWindow(@NonNull View view){
  removeChildDrawingOrderCallbackIfNecessary(view);
  final ViewHolder holder=mRecyclerView.getChildViewHolder(view);
  if (holder == null) {
    return;
  }
  if (mSelected != null && holder == mSelected) {
    select(null,ACTION_STATE_IDLE);
  }
 else {
    endRecoverAnimation(holder,false);
    if (mPendingCleanup.remove(holder.itemView)) {
      mCallback.clearView(mRecyclerView,holder);
    }
  }
}
