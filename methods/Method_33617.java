private void loadCustomData(){
  viewModel.loadGankData().observe(this,new Observer<GankIoDataBean>(){
    @Override public void onChanged(    @Nullable GankIoDataBean bean){
      if (bean != null && bean.getResults() != null && bean.getResults().size() > 0) {
        if (viewModel.getPage() == 1) {
          showContentView();
          boolean isAll="??".equals(SPUtils.getString(GANK_CALA,"??"));
          adapter.setAllType(isAll);
          adapter.clear();
          adapter.notifyDataSetChanged();
        }
        int positionStart=adapter.getItemCount() + 2;
        adapter.addAll(bean.getResults());
        adapter.notifyItemRangeInserted(positionStart,bean.getResults().size());
        bindingView.xrvCustom.refreshComplete();
        if (mIsFirst) {
          mIsFirst=false;
        }
      }
 else {
        if (viewModel.getPage() == 1) {
          showError();
        }
 else {
          bindingView.xrvCustom.noMoreLoading();
        }
      }
    }
  }
);
}
