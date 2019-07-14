@Override public boolean isFinished(){
  return mLastValueTimeNs >= mExpectedEndTimeNs;
}
