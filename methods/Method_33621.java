@Override protected void loadData(){
  if (mIsPrepared && isLoadBanner) {
    onResume();
  }
  if (!mIsVisible || !mIsPrepared) {
    return;
  }
  bindingView.recyclerView.postDelayed(() -> viewModel.loadData(),150);
}
