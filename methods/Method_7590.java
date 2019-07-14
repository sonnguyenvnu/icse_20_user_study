protected void updateItemsBackgroundColor(){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      view.setBackgroundDrawable(Theme.createSelectorDrawable(isActionMode ? parentActionBar.itemsActionModeBackgroundColor : parentActionBar.itemsBackgroundColor));
    }
  }
}
