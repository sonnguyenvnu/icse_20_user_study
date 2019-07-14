public void setAllowOpenDrawer(boolean value,boolean animated){
  allowOpenDrawer=value;
  if (!allowOpenDrawer && drawerPosition != 0) {
    if (!animated) {
      setDrawerPosition(0);
      onDrawerAnimationEnd(false);
    }
 else {
      closeDrawer(true);
    }
  }
}
