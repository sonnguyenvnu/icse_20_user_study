/** 
 * Unlink the TabLayout and the ViewPager
 */
public void detach(){
  mAdapter.unregisterAdapterDataObserver(mPagerAdapterObserver);
  mTabLayout.removeOnTabSelectedListener(mOnTabSelectedListener);
  mViewPager.unregisterOnPageChangeCallback(mOnPageChangeCallback);
  mPagerAdapterObserver=null;
  mOnTabSelectedListener=null;
  mOnPageChangeCallback=null;
  mAttached=false;
}
