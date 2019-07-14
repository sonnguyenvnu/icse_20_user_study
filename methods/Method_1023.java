@Override public int getFrameNumberToRender(long animationTimeMs,long lastFrameTimeMs){
  if (!isInfiniteAnimation()) {
    long loopCount=animationTimeMs / getLoopDurationMs();
    if (loopCount >= mAnimationInformation.getLoopCount()) {
      return FRAME_NUMBER_DONE;
    }
  }
  long timeInCurrentLoopMs=animationTimeMs % getLoopDurationMs();
  return getFrameNumberWithinLoop(timeInCurrentLoopMs);
}
