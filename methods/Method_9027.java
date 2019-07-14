public void finishAddingTabs(){
  int count=tabsContainer.getChildCount();
  for (int a=0; a < count; a++) {
    TextView tab=(TextView)tabsContainer.getChildAt(a);
    tab.setTag(currentPosition == a ? Theme.key_actionBarTabActiveText : Theme.key_actionBarTabUnactiveText);
    tab.setTextColor(Theme.getColor(currentPosition == a ? Theme.key_actionBarTabActiveText : Theme.key_actionBarTabUnactiveText));
  }
}
