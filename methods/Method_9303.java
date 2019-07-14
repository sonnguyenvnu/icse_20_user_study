@Override public boolean needCloseLastFragment(ActionBarLayout layout){
  if (AndroidUtilities.isTablet()) {
    if (layout == actionBarLayout && layout.fragmentsStack.size() <= 1) {
      onFinish();
      finish();
      return false;
    }
 else     if (layout == rightActionBarLayout) {
      if (!tabletFullSize) {
        backgroundTablet.setVisibility(View.VISIBLE);
      }
    }
 else     if (layout == layersActionBarLayout && actionBarLayout.fragmentsStack.isEmpty() && layersActionBarLayout.fragmentsStack.size() == 1) {
      onFinish();
      finish();
      return false;
    }
  }
 else {
    if (layout.fragmentsStack.size() <= 1) {
      onFinish();
      finish();
      return false;
    }
    if (layout.fragmentsStack.size() >= 2 && !(layout.fragmentsStack.get(0) instanceof LoginActivity)) {
      drawerLayoutContainer.setAllowOpenDrawer(true,false);
    }
  }
  return true;
}
