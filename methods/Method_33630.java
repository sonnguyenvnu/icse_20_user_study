private void loadWelfareData(){
  viewModel.loadWelfareData().observe(this,new Observer<GankIoDataBean>(){
    @Override public void onChanged(    @Nullable GankIoDataBean bean){
      if (bean != null && bean.getResults() != null && bean.getResults().size() > 0) {
        if (viewModel.getPage() == 1) {
          showContentView();
          mWelfareAdapter.clear();
        }
        int positionStart=mWelfareAdapter.getItemCount() + 1;
        mWelfareAdapter.addAll(bean.getResults());
        mWelfareAdapter.notifyItemRangeInserted(positionStart,bean.getResults().size());
        bindingView.xrvWelfare.refreshComplete();
        if (isFirst) {
          isFirst=false;
        }
      }
 else {
        bindingView.xrvWelfare.refreshComplete();
        if (mWelfareAdapter.getItemCount() == 0) {
          showError();
        }
 else {
          bindingView.xrvWelfare.noMoreLoading();
        }
      }
    }
  }
);
}
