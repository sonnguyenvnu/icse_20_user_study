private void getHomeList(){
  viewModel.getHomeList(categoryId).observe(this,new Observer<HomeListBean>(){
    @Override public void onChanged(    @Nullable HomeListBean homeListBean){
      if (homeListBean != null) {
        if (homeListBean.getData() != null && homeListBean.getData().getDatas() != null && homeListBean.getData().getDatas().size() > 0) {
          showContentView();
          if (viewModel.getPage() == 0) {
            mAdapter.getData().clear();
          }
          mAdapter.addData(homeListBean.getData().getDatas());
          mAdapter.loadMoreComplete();
        }
 else {
          if (viewModel.getPage() == 0) {
            showEmptyView(String.format("????\"%s\"?????",categoryName));
          }
 else {
            mAdapter.loadMoreEnd();
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
