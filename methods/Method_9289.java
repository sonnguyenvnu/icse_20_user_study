private void showPasscodeActivity(){
  if (passcodeView == null) {
    return;
  }
  SharedConfig.appLocked=true;
  if (SecretMediaViewer.hasInstance() && SecretMediaViewer.getInstance().isVisible()) {
    SecretMediaViewer.getInstance().closePhoto(false,false);
  }
 else   if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isVisible()) {
    PhotoViewer.getInstance().closePhoto(false,true);
  }
 else   if (ArticleViewer.hasInstance() && ArticleViewer.getInstance().isVisible()) {
    ArticleViewer.getInstance().close(false,true);
  }
  passcodeView.onShow();
  SharedConfig.isWaitingForPasscodeEnter=true;
  drawerLayoutContainer.setAllowOpenDrawer(false,false);
  passcodeView.setDelegate(() -> {
    SharedConfig.isWaitingForPasscodeEnter=false;
    if (passcodeSaveIntent != null) {
      handleIntent(passcodeSaveIntent,passcodeSaveIntentIsNew,passcodeSaveIntentIsRestore,true);
      passcodeSaveIntent=null;
    }
    drawerLayoutContainer.setAllowOpenDrawer(true,false);
    actionBarLayout.setVisibility(View.VISIBLE);
    actionBarLayout.showLastFragment();
    if (AndroidUtilities.isTablet()) {
      layersActionBarLayout.showLastFragment();
      rightActionBarLayout.showLastFragment();
      if (layersActionBarLayout.getVisibility() == View.INVISIBLE) {
        layersActionBarLayout.setVisibility(View.VISIBLE);
      }
      rightActionBarLayout.setVisibility(View.VISIBLE);
    }
  }
);
  actionBarLayout.setVisibility(View.INVISIBLE);
  if (AndroidUtilities.isTablet()) {
    if (layersActionBarLayout.getVisibility() == View.VISIBLE) {
      layersActionBarLayout.setVisibility(View.INVISIBLE);
    }
    rightActionBarLayout.setVisibility(View.INVISIBLE);
  }
}
