@Override public void onScrolled(boolean isUp){
  if (fab != null) {
    if (isUp) {
      fab.hide();
    }
 else {
      fab.show();
    }
  }
}
