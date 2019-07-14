@Override public void onQueueSearch(@NonNull String query,boolean showRepoName){
  this.searchQuery=query;
  if (getView() != null)   onSetSearchQuery(query,showRepoName);
}
