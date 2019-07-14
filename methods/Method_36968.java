private boolean scrollNextPage(){
  direction=1;
  boolean isChange=false;
  if (mUltraViewPager != null && mUltraViewPager.getAdapter() != null && mUltraViewPager.getAdapter().getCount() > 0) {
    final int curr=mUltraViewPager.getCurrentItemFake();
    int nextPage=0;
    if (curr < mUltraViewPager.getAdapter().getCount() - 1) {
      nextPage=curr + 1;
      isChange=true;
    }
    mUltraViewPager.setCurrentItemFake(nextPage,true);
  }
  return isChange;
}
