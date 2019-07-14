@Override public void onNavigationChanged(@MainMvp.NavigationType int navType){
  if (navType == MainMvp.PROFILE) {
    getPresenter().onModuleChanged(getSupportFragmentManager(),navType);
    bottomNavigation.setSelectedIndex(this.navType,true);
    return;
  }
  this.navType=navType;
  if (bottomNavigation.getSelectedIndex() != navType)   bottomNavigation.setSelectedIndex(navType,true);
  hideShowShadow(navType == MainMvp.FEEDS);
  getPresenter().onModuleChanged(getSupportFragmentManager(),navType);
}
