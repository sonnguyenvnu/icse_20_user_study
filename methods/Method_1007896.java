public void start(){
  if (!mIsRunning) {
    mIsRunning=true;
    mLastTime=System.currentTimeMillis();
    invalidate();
  }
}
