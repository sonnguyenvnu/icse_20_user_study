public void setSearchTextColor(int color,boolean placeholder){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      ActionBarMenuItem item=(ActionBarMenuItem)view;
      if (item.isSearchField()) {
        if (placeholder) {
          item.getSearchField().setHintTextColor(color);
        }
 else {
          item.getSearchField().setTextColor(color);
        }
        break;
      }
    }
  }
}
