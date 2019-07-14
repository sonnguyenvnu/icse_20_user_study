public void reLoad(View view){
  downLoadThread.interrupt();
  mFlikerbar.reset();
  mRoundFlikerbar.reset();
  downLoad();
}
