private void loadDouBanTop250(){
  viewModel.getHotMovie().observe(this,new Observer<HotMovieBean>(){
    @Override public void onChanged(    @Nullable HotMovieBean bean){
      if (bean != null && bean.getSubjects() != null && bean.getSubjects().size() > 0) {
        if (viewModel.getStart() == 0) {
          showContentView();
          mDouBanTopAdapter.clear();
        }
        int positionStart=mDouBanTopAdapter.getItemCount() + 1;
        mDouBanTopAdapter.addAll(bean.getSubjects());
        mDouBanTopAdapter.notifyItemRangeInserted(positionStart,bean.getSubjects().size());
        bindingView.xrvTop.refreshComplete();
      }
 else {
        if (mDouBanTopAdapter.getItemCount() == 0) {
          showError();
        }
 else {
          bindingView.xrvTop.noMoreLoading();
        }
      }
    }
  }
);
}
