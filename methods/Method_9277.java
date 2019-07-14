private void closeSearch(){
  searching=false;
  searchWas=false;
  adapter.setSearching(false);
  adapter.searchDialogs(null);
  listView.setFastScrollVisible(true);
  listView.setVerticalScrollBarEnabled(false);
  emptyView.setText(LocaleController.getString("NoContacts",R.string.NoContacts));
}
