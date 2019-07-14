private void tl_3(){
  final ViewPager vp_3=ViewFindUtils.find(mDecorView,R.id.vp_2);
  vp_3.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
  mTabLayout_3.setTabData(mTitles_3);
  mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener(){
    @Override public void onTabSelect(    int position){
      vp_3.setCurrentItem(position);
    }
    @Override public void onTabReselect(    int position){
    }
  }
);
  vp_3.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
    @Override public void onPageScrolled(    int position,    float positionOffset,    int positionOffsetPixels){
    }
    @Override public void onPageSelected(    int position){
      mTabLayout_3.setCurrentTab(position);
    }
    @Override public void onPageScrollStateChanged(    int state){
    }
  }
);
  vp_3.setCurrentItem(1);
}
