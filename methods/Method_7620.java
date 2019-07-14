private void unregisterListener(){
  if (mSuperScrollListener != null && mViewTreeObserver != null) {
    if (mViewTreeObserver.isAlive()) {
      mViewTreeObserver.removeOnScrollChangedListener(mSuperScrollListener);
    }
    mViewTreeObserver=null;
  }
}
