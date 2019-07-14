private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView view,int position,long deadlineNs){
  if (isPrefetchPositionAttached(view,position)) {
    return null;
  }
  RecyclerView.Recycler recycler=view.mRecycler;
  RecyclerView.ViewHolder holder;
  try {
    view.onEnterLayoutOrScroll();
    holder=recycler.tryGetViewHolderForPositionByDeadline(position,false,deadlineNs);
    if (holder != null) {
      if (holder.isBound() && !holder.isInvalid()) {
        recycler.recycleView(holder.itemView);
      }
 else {
        recycler.addViewHolderToRecycledViewPool(holder,false);
      }
    }
  }
  finally {
    view.onExitLayoutOrScroll(false);
  }
  return holder;
}
