@Override public void manageDisposable(@Nullable Disposable... disposables){
  if (disposables != null) {
    subscriptionHandler.manageDisposables(disposables);
  }
}
