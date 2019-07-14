/** 
 * ???????
 */
protected void showContentView(){
  if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
    bindingView.getRoot().setVisibility(View.VISIBLE);
  }
  if (loadingView != null && loadingView.getVisibility() != View.GONE) {
    loadingView.setVisibility(View.GONE);
  }
  if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
    mAnimationDrawable.stop();
  }
  if (errorView != null) {
    errorView.setVisibility(View.GONE);
  }
  if (emptyView != null && emptyView.getVisibility() != View.GONE) {
    emptyView.setVisibility(View.GONE);
  }
}
