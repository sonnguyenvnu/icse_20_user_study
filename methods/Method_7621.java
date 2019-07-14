private void registerListener(View anchor){
  if (mSuperScrollListener != null) {
    ViewTreeObserver vto=(anchor.getWindowToken() != null) ? anchor.getViewTreeObserver() : null;
    if (vto != mViewTreeObserver) {
      if (mViewTreeObserver != null && mViewTreeObserver.isAlive()) {
        mViewTreeObserver.removeOnScrollChangedListener(mSuperScrollListener);
      }
      if ((mViewTreeObserver=vto) != null) {
        vto.addOnScrollChangedListener(mSuperScrollListener);
      }
    }
  }
}
