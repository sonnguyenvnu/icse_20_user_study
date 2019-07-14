private void loadCustomData(){
  HttpClient.Builder.getDouBanService().getBook(mType,mStart,mCount).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BookBean>(){
    @Override public void onError(    Throwable e){
      showContentView();
      if (bindingView.srlBook.isRefreshing()) {
        bindingView.srlBook.setRefreshing(false);
      }
      if (mStart == 0) {
        showError();
      }
    }
    @Override public void onComplete(){
      showContentView();
      if (bindingView.srlBook.isRefreshing()) {
        bindingView.srlBook.setRefreshing(false);
      }
    }
    @Override public void onSubscribe(    Disposable d){
      addSubscription(d);
    }
    @Override public void onNext(    BookBean bookBean){
      if (mStart == 0) {
        if (bookBean != null && bookBean.getBooks() != null && bookBean.getBooks().size() > 0) {
          if (mBookAdapter == null) {
            mBookAdapter=new BookAdapter(getActivity());
            bindingView.xrvBook.setAdapter(mBookAdapter);
          }
          mBookAdapter.setList(bookBean.getBooks());
          mBookAdapter.notifyDataSetChanged();
        }
        mIsFirst=false;
      }
 else {
        mBookAdapter.addAll(bookBean.getBooks());
        mBookAdapter.notifyDataSetChanged();
      }
      if (mBookAdapter != null) {
        mBookAdapter.updateLoadStatus(BookAdapter.LOAD_PULL_TO);
      }
    }
  }
);
}
