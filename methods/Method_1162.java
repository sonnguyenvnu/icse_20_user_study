@Override public void setFailure(Throwable throwable){
  mFadeDrawable.beginBatchMode();
  fadeOutBranches();
  if (mFadeDrawable.getDrawable(FAILURE_IMAGE_INDEX) != null) {
    fadeInLayer(FAILURE_IMAGE_INDEX);
  }
 else {
    fadeInLayer(PLACEHOLDER_IMAGE_INDEX);
  }
  mFadeDrawable.endBatchMode();
}
