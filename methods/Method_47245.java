private void invalidateVisibility(){
  if (recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() == 0 || recyclerView.getChildAt(0) == null || isRecyclerViewScrollable()) {
    setVisibility(INVISIBLE);
  }
 else {
    setVisibility(VISIBLE);
  }
}
