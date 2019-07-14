private void getCollectUrlList(){
  viewModel.getCollectUrlList().observe(this,new Observer<CollectUrlBean>(){
    @Override public void onChanged(    @Nullable CollectUrlBean bean){
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (bean != null) {
        if (bean.getData() != null && bean.getData() != null && bean.getData().size() > 0) {
          showContentView();
          mAdapter.clear();
          mAdapter.addAll(bean.getData());
          mAdapter.notifyDataSetChanged();
          bindingView.xrvWan.refreshComplete();
          bindingView.xrvWan.noMoreLoading();
        }
 else {
          showEmptyView("?????????~");
        }
        if (mIsFirst) {
          mIsFirst=false;
        }
      }
 else {
        showError();
      }
    }
  }
);
}
