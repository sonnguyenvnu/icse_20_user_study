@Override public int getColumnCountForAccessibility(RecyclerView.Recycler recycler,RecyclerView.State state){
  if (mOrientation == VERTICAL) {
    return mSpanCount;
  }
  if (state.getItemCount() < 1) {
    return 0;
  }
  return getSpanGroupIndex(recycler,state,state.getItemCount() - 1) + 1;
}
