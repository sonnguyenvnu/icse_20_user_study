private boolean isRecyclerViewScrollable(){
  return recyclerView.getChildAt(0).getHeight() * recyclerView.getAdapter().getItemCount() / columns <= getHeightMinusPadding() || recyclerView.getAdapter().getItemCount() / columns < 25;
}
