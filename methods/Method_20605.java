@CallSuper protected void onDetach(){
  Timber.d("onDetach %s",this.toString());
  this.viewChange.onCompleted();
}
