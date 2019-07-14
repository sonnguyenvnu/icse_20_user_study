@VisibleForTesting int getFrameNumberWithinLoop(long timeInCurrentLoopMs){
  int frame=0;
  long currentDuration=0;
  do {
    currentDuration+=mAnimationInformation.getFrameDurationMs(frame);
    frame++;
  }
 while (timeInCurrentLoopMs >= currentDuration);
  return frame - 1;
}
