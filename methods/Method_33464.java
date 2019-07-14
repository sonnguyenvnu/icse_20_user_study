protected void showError(){
  if (loadingView != null && loadingView.getVisibility() != View.GONE) {
    loadingView.setVisibility(View.GONE);
  }
  if (mAnimationDrawable.isRunning()) {
    mAnimationDrawable.stop();
  }
  if (errorView == null) {
    ViewStub viewStub=(ViewStub)findViewById(R.id.vs_error_refresh);
    errorView=viewStub.inflate();
    errorView.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        showLoading();
        onRefresh();
      }
    }
);
  }
 else {
    errorView.setVisibility(View.VISIBLE);
  }
  if (bindingView.getRoot().getVisibility() != View.GONE) {
    bindingView.getRoot().setVisibility(View.GONE);
  }
}
