private void setCurrentPageImmediate(){
  if (viewPager != null) {
    currentPage=viewPager.getCurrentItem();
  }
 else {
    currentPage=0;
  }
  if (dotCenterX != null && dotCenterX.length != 0) {
    selectedDotX=dotCenterX[currentPage];
  }
 else {
    selectedDotX=0;
  }
}
