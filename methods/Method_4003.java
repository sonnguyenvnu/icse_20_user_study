void dispatchOnScrollStateChanged(int state){
  if (mLayout != null) {
    mLayout.onScrollStateChanged(state);
  }
  onScrollStateChanged(state);
  if (mScrollListener != null) {
    mScrollListener.onScrollStateChanged(this,state);
  }
  if (mScrollListeners != null) {
    for (int i=mScrollListeners.size() - 1; i >= 0; i--) {
      mScrollListeners.get(i).onScrollStateChanged(this,state);
    }
  }
}
