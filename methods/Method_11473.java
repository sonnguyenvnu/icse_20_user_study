private void tl_2(){
  mTabLayout_2.setTabData(mTabEntities);
  mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener(){
    @Override public void onTabSelect(    int position){
      mViewPager.setCurrentItem(position);
    }
    @Override public void onTabReselect(    int position){
      if (position == 0) {
        mTabLayout_2.showMsg(0,mRandom.nextInt(100) + 1);
      }
    }
  }
);
  mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
    @Override public void onPageScrolled(    int position,    float positionOffset,    int positionOffsetPixels){
    }
    @Override public void onPageSelected(    int position){
      mTabLayout_2.setCurrentTab(position);
    }
    @Override public void onPageScrollStateChanged(    int state){
    }
  }
);
  mViewPager.setCurrentItem(1);
}
