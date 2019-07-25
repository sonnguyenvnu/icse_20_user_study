public void start(){
  if (mBannerViewPagerAdapter == null) {
    mBannerViewPagerAdapter=new BannerViewPagerAdapter();
  }
  setAdapter(mBannerViewPagerAdapter);
  setCurrentItem(1);
  startAutoPlay();
}
