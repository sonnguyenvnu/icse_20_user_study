private int getFrameStartTime(int frameNumber){
  if (frameNumber == 0) {
    return 0;
  }
  if (mFrameStartTimes[frameNumber] != 0) {
    return mFrameStartTimes[frameNumber];
  }
  for (int i=0; i < frameNumber; i++) {
    mFrameStartTimes[frameNumber]+=mGifDecoder.getFrameDurationMs(i);
  }
  return mFrameStartTimes[frameNumber];
}
