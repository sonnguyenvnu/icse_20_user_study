@Override public long getTargetRenderTimeMs(int frameNumber){
  long targetRenderTimeMs=0;
  for (int i=0; i < frameNumber; i++) {
    targetRenderTimeMs+=mAnimationInformation.getFrameDurationMs(frameNumber);
  }
  return targetRenderTimeMs;
}
