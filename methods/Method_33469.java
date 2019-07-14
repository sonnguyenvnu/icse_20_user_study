/** 
 * ???????
 */
protected void showLoading(){
  if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
    loadingView.setVisibility(View.VISIBLE);
  }
  if (!mAnimationDrawable.isRunning()) {
    mAnimationDrawable.start();
  }
  if (bindingView.getRoot().getVisibility() != View.GONE) {
    bindingView.getRoot().setVisibility(View.GONE);
  }
  if (errorView != null) {
    errorView.setVisibility(View.GONE);
  }
  if (emptyView != null && emptyView.getVisibility() != View.GONE) {
    emptyView.setVisibility(View.GONE);
  }
}
