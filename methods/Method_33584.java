private void initNewSlidingParams(){
  int headerSize=getResources().getDimensionPixelOffset(R.dimen.new_home_header_size);
  int navBarHeight=getResources().getDimensionPixelOffset(R.dimen.nav_bar_height) + 2 * getStatusBarHeight(this);
  slidingDistance=headerSize - navBarHeight;
  Log.d("HomeFragment","slidingDistance" + slidingDistance);
}
