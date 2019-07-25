public void start(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    choreographer.postFrameCallback(frameDropsMetrics);
  }
}
