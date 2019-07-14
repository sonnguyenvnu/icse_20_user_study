@Override public void setImage(Drawable drawable,float progress,boolean immediate){
  drawable=WrappingUtils.maybeApplyLeafRounding(drawable,mRoundingParams,mResources);
  drawable.mutate();
  mActualImageWrapper.setDrawable(drawable);
  mFadeDrawable.beginBatchMode();
  fadeOutBranches();
  fadeInLayer(ACTUAL_IMAGE_INDEX);
  setProgress(progress);
  if (immediate) {
    mFadeDrawable.finishTransitionImmediately();
  }
  mFadeDrawable.endBatchMode();
}
