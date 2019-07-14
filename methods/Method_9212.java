@Override protected void onBecomeFullyHidden(){
  if (closeSearchFieldOnHide) {
    if (actionBar != null) {
      actionBar.closeSearchField();
    }
    if (searchObject != null) {
      dialogsSearchAdapter.putRecentSearch(searchDialogId,searchObject);
      searchObject=null;
    }
    closeSearchFieldOnHide=false;
  }
  if (undoView[0] != null) {
    undoView[0].hide(true,0);
  }
}
