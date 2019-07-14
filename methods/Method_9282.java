public void switchToAccount(int account,boolean removeAll){
  if (account == UserConfig.selectedAccount) {
    return;
  }
  ConnectionsManager.getInstance(currentAccount).setAppPaused(true,false);
  UserConfig.selectedAccount=account;
  UserConfig.getInstance(0).saveConfig(false);
  checkCurrentAccount();
  if (AndroidUtilities.isTablet()) {
    layersActionBarLayout.removeAllFragments();
    rightActionBarLayout.removeAllFragments();
    if (!tabletFullSize) {
      shadowTabletSide.setVisibility(View.VISIBLE);
      if (rightActionBarLayout.fragmentsStack.isEmpty()) {
        backgroundTablet.setVisibility(View.VISIBLE);
      }
      rightActionBarLayout.setVisibility(View.GONE);
    }
    layersActionBarLayout.setVisibility(View.GONE);
  }
  if (removeAll) {
    actionBarLayout.removeAllFragments();
  }
 else {
    actionBarLayout.removeFragmentFromStack(0);
  }
  DialogsActivity dialogsActivity=new DialogsActivity(null);
  dialogsActivity.setSideMenu(sideMenu);
  actionBarLayout.addFragmentToStack(dialogsActivity,0);
  drawerLayoutContainer.setAllowOpenDrawer(true,false);
  actionBarLayout.showLastFragment();
  if (AndroidUtilities.isTablet()) {
    layersActionBarLayout.showLastFragment();
    rightActionBarLayout.showLastFragment();
  }
  if (!ApplicationLoader.mainInterfacePaused) {
    ConnectionsManager.getInstance(currentAccount).setAppPaused(false,false);
  }
  if (UserConfig.getInstance(account).unacceptedTermsOfService != null) {
    showTosActivity(account,UserConfig.getInstance(account).unacceptedTermsOfService);
  }
}
