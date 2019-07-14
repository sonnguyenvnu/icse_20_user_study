final public void onBindItem(int position,boolean showFromStart,BaseCell cell){
  if (isDetectingFastScroll) {
    if (position % fastScrollThreshold == 0) {
      long now=System.currentTimeMillis();
      if (now - lastAnchorTime < 1000) {
        mOnScrollListener=null;
        isDetectingFastScroll=false;
        onInternalPageFastScroll();
      }
      lastAnchorTime=System.currentTimeMillis();
    }
  }
  onBindItem(position,cell);
}
