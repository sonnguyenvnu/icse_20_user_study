public void selectIndex(int index){
  mCenterIndex=index;
  post(new Runnable(){
    @Override public void run(){
      scrollTo((int)(mCenterIndex * mIntervalDis - mMaxOverScrollDistance),0);
      invalidate();
      refreshCenter();
    }
  }
);
}
