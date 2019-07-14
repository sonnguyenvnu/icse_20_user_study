@Override public void invalidate(Rect dirty){
  if (mSuppressInvalidations) {
    mWasInvalidatedWhileSuppressed=true;
    return;
  }
  super.invalidate(dirty);
}
