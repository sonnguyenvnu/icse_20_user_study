private void selectTab(int position,boolean fromViewPager){
  if (!fromViewPager) {
    onShowFilterMenu(getModelAtIndex(position),ViewHelper.getTabTextView(tabs,position));
    pager.setCurrentItem(position);
  }
 else {
    TabLayout.Tab tab=tabs.getTabAt(position);
    if (tab != null) {
      tab.setTag("hello");
      if (!tab.isSelected())       tab.select();
    }
  }
}
