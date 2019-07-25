public void start(AnimatedPieViewConfig config){
  applyConfig(config);
  if (mConfig == null) {
    throw new NullPointerException("config must not be null");
  }
  mPieChartRender.prepare();
}
