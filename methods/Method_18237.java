void onDetach(LithoView lithoView){
  for (int i=0, size=mViewPagerListeners.size(); i < size; i++) {
    ViewPagerListener viewPagerListener=mViewPagerListeners.get(i);
    viewPagerListener.release();
  }
  mViewPagerListeners.clear();
}
