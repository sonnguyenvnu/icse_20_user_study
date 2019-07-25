@Override public void hide(boolean animate){
  if (animate && mActivityRoot != null) {
    initRowBadgeCenter();
    animateHide(mRowBadgeCenter);
  }
 else {
    setBadgeNumber(0);
  }
}
