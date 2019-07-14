private void checkBottomTabScroll(float dy){
  lastBottomScrollDy+=dy;
  int offset;
  if (pager.getCurrentItem() == 0) {
    offset=AndroidUtilities.dp(38);
  }
 else {
    offset=AndroidUtilities.dp(48);
  }
  if (lastBottomScrollDy >= offset) {
    showBottomTab(false,true);
  }
 else   if (lastBottomScrollDy <= -offset) {
    showBottomTab(true,true);
  }
 else   if (bottomTabContainer.getTag() == null && lastBottomScrollDy < 0 || bottomTabContainer.getTag() != null && lastBottomScrollDy > 0) {
    lastBottomScrollDy=0;
  }
}
