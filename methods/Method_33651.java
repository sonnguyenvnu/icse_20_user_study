private void loadWanData(){
  viewModel.searchWan(keyWord).observe(this,new Observer<HomeListBean>(){
    @Override public void onChanged(    @Nullable HomeListBean homeListBean){
      if (homeListBean != null && homeListBean.getData() != null && homeListBean.getData().getDatas() != null && homeListBean.getData().getDatas().size() > 0) {
        showLayoutView(false);
        if (viewModel.getPage() == 0) {
          mAdapter.getData().clear();
          mAdapter.notifyDataSetChanged();
        }
        if (mAdapter instanceof CategoryArticleAdapter) {
          mAdapter.addData(homeListBean.getData().getDatas());
        }
        mAdapter.loadMoreComplete();
      }
 else {
        if (viewModel.getPage() != 0) {
          mAdapter.loadMoreEnd();
        }
 else {
          mAdapter.getData().clear();
          mAdapter.notifyDataSetChanged();
        }
      }
    }
  }
);
}
