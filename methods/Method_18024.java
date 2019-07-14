@Override public float calculateValue(long frameTimeNanos){
  if (mLastFrameTimeNs == Long.MIN_VALUE) {
    mLastFrameTimeNs=frameTimeNanos;
    float initialValue=getInput(INITIAL_INPUT).getValue();
    final float endValue=getInput(END_INPUT).getValue();
    mSpring.setCurrentValue(initialValue);
    mSpring.setEndValue(endValue);
    return initialValue;
  }
  final float endValue=getInput(END_INPUT).getValue();
  mSpring.setEndValue(endValue);
  if (isFinished()) {
    return endValue;
  }
  double timeDeltaSec=(frameTimeNanos - mLastFrameTimeNs) / NS_PER_SECOND;
  mSpring.advance(timeDeltaSec);
  mLastFrameTimeNs=frameTimeNanos;
  return (float)mSpring.getCurrentValue();
}
