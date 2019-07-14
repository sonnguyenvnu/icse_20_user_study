private void updateSearchInterface(){
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
  if (searching && searchResult.isEmpty() || loadingRecent && lastSearchString == null) {
    emptyView.showProgress();
  }
 else {
    emptyView.showTextView();
  }
}
