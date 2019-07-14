@CallSuper protected void onPause(){
  Timber.d("onPause %s",this.toString());
  dropView();
}
