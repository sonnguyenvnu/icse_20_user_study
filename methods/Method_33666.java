private void getCollectList(){
  viewModel.getCollectList().observe(this,new Observer<HomeListBean>(){
    @Override public void onChanged(    @Nullable HomeListBean homeListBean){
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (homeListBean != null) {
        if (homeListBean.getData() != null && homeListBean.getData().getDatas() != null && homeListBean.getData().getDatas().size() > 0) {
          if (viewModel.getPage() == 0) {
            showContentView();
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
          }
          int positionStart=mAdapter.getItemCount() + 1;
          mAdapter.addAll(homeListBean.getData().getDatas());
          mAdapter.notifyItemRangeInserted(positionStart,homeListBean.getData().getDatas().size());
          bindingView.xrvWan.refreshComplete();
        }
 else {
          if (viewModel.getPage() == 0) {
            showEmptyView("?????????~");
          }
 else {
            bindingView.xrvWan.noMoreLoading();
          }
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
