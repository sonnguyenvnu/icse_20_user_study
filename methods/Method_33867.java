@Override public void onMove(float delta){
  if (getVisiableHeight() > 0 || delta > 0) {
    setVisiableHeight((int)delta + getVisiableHeight());
    if (mState <= STATE_RELEASE_TO_REFRESH) {
      if (getVisiableHeight() > mMeasuredHeight) {
        setState(STATE_RELEASE_TO_REFRESH);
      }
 else {
        setState(STATE_NORMAL);
      }
    }
  }
}
