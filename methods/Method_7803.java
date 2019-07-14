private void updateSearchResults(final ArrayList<TLObject> users,final ArrayList<CharSequence> names){
  AndroidUtilities.runOnUIThread(() -> {
    searchResult=users;
    searchResultNames=names;
    searchAdapterHelper.mergeResults(users);
    notifyDataSetChanged();
  }
);
}
