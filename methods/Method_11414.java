private int findPinnedHeaderPosition(RecyclerView parent,int fromPosition){
  if (fromPosition > mAdapter.getItemCount() || fromPosition < 0) {
    return -1;
  }
  for (int position=fromPosition; position >= 0; position--) {
    final int viewType=mAdapter.getItemViewType(position);
    if (isPinnedViewType(parent,position,viewType)) {
      return position;
    }
  }
  return -1;
}
