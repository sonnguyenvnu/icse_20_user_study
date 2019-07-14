@Override public boolean needCloseLastFragment(ActionBarLayout layout){
  if (AndroidUtilities.isTablet()) {
    if (layout == actionBarLayout && layout.fragmentsStack.size() <= 1) {
      onFinish();
      finish();
      return false;
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
  }
  return true;
}
