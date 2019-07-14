private void updateRefreshing(){
  boolean hasBroadcast=mBroadcastResource.has();
  ViewUtils.fadeToVisibility(mProgress,!hasBroadcast);
  ViewUtils.fadeToVisibility(mTextAndContentLayout,hasBroadcast);
}
