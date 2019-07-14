@Override public void onSetSearchQuery(@NonNull String query){
  this.searchQuery=query;
  getLoadMore().reset();
  adapter.clear();
  if (!InputHelper.isEmpty(query)) {
    recycler.removeOnScrollListener(getLoadMore());
    recycler.addOnScrollListener(getLoadMore());
    onRefresh();
  }
}
