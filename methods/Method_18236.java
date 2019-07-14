void onAttach(LithoView lithoView){
  if (!mComponentTree.isIncrementalMountEnabled()) {
    return;
  }
  ViewParent viewParent=lithoView.getParent();
  while (viewParent != null) {
    if (viewParent instanceof ViewPager) {
      final ViewPager viewPager=(ViewPager)viewParent;
      final IncrementalMountHelper.ViewPagerListener viewPagerListener=new ViewPagerListener(mComponentTree,viewPager);
      try {
        viewPager.addOnPageChangeListener(viewPagerListener);
      }
 catch (      ConcurrentModificationException e) {
        ViewCompat.postOnAnimation(viewPager,new Runnable(){
          @Override public void run(){
            viewPager.addOnPageChangeListener(viewPagerListener);
          }
        }
);
      }
      mViewPagerListeners.add(viewPagerListener);
    }
    viewParent=viewParent.getParent();
  }
}
