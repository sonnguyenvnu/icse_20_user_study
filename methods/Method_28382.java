@Override public void onSetSearchQuery(@NonNull String query,boolean showRepoName){
  this.searchQuery=query;
  this.showRepoName=showRepoName;
  getLoadMore().reset();
  adapter.clear();
  adapter.showRepoName(showRepoName);
  if (!InputHelper.isEmpty(query)) {
    recycler.removeOnScrollListener(getLoadMore());
    recycler.addOnScrollListener(getLoadMore());
    onRefresh();
  }
}
