private void init(){
  mUltraViewPager=new BannerViewPager(getContext());
  mUltraViewPager.setId(R.id.TANGRAM_BANNER_ID);
  mIndicator=new BannerIndicator(getContext());
  addView(mUltraViewPager);
  addView(mIndicator);
  mIndicator.setPadding(mIndicatorGap,0,0,0);
  mScreenBroadcastReceiver=new ScreenBroadcastReceiver(this);
  filter.addAction(Intent.ACTION_SCREEN_ON);
  filter.addAction(Intent.ACTION_SCREEN_OFF);
  filter.addAction(Intent.ACTION_USER_PRESENT);
}
