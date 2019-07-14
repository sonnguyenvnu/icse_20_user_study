private void getHomeList(){
  viewModel.getHomeList(null).observe(this,new Observer<HomeListBean>(){
    @Override public void onChanged(    @Nullable HomeListBean homeListBean){
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (homeListBean != null && homeListBean.getData() != null && homeListBean.getData().getDatas() != null && homeListBean.getData().getDatas().size() > 0) {
        if (viewModel.getPage() == 0) {
          showContentView();
          mAdapter.clear();
          mAdapter.notifyDataSetChanged();
        }
        int positionStart=mAdapter.getItemCount() + 2;
        mAdapter.addAll(homeListBean.getData().getDatas());
        mAdapter.notifyItemRangeInserted(positionStart,homeListBean.getData().getDatas().size());
        bindingView.xrvWan.refreshComplete();
        if (mIsFirst && viewModel.getPage() == 0) {
          if (isLoadBanner) {
            androidBinding.banner.startAutoPlay();
          }
          mIsFirst=false;
        }
      }
 else {
        if (viewModel.getPage() == 0) {
          showError();
        }
 else {
          bindingView.xrvWan.refreshComplete();
          bindingView.xrvWan.noMoreLoading();
        }
      }
    }
  }
);
}
