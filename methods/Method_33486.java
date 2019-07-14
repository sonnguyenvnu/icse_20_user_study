@Override protected void onCleared(){
  super.onCleared();
  if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
    this.mCompositeDisposable.clear();
  }
}
