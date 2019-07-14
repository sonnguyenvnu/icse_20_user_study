private void initFlikerProgressBar(){
  if (!mFlikerbar.isFinish()) {
    mFlikerbar.toggle();
    mRoundFlikerbar.toggle();
    if (mFlikerbar.isStop()) {
      downLoadThread.interrupt();
    }
 else {
      downLoad();
    }
  }
}
