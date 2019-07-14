public synchronized void updateViewPort(int viewPortWidth,int viewPortHeight){
  if (mViewPortWidth == viewPortWidth && mViewPortHeight == viewPortHeight) {
    return;
  }
  mViewPortWidth=viewPortWidth;
  mViewPortHeight=viewPortHeight;
  determineScaleAndPosition();
}
