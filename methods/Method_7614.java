public void setShowSearchProgress(boolean show){
  if (progressDrawable == null) {
    return;
  }
  if (show) {
    progressDrawable.startAnimation();
  }
 else {
    progressDrawable.stopAnimation();
  }
}
