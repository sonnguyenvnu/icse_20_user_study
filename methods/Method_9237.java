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
      handleIntent(passcodeSaveIntent,passcodeSaveIntentIsNew,passcodeSaveIntentIsRestore,true,passcodeSaveIntentAccount,passcodeSaveIntentState);
      passcodeSaveIntent=null;
    }
    drawerLayoutContainer.setAllowOpenDrawer(true,false);
    actionBarLayout.showLastFragment();
    if (AndroidUtilities.isTablet()) {
      layersActionBarLayout.showLastFragment();
    }
  }
);
}
