@CallSuper protected void onDestroy(){
  Timber.d("onDestroy %s",this.toString());
  this.subscriptions.clear();
  this.viewChange.onCompleted();
}
