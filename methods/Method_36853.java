public void initAdapter(){
  if (mBannerAdapter == null) {
    if (serviceManager != null) {
      GroupBasicAdapter adapter=serviceManager.getService(GroupBasicAdapter.class);
      RecyclerView.RecycledViewPool pool=serviceManager.getService(RecyclerView.RecycledViewPool.class);
      mBannerAdapter=new BannerAdapter(adapter,pool);
    }
  }
  if (mBannerWrapper == null) {
    mBannerWrapper=new UltraViewPagerAdapter(mBannerAdapter);
  }
}
