protected void showAppbar(){
  if (toggled) {
    if (scrollerView.getY() == 0) {
      if (appBarLayout != null) {
        appBarLayout.setExpanded(true,true);
      }
      if (bottomNavigation != null) {
        bottomNavigation.setExpanded(true,true);
      }
      toggled=false;
    }
  }
}
