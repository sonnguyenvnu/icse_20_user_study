public void addSubscription(Disposable s){
  if (this.mCompositeDisposable == null) {
    this.mCompositeDisposable=new CompositeDisposable();
  }
  this.mCompositeDisposable.add(s);
}
