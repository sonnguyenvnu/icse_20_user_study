private void updateSearchResults(final ArrayList<LocaleController.LocaleInfo> arrCounties){
  AndroidUtilities.runOnUIThread(() -> {
    searchResult=arrCounties;
    searchListViewAdapter.notifyDataSetChanged();
  }
);
}
