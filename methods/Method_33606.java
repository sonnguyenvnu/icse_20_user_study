private void getHotFilm(){
  viewModel.getHotFilm().observe(this,new android.arch.lifecycle.Observer<MtimeFilmeBean>(){
    @Override public void onChanged(    @Nullable MtimeFilmeBean bookBean){
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (bookBean != null && bookBean.getMs() != null && bookBean.getMs().size() > 0) {
        if (viewModel.getStart() == 0) {
          showContentView();
          adapter.clear();
          adapter.notifyDataSetChanged();
        }
        int positionStart=adapter.getItemCount() + 1;
        adapter.addAll(bookBean.getMs());
        adapter.notifyItemRangeInserted(positionStart,bookBean.getMs().size());
        bindingView.xrvWan.refreshComplete();
        if (mIsFirst) {
          mIsFirst=false;
        }
      }
 else {
        if (adapter.getItemCount() == 0) {
          showError();
        }
 else {
          bindingView.xrvWan.noMoreLoading();
        }
      }
    }
  }
);
}
