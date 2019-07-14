public void clearRecentHashtags(){
  searchAdapterHelper.clearRecentHashtags();
  searchResultHashtags.clear();
  notifyDataSetChanged();
  if (delegate != null) {
    delegate.needChangePanelVisibility(false);
  }
}
