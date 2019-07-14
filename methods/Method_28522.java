protected void hideAppbar(){
  if (!toggled) {
    if (appBarLayout != null) {
      appBarLayout.setExpanded(false,true);
    }
    if (bottomNavigation != null) {
      bottomNavigation.setExpanded(false,true);
    }
    toggled=true;
  }
}
