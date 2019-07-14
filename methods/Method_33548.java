private void getBook(){
  viewModel.getBook().observe(this,new android.arch.lifecycle.Observer<BookBean>(){
    @Override public void onChanged(    @Nullable BookBean bookBean){
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (bookBean != null && bookBean.getBooks() != null && bookBean.getBooks().size() > 0) {
        if (viewModel.getStart() == 0) {
          showContentView();
          mBookAdapter.clear();
          mBookAdapter.notifyDataSetChanged();
        }
        int positionStart=mBookAdapter.getItemCount() + 2;
        mBookAdapter.addAll(bookBean.getBooks());
        mBookAdapter.notifyItemRangeInserted(positionStart,bookBean.getBooks().size());
        bindingView.xrvWan.refreshComplete();
        if (mIsFirst) {
          mIsFirst=false;
        }
      }
 else {
        if (mBookAdapter.getItemCount() == 0) {
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
