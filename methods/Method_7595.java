protected void redrawPopup(int color){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      ActionBarMenuItem item=(ActionBarMenuItem)view;
      item.redrawPopup(color);
    }
  }
}
