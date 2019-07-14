@Override protected void loadData(){
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  bindingView.vpGank.postDelayed(() -> {
    initFragmentList();
    MyFragmentPagerAdapter myAdapter=new MyFragmentPagerAdapter(getChildFragmentManager(),mFragments,mTitleList);
    bindingView.vpGank.setAdapter(myAdapter);
    myAdapter.notifyDataSetChanged();
    bindingView.vpGank.setOffscreenPageLimit(3);
    bindingView.tabGank.setTabMode(TabLayout.MODE_FIXED);
    bindingView.tabGank.setupWithViewPager(bindingView.vpGank);
    initRxBus();
    mIsFirst=false;
  }
,120);
}
