private void initNewSlidingParams(){
  int titleBarAndStatusHeight=(int)(CommonUtils.getDimens(R.dimen.nav_bar_height) + StatusBarUtil.getStatusBarHeight(this));
  slidingDistance=imageBgHeight - titleBarAndStatusHeight - (int)(CommonUtils.getDimens(R.dimen.base_header_activity_slide_more));
}
