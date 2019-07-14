public void selectTab(int num){
  if (num < 0 || num >= tabCount) {
    return;
  }
  View tab=tabsContainer.getChildAt(num);
  tab.performClick();
}
