private void increaseProgress(final FloatingActionButton fab,int i){
  if (i <= mMaxProgress) {
    fab.setProgress(i,false);
    final int progress=++i;
    mUiHandler.postDelayed(new Runnable(){
      @Override public void run(){
        increaseProgress(fab,progress);
      }
    }
,30);
  }
 else {
    mUiHandler.postDelayed(new Runnable(){
      @Override public void run(){
        fab.hideProgress();
      }
    }
,200);
    mProgressTypes.offer(ProgressType.PROGRESS_NO_ANIMATION);
  }
}
