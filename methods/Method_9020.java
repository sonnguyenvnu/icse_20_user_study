public void onPageScrolled(int position,int first){
  if (currentPosition == position) {
    return;
  }
  View currentTab=tabsContainer.getChildAt(currentPosition);
  if (currentTab != null) {
    startAnimationPosition=currentTab.getLeft();
    positionAnimationProgress=0.0f;
    animateFromPosition=true;
    lastAnimationTime=SystemClock.uptimeMillis();
  }
 else {
    animateFromPosition=false;
  }
  currentPosition=position;
  if (position >= tabsContainer.getChildCount()) {
    return;
  }
  positionAnimationProgress=0.0f;
  for (int a=0; a < tabsContainer.getChildCount(); a++) {
    tabsContainer.getChildAt(a).setSelected(a == position);
  }
  if (first == position && position > 1) {
    scrollToChild(position - 1);
  }
 else {
    scrollToChild(position);
  }
  invalidate();
}
