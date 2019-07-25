/** 
 * Link the TabLayout and the ViewPager2 together.
 * @throws IllegalStateException If the mediator is already attached, or the ViewPager2 has noadapter.
 */
public void attach(){
  if (mAttached) {
    throw new IllegalStateException("TabLayoutMediator is already attached");
  }
  mAdapter=mViewPager.getAdapter();
  if (mAdapter == null) {
    throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an " + "adapter");
  }
  mAttached=true;
  mOnPageChangeCallback=new TabLayoutOnPageChangeCallback(mTabLayout);
  mViewPager.registerOnPageChangeCallback(mOnPageChangeCallback);
  mOnTabSelectedListener=new ViewPagerOnTabSelectedListener(mViewPager);
  mTabLayout.addOnTabSelectedListener(mOnTabSelectedListener);
  if (mAutoRefresh) {
    mPagerAdapterObserver=new PagerAdapterObserver();
    mAdapter.registerAdapterDataObserver(mPagerAdapterObserver);
  }
  populateTabsFromPagerAdapter();
  mTabLayout.setScrollPosition(mViewPager.getCurrentItem(),0f,true);
}
