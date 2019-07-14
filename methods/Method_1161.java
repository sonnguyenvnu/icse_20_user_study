@Override public void setProgress(float progress,boolean immediate){
  if (mFadeDrawable.getDrawable(PROGRESS_BAR_IMAGE_INDEX) == null) {
    return;
  }
  mFadeDrawable.beginBatchMode();
  setProgress(progress);
  if (immediate) {
    mFadeDrawable.finishTransitionImmediately();
  }
  mFadeDrawable.endBatchMode();
}
