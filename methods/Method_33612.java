private void loadAndroidData(){
  viewModel.loadGankData().observe(this,bean -> {
    if (bean != null && bean.getResults() != null && bean.getResults().size() > 0) {
      if (viewModel.getPage() == 1) {
        showContentView();
        adapter.clear();
        adapter.notifyDataSetChanged();
      }
      int positionStart=adapter.getItemCount() + 1;
      adapter.addAll(bean.getResults());
      adapter.notifyItemRangeInserted(positionStart,bean.getResults().size());
      bindingView.xrvAndroid.refreshComplete();
      if (mIsFirst) {
        mIsFirst=false;
      }
    }
 else {
      if (viewModel.getPage() == 1) {
        showError();
      }
 else {
        bindingView.xrvAndroid.noMoreLoading();
      }
    }
  }
);
}
