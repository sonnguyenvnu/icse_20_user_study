protected void updateItemsColor(){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      ((ActionBarMenuItem)view).setIconColor(isActionMode ? parentActionBar.itemsActionModeColor : parentActionBar.itemsColor);
    }
  }
}
