public void openSearch(boolean openKeyboard){
  if (searchContainer == null || searchContainer.getVisibility() == VISIBLE || parentMenu == null) {
    return;
  }
  parentMenu.parentActionBar.onSearchFieldVisibilityChanged(toggleSearch(openKeyboard));
}
