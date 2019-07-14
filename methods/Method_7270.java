boolean doStopCurrentWork(){
  if (mCurProcessor != null) {
    mCurProcessor.cancel(mInterruptIfStopped);
  }
  mStopped=true;
  return onStopCurrentWork();
}
