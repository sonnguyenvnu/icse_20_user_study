public void onPageScrolled(int position,int first){
  if (currentPosition == position) {
    return;
  }
  currentPosition=position;
  if (position >= tabsContainer.getChildCount()) {
    return;
  }
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
