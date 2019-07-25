public void stop(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    frameStartTime=0;
    framesRendered=0;
    choreographer.removeFrameCallback(frameDropsMetrics);
  }
}
