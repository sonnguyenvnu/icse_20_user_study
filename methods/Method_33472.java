public void addSubscription(Disposable disposable){
  if (this.mCompositeDisposable == null) {
    this.mCompositeDisposable=new CompositeDisposable();
  }
  this.mCompositeDisposable.add(disposable);
}
