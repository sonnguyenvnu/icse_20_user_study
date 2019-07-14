public boolean toggleSearch(boolean openKeyboard){
  if (searchContainer == null) {
    return false;
  }
  if (searchContainer.getVisibility() == VISIBLE) {
    if (listener == null || listener != null && listener.canCollapseSearch()) {
      if (openKeyboard) {
        AndroidUtilities.hideKeyboard(searchField);
      }
      searchField.setText("");
      searchContainer.setVisibility(GONE);
      searchField.clearFocus();
      setVisibility(VISIBLE);
      if (listener != null) {
        listener.onSearchCollapse();
      }
    }
    return false;
  }
 else {
    searchContainer.setVisibility(VISIBLE);
    setVisibility(GONE);
    searchField.setText("");
    searchField.requestFocus();
    if (openKeyboard) {
      AndroidUtilities.showKeyboard(searchField);
    }
    if (listener != null) {
      listener.onSearchExpand();
    }
    return true;
  }
}
