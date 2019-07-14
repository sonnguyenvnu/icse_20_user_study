private void loadGankData(){
  viewModel.loadGankData(keyWord).observe(this,bean -> {
    if (bean != null && bean.getResults() != null && bean.getResults().size() > 0) {
      showLayoutView(false);
      if (viewModel.getGankPage() == 1) {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
      }
      if (mAdapter instanceof GankAndroidSearchAdapter) {
        GankAndroidSearchAdapter adapter=(GankAndroidSearchAdapter)this.mAdapter;
        adapter.addData(bean.getResults());
        int position=binding.tlSearch.getSelectedTabPosition();
        if (position == 2) {
          adapter.setAllType(true);
        }
 else {
          adapter.setAllType(false);
        }
      }
      mAdapter.loadMoreComplete();
    }
 else {
      if (viewModel.getGankPage() != 1) {
        mAdapter.loadMoreEnd();
      }
 else {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        mAdapter.loadMoreComplete();
      }
    }
  }
);
}
