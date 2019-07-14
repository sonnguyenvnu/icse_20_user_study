private void loadBookDetail(){
  HttpClient.Builder.getDouBanService().getBookDetail(booksBean.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BookDetailBean>(){
    @Override public void onError(    Throwable e){
      showError();
    }
    @Override public void onComplete(){
      showContentView();
    }
    @Override public void onSubscribe(    Disposable d){
      addSubscription(d);
    }
    @Override public void onNext(    final BookDetailBean bookDetailBean){
      mBookDetailUrl=bookDetailBean.getAlt();
      mBookDetailName=bookDetailBean.getTitle();
      bindingContentView.setBookDetailBean(bookDetailBean);
      bindingContentView.executePendingBindings();
    }
  }
);
}
