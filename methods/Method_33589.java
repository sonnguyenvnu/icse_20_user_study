private void initNewSlidingParams(){
  int titleBarAndStatusHeight=(int)(CommonUtils.getDimens(R.dimen.nav_bar_height) + getStatusBarHeight(this));
  slidingDistance=imageBgHeight - titleBarAndStatusHeight - (int)(CommonUtils.getDimens(R.dimen.nav_bar_height_more));
}
