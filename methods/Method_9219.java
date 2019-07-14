private void closeSearch(){
  if (AndroidUtilities.isTablet()) {
    if (actionBar != null) {
      actionBar.closeSearchField();
    }
    if (searchObject != null) {
      dialogsSearchAdapter.putRecentSearch(searchDialogId,searchObject);
      searchObject=null;
    }
  }
 else {
    closeSearchFieldOnHide=true;
  }
}
