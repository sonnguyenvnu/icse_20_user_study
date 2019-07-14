private void updateVisibleTrendingSets(){
  if (layoutManager == null) {
    return;
  }
  int first=layoutManager.findFirstVisibleItemPosition();
  if (first == RecyclerView.NO_POSITION) {
    return;
  }
  int last=layoutManager.findLastVisibleItemPosition();
  if (last == RecyclerView.NO_POSITION) {
    return;
  }
  listAdapter.notifyItemRangeChanged(first,last - first + 1);
}
