public void openSearchField(boolean toggle,String text,boolean animated){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      ActionBarMenuItem item=(ActionBarMenuItem)view;
      if (item.isSearchField()) {
        if (toggle) {
          parentActionBar.onSearchFieldVisibilityChanged(item.toggleSearch(true));
        }
        item.setSearchFieldText(text,animated);
        item.getSearchField().setSelection(text.length());
        break;
      }
    }
  }
}
