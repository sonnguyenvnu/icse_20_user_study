public void onMenuButtonPressed(){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    View view=getChildAt(a);
    if (view instanceof ActionBarMenuItem) {
      ActionBarMenuItem item=(ActionBarMenuItem)view;
      if (item.getVisibility() != VISIBLE) {
        continue;
      }
      if (item.hasSubMenu()) {
        item.toggleSubMenu();
        break;
      }
 else       if (item.overrideMenuClick) {
        onItemClick((Integer)item.getTag());
        break;
      }
    }
  }
}
