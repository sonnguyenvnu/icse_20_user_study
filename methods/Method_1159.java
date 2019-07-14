private void resetFade(){
  if (mFadeDrawable != null) {
    mFadeDrawable.beginBatchMode();
    mFadeDrawable.fadeInAllLayers();
    fadeOutBranches();
    fadeInLayer(PLACEHOLDER_IMAGE_INDEX);
    mFadeDrawable.finishTransitionImmediately();
    mFadeDrawable.endBatchMode();
  }
}
