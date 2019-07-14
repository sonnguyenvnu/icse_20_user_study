@Override protected void loadData(){
  if (!mIsPrepared || !mIsVisible || !mIsFirst) {
    return;
  }
  bindingView.vpGank.postDelayed(() -> {
    initFragmentList();
    MyFragmentPagerAdapter myAdapter=new MyFragmentPagerAdapter(getChildFragmentManager(),mFragments,mTitleList);
    bindingView.vpGank.setAdapter(myAdapter);
    myAdapter.notifyDataSetChanged();
    bindingView.tabGank.setTabMode(TabLayout.MODE_FIXED);
    bindingView.tabGank.setupWithViewPager(bindingView.vpGank);
    mIsFirst=false;
  }
,110);
}
