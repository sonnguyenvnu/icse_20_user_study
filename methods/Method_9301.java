@Override public boolean onKeyUp(int keyCode,KeyEvent event){
  if (keyCode == KeyEvent.KEYCODE_MENU && !SharedConfig.isWaitingForPasscodeEnter) {
    if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isVisible()) {
      return super.onKeyUp(keyCode,event);
    }
 else     if (ArticleViewer.hasInstance() && ArticleViewer.getInstance().isVisible()) {
      return super.onKeyUp(keyCode,event);
    }
    if (AndroidUtilities.isTablet()) {
      if (layersActionBarLayout.getVisibility() == View.VISIBLE && !layersActionBarLayout.fragmentsStack.isEmpty()) {
        layersActionBarLayout.onKeyUp(keyCode,event);
      }
 else       if (rightActionBarLayout.getVisibility() == View.VISIBLE && !rightActionBarLayout.fragmentsStack.isEmpty()) {
        rightActionBarLayout.onKeyUp(keyCode,event);
      }
 else {
        actionBarLayout.onKeyUp(keyCode,event);
      }
    }
 else {
      if (actionBarLayout.fragmentsStack.size() == 1) {
        if (!drawerLayoutContainer.isDrawerOpened()) {
          if (getCurrentFocus() != null) {
            AndroidUtilities.hideKeyboard(getCurrentFocus());
          }
          drawerLayoutContainer.openDrawer(false);
        }
 else {
          drawerLayoutContainer.closeDrawer(false);
        }
      }
 else {
        actionBarLayout.onKeyUp(keyCode,event);
      }
    }
  }
  return super.onKeyUp(keyCode,event);
}
