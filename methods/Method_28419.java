@Override public void onQueueSearch(@NonNull String query){
  this.searchQuery=query;
  if (getView() != null)   onSetSearchQuery(query);
}
