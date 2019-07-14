@Override protected float calculateValue(long frameTimeNanos){
  float timingValue=getInput(DEFAULT_INPUT).getValue();
  return mInterpolator.getInterpolation(timingValue);
}
