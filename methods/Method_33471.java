/** 
 * ?????????????
 */
protected void showError(){
  ViewStub viewStub=getView(R.id.vs_error_refresh);
  if (viewStub != null) {
    errorView=viewStub.inflate();
    errorView.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        showLoading();
        onRefresh();
      }
    }
);
  }
  if (errorView != null) {
    errorView.setVisibility(View.VISIBLE);
  }
  if (loadingView != null && loadingView.getVisibility() != View.GONE) {
    loadingView.setVisibility(View.GONE);
  }
  if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
    mAnimationDrawable.stop();
  }
  if (bindingView.getRoot().getVisibility() != View.GONE) {
    bindingView.getRoot().setVisibility(View.GONE);
  }
  if (emptyView != null && emptyView.getVisibility() != View.GONE) {
    emptyView.setVisibility(View.GONE);
  }
}
