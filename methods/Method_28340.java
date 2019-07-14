@Override public void onNavigationChanged(@RepoPagerMvp.RepoNavigationType int navType){
  if (navType == RepoPagerMvp.PROFILE) {
    getPresenter().onModuleChanged(getSupportFragmentManager(),navType);
    bottomNavigation.setSelectedIndex(this.navType,true);
    return;
  }
  this.navType=navType;
  try {
    if (bottomNavigation.getSelectedIndex() != navType)     bottomNavigation.setSelectedIndex(navType,true);
  }
 catch (  Exception ignored) {
  }
  showHideFab();
  getPresenter().onModuleChanged(getSupportFragmentManager(),navType);
}
