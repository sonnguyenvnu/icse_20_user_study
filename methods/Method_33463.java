protected void showContentView(){
  if (loadingView != null && loadingView.getVisibility() != View.GONE) {
    loadingView.setVisibility(View.GONE);
  }
  if (mAnimationDrawable.isRunning()) {
    mAnimationDrawable.stop();
  }
  if (errorView != null) {
    errorView.setVisibility(View.GONE);
  }
  if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
    bindingView.getRoot().setVisibility(View.VISIBLE);
  }
}
