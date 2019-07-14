public void setSearchFieldHint(CharSequence hint){
  if (searchFieldCaption == null) {
    return;
  }
  searchField.setHint(hint);
  setContentDescription(hint);
}
