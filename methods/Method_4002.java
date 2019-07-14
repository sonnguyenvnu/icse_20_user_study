void dispatchOnScrolled(int hresult,int vresult){
  mDispatchScrollCounter++;
  final int scrollX=getScrollX();
  final int scrollY=getScrollY();
  onScrollChanged(scrollX,scrollY,scrollX,scrollY);
  onScrolled(hresult,vresult);
  if (mScrollListener != null) {
    mScrollListener.onScrolled(this,hresult,vresult);
  }
  if (mScrollListeners != null) {
    for (int i=mScrollListeners.size() - 1; i >= 0; i--) {
      mScrollListeners.get(i).onScrolled(this,hresult,vresult);
    }
  }
  mDispatchScrollCounter--;
}
