private void didSelectTab(int tab){
  if (selectedTab == tab) {
    return;
  }
  selectedTab=tab;
  animateToTab(tab);
  if (delegate != null) {
    delegate.didSelectTab(tab);
  }
}
