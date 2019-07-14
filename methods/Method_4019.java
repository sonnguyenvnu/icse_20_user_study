@Override public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder,@NonNull ItemHolderInfo preInfo,@NonNull ItemHolderInfo postInfo){
  if (preInfo.left != postInfo.left || preInfo.top != postInfo.top) {
    if (DEBUG) {
      Log.d(TAG,"PERSISTENT: " + viewHolder + " with view " + viewHolder.itemView);
    }
    return animateMove(viewHolder,preInfo.left,preInfo.top,postInfo.left,postInfo.top);
  }
  dispatchMoveFinished(viewHolder);
  return false;
}
