public int getFrameDurationMs(int frameNumber){
  if (!mDecoded) {
    throw new IllegalStateException("getFrameDurationMs called before decode");
  }
  return mFrameControls.get(frameNumber)[CONTROL_INDEX_DELAY];
}
