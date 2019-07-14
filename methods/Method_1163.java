@Override public void setRetry(Throwable throwable){
  mFadeDrawable.beginBatchMode();
  fadeOutBranches();
  if (mFadeDrawable.getDrawable(RETRY_IMAGE_INDEX) != null) {
    fadeInLayer(RETRY_IMAGE_INDEX);
  }
 else {
    fadeInLayer(PLACEHOLDER_IMAGE_INDEX);
  }
  mFadeDrawable.endBatchMode();
}
