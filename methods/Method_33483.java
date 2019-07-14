protected void showContentView(){
  if (loadingView != null && loadingView.getVisibility() != View.GONE) {
    loadingView.setVisibility(View.GONE);
  }
  if (mAnimationDrawable.isRunning()) {
    mAnimationDrawable.stop();
  }
  if (errorView != null && errorView.getVisibility() != View.GONE) {
    errorView.setVisibility(View.GONE);
  }
  if (bindingContentView.getRoot().getVisibility() != View.VISIBLE) {
    bindingContentView.getRoot().setVisibility(View.VISIBLE);
  }
}
