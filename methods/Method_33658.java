private void showContent(HomeListBean homeListBean){
  if (bindingView.srlWan.isRefreshing()) {
    bindingView.srlWan.setRefreshing(false);
  }
  if (homeListBean != null) {
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
      showError();
    }
 else {
      bindingView.xrvWan.noMoreLoading();
    }
  }
}
