protected void changeStatusBarColor(boolean isTransparent){
  if (!isTransparent) {
    getWindow().setStatusBarColor(ViewHelper.getPrimaryDarkColor(this));
  }
}
