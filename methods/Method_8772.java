public View getTab(int position){
  if (position < 0 || position >= tabsContainer.getChildCount()) {
    return null;
  }
  return tabsContainer.getChildAt(position);
}
