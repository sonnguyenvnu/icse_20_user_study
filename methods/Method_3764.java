@Override public int getRowCountForAccessibility(RecyclerView.Recycler recycler,RecyclerView.State state){
  if (mOrientation == HORIZONTAL) {
    return mSpanCount;
  }
  if (state.getItemCount() < 1) {
    return 0;
  }
  return getSpanGroupIndex(recycler,state,state.getItemCount() - 1) + 1;
}
