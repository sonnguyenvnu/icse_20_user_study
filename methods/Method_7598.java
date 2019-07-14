public void closeSearchField(boolean closeKeyboard){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      ActionBarMenuItem item=(ActionBarMenuItem)view;
      if (item.isSearchField()) {
        parentActionBar.onSearchFieldVisibilityChanged(false);
        item.toggleSearch(closeKeyboard);
        break;
      }
    }
  }
}
